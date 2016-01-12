package de.hub.emffrag.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.FluentIterable;

import de.hub.emffrag.FragmentationUtil;

/**
 * Can have different states and stores different things in different states:
 * loaded non root object:
 * - eClass
 * - container
 * - containingFeature
 * - isModified
 * - settings
 * - root
 * loaded root object
 * - all of loaded non root object
 * - fragmentID
 * - fragmentation
 * proxy
 * - proxyURI
 * - fragmentation
 * 
 * TODO minimalize storage with flexible object array
 */
public class FStoreObjectImpl implements FStoreObject {
	
	private static final int MODIFIED = 1 << 1;
	
	private EClass fClass = null;
	private FStoreObject container = null;
	private FStoreObjectImpl root = null;
	private int fragmentID = -1;
	private FURI proxyURI = null;
	private Object[] settings = null;
	private FStoreFragmentation fragmentation;
	private int flags = 0;
	
	public FStoreObjectImpl(FURI uri, EClass eClass) {
		this(eClass);
		this.proxyURI = uri;
	}
	
	public FStoreObjectImpl(EClass eClass) {
		this.root = this;
		this.fClass = eClass;
		checkRootCondition();
	}
	
	@Override
	public EClass fClass() {
		return fClass;
	}
	
	@Override
	public void fSetClass(EClass eClass) {
		Preconditions.checkArgument(eClass != null);
		this.fClass = eClass;
	}

	@Override
	public Object fGet(EStructuralFeature feature) {
		Object value = settings()[fClass.getFeatureID(feature)];
		if (value == null && feature.isMany()) {
			value = new ArrayList<Object>();
			fSet(feature, value);
		}
		return value;
	}

	@Override
	public void fSet(EStructuralFeature feature, Object value) {	
		settings()[fClass.getFeatureID(feature)] = value;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean fIsSet(EStructuralFeature feature) {
		return fGet(feature) != null && (!feature.isMany() || !((List)fGet(feature)).isEmpty());
	}

	@Override
	public void fUnSet(EStructuralFeature feature) {
		fSet(feature, null);
	}

	@Override
	public boolean fIsRoot() {
		return root == this;
	}

	@Override
	public FStoreObject fRoot() {
		return root;
	}
	
	private void fSetRoot(FStoreObject root, boolean isEmpty) {
		this.root = (FStoreObjectImpl) root;
		if (!isEmpty) {
			for(FStoreObject content: this.fAllContents(true)) {
				((FStoreObjectImpl)content).root = (FStoreObjectImpl)root;
			}
		}
		checkRootCondition();
	}

	@Override
	public int fFragmentID() {
		return root.fragmentID;
	}

	@Override
	public void fSetFragmentID(FStoreFragmentation fragmentation, int fragmentID) {
		root.fragmentID = fragmentID;
		root.fragmentation = fragmentation;
	}

	@Override
	public FStoreFragmentation fFragmentation() {
		if (fIsRoot() || fIsProxy()) {
			return fragmentation;
		} else {
			return root.fragmentation;
		}
	}

	private void checkRootCondition() {
		if (!(root.fIsRoot() || root.fIsProxy()) || root == null) {
			if (root == null) {
				throw new IllegalStateException("Root cannot be null");
			} else {
				throw new IllegalStateException("A root must be root or at least a proxy");
			}
		}
	}

	@Override
	public FStoreObject fContainer() {
		return container;
	}

	@Override
	public EReference fContainingFeature() {
		if (container != null) {
			return (EReference) container.fClass().getEStructuralFeature(flags >> 16);
		} else {
			return null;
		}
	}
	
	@Override
	public void fSetContainer(FStoreObject newContainer, EReference containingFeature, boolean isEmpty) {
		if (newContainer != container) {
			fMarkModified(true);
		}
		
		FStoreFragmentation oldFragmentation = fFragmentation();
		if (newContainer != null) {
			boolean isAddedToFragmentation = newContainer.fFragmentation() != null && fFragmentation() != newContainer.fFragmentation();
			
			flags = newContainer.fClass().getFeatureID(containingFeature) << 16 | (flags & 0x00FF);
			container = newContainer;
			fMarkModified(true);
			if (FragmentationUtil.isFragmenting(containingFeature)) {
				fSetRoot(this, isEmpty);
			} else {
				fragmentation = null;
				fSetRoot(newContainer.fRoot(), isEmpty);
			}
			
			if (isAddedToFragmentation) {
				if (oldFragmentation != null) {
					oldFragmentation.onRemoveFromFragmentation(this);
				}
				newContainer.fFragmentation().onAddToFragmentation(this);
			}
		} else {			
			container = null;
			if (oldFragmentation != null) {
				oldFragmentation.onRemoveFromFragmentation(this);
			}
			fSetRoot(this, isEmpty);
		}
	}

	@Override
	public boolean fIsProxy() {
		return proxyURI != null;
	}

	@Override
	public FURI fProxyURI() {
		return proxyURI;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FURI fCreateURI() {
		if (fIsProxy()) {
			return fProxyURI();
		}
		FURIImpl uri = new FURIImpl();	
		int fragmentID = fFragmentID();
		uri.setFragment(fragmentID);
		FStoreFragmentation fFragmentation = fFragmentation();
		if (fFragmentation != null) {
			uri.setFragmentation(fFragmentation.getURI());
		}
		FStoreObject i = this;
		while (!i.fIsRoot()) {
			EReference fContainingFeature = i.fContainingFeature();
			int fContainingFeatureID = i.fContainer().fClass().getFeatureID(fContainingFeature);
			if (fContainingFeature.isMany()) {
				uri.addFeatureToSegment(fContainingFeatureID, ((List<FStoreObjectImpl>)i.fContainer().fGet(fContainingFeature)).indexOf(i));	
			} else {
				uri.addFeatureToSegment(fContainingFeatureID, -1);
			}
			i = i.fContainer();
		}
		return uri;
	}
	
	@Override
	public FStoreObject resolve(boolean loadOnDemand) {
		if (fIsProxy()) {
			FStoreObject resolved = fFragmentation().resolve(fProxyURI(), loadOnDemand);
			if (resolved == null && !loadOnDemand) {
				return this;
			} else {
				return resolved;
			}
		} else {
			return this;
		}
	}

	@Override
	public FURI fUnload(FURI uri) {
		fragmentation = fFragmentation();
		if (uri == null) {
			uri = fCreateURI();
		} else {
			uri = FURI.copy(uri);
		}
		
		FStore.fINSTANCE.proxyManager.onFStoreObjectUnloaded(this, uri);
		
		settings = null;
		container = null;
		flags = 0;
		proxyURI = uri;
		
		return uri;
	}

	@Override
	public boolean fModified() {
		return (root.flags & MODIFIED) != 0;		
	}
	
	@Override
	public void fMarkModified(boolean modified) {
		if (modified) {
			root.flags |= MODIFIED;	
		} else {
			root.flags &= ~MODIFIED;
		}
	}
	
	private Object[] settings() {
		if (settings == null) {
			int size = fClass().getFeatureCount();
			settings = new Object[size];
		}
		return settings;
	}
	
	/**
	 * @return An unmodifiable iterable that allows to iterate all direct
	 *         contents of this object that is contained in the same fragment
	 *         as this object. It uses fragmenting references to
	 *         determine if contents is supposed to be in the same fragment.
	 */
	@Override
	public Iterable<FStoreObject> fContents(boolean onlyWithInSameFragment) {
		return new FluentIterable<FStoreObject>() {
			@Override
			public Iterator<FStoreObject> iterator() {
				return new AbstractIterator<FStoreObject>() {
					int currentFeatureIndex = 0;
					EReference currentFeature = null;
					Object currentFeatureValue = null;
					int currentSettingIndex = 0;

					@Override
					protected FStoreObject computeNext() {
						if (currentFeatureValue == null) {
							Object[] fSetting = settings();
							while (currentSettingIndex < fSetting.length) {
								Object nextValue = fSetting[currentSettingIndex];
								if (nextValue != null) {
									EStructuralFeature nextFeature = fClass().getEStructuralFeature(currentSettingIndex);
									if (nextFeature instanceof EReference && ((EReference) nextFeature).isContainment() && 
											(!onlyWithInSameFragment || !FragmentationUtil.isFragmenting((EReference) nextFeature))) {
										currentFeature = (EReference) nextFeature;
										currentFeatureValue = nextValue;
										currentSettingIndex++;
										break;
									}
								}
								currentSettingIndex++;
							}
						}

						if (currentFeatureValue != null) {
							if (currentFeature.isMany()) {
								List<?> currentFeatureListValue = (List<?>) currentFeatureValue;
								if (currentFeatureIndex < currentFeatureListValue.size()) {
									Object result = currentFeatureListValue.get(currentFeatureIndex);
									currentFeatureIndex++;
									return (FStoreObject) result;
								} else {
									currentFeatureIndex = 0;
									currentFeature = null;
									currentFeatureValue = null;
									return computeNext();
								}
							} else {
								Object result = currentFeatureValue;
								currentFeatureValue = null;
								currentFeature = null;
								return (FStoreObject) result;
							}
						} else {
							return endOfData();
						}
					}
				};
			}
		};
	}

	/**
	 * @return An unmodifiable iterable that allows to iterate all direct and
	 *         recursive contents of this object that is contained in the same
	 *         fragment as this object. It will not recurse
	 *         into other fragments, based on fragmenting containment
	 *         references.
	 */
	@Override
	public Iterable<FStoreObject> fAllContents(boolean onlyWithInSameFragment) {
		return new FluentIterable<FStoreObject>() {
			@Override
			public Iterator<FStoreObject> iterator() {
				return new AbstractIterator<FStoreObject>() {
					Iterator<FStoreObject> contentIterator = fContents(onlyWithInSameFragment).iterator();
					Iterator<FStoreObject> currentSubContentIterator = null;

					@Override
					protected FStoreObject computeNext() {
						if (currentSubContentIterator != null) {
							if (currentSubContentIterator.hasNext()) {
								return currentSubContentIterator.next();
							} else {
								currentSubContentIterator = null;
							}
						}

						if (contentIterator.hasNext()) {
							FStoreObject nextDirectContent = contentIterator.next();
							nextDirectContent = nextDirectContent.resolve(true);							
							currentSubContentIterator = nextDirectContent.fAllContents(onlyWithInSameFragment).iterator();
							return nextDirectContent;
						} else {
							return endOfData();
						}
					}
				};
			}
		};
	}

	public String toBaseString() {
		if (fIsProxy()) {
			return "proxy: " + fProxyURI().toString();
		} else {
			String str = fClass().getName();
			EStructuralFeature nameFeature = fClass().getEStructuralFeature("name");
			if (nameFeature != null) {
				str += " " + fGet(nameFeature);
			}
			str += "[";
			if (fIsRoot()) {
				str += "R";
			}
			if (fModified()) {
				str += "M";
			}
			if (fContainer() != null) {
				str += "C";
			}
			str += "]"; 
			return str;
		}
	}
	
	@Override
	public String toString() {
		return toFullTreeString();
	}

	private class FullTreeHelper {
		int indent = 0;
		StringBuilder out = new StringBuilder();
		
		@SuppressWarnings("unchecked")
		FullTreeHelper appendObject(FStoreObject object) {
			append(((FStoreObjectImpl) object).toBaseString());
			append(" {\n", 1);
				for (EStructuralFeature feature: object.fClass().getEAllStructuralFeatures()) {
					if (object.fIsSet(feature)) { 
						append(feature.getName());
						append("=");
						if (feature.isMany()) {
							append("[\n", 1);
							for(Object value: (List<Object>)object.fGet(feature)) { 
								appendValue(value, feature);
								append("\n");
							}
							append("]", -1);
						} else {
							appendValue(object.fGet(feature), feature);
						}
						append("\n");
					}
				}
			append("}", -1);
			return this;
		}
		
		String getResult() {
			return out.toString();
		}
		
		void appendValue(Object value, EStructuralFeature feature) {
			if (value == null) {
				append("null");
			} else if (value instanceof String) {
				String stringValue = (String) value;
				append("\"");
				if (stringValue.length() > 40) {					
					append(stringValue.substring(0, 40));
					append("...");
				} else {
					append(stringValue);
				}
				append("\"");
			} else if (feature instanceof EAttribute) {
				append(value.toString());
			} else {
				EReference reference = (EReference) feature;
				FStoreObject objectValue = (FStoreObject)value;
				if (reference.isContainment() && !objectValue.fIsProxy()) {
					appendObject(objectValue);
				} else {
					append("<ref> ");
					append(((FStoreObjectImpl) objectValue).toBaseString());
				}
			}
		}
		
		void append(String str) {
			append(str, 0);
		}
		
		void append(String str, int indentChange) {
			if (indentChange < 0) {
				out.deleteCharAt(out.length()-1);
				out.deleteCharAt(out.length()-1);
			}
			out.append(str);
			indent += indentChange;
			if (str.endsWith("\n")) {
				for (int i = 0; i < indent; i++) out.append("  ");
			}
		}
	}
	
	public String toFullTreeString() {
		return new FullTreeHelper().appendObject(this).getResult();
	}
}
