package de.hub.emffrag.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.FluentIterable;

import de.hub.emffrag.FStore;
import de.hub.emffrag.FURI;
import de.hub.emffrag.FURIImpl;
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
	
	public FStoreObjectImpl(FURI uri) {
		this();
		this.proxyURI = uri;
	}
	
	public FStoreObjectImpl() {	
		this.root = this;
	}
	
	@Override
	public EClass fClass() {
		return fClass;
	}

	@Override
	public void fSetClass(EClass eClass) {
		this.fClass = eClass;
	}

	@Override
	public Object fGet(EStructuralFeature feature) {
		Object value = settings()[feature.getFeatureID()];
		if (value == null && feature.isMany()) {
			value = new ArrayList<Object>();
			fSet(feature, value);
		}
		return value;
	}

	@Override
	public void fSet(EStructuralFeature feature, Object value) {
		settings()[feature.getFeatureID()] = value;
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
			return fRoot().fFragmentation();
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
	public void fSetContainer(FStoreObject newContainer, EReference containingFeature) {
		if (newContainer != null) {
			boolean isAddedToFragmentation = newContainer.fFragmentation() != null && fFragmentation() != newContainer.fFragmentation();
			
			flags = containingFeature.getFeatureID() << 16 | (flags & 0x00FF);
			container = newContainer;		
			if (FragmentationUtil.isFragmenting(containingFeature)) {
				root = this;
			} else {
				fragmentation = null;
				root = (FStoreObjectImpl) newContainer.fRoot();
			}
			
			if (isAddedToFragmentation) {
				newContainer.fFragmentation().onAddToFragmentation(this);
			}
		} else {
			FStoreFragmentation oldFragmentation = fFragmentation();			
			container = null;
			if (oldFragmentation != null) {
				oldFragmentation.onRemoveFromFragmentation(this);
			}
			root = this;
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
		uri.setFragment(fFragmentID());
		FStoreObject i = this;
		while (!i.fIsRoot()) {
			EReference fContainingFeature = i.fContainingFeature();
			if (fContainingFeature.isMany()) {
				uri.addFeatureToSegment(fContainingFeature, ((List<FStoreObjectImpl>)i.fContainer().fGet(fContainingFeature)).indexOf(i));	
			} else {
				uri.addFeatureToSegment(fContainingFeature, -1);
			}
			i = i.fContainer();
		}
		return uri;
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
	 *         (including null) as this object. It uses fragmenting references to
	 *         determine if contents is supposed to be in the same fragment.
	 */
	public Iterable<FStoreObject> fContents() {
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
									if (nextFeature instanceof EReference && ((EReference) nextFeature).isContainment()) {
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
	 *         fragment (including null) as this object. It will not recurse
	 *         into other fragments, based on fragmenting containment
	 *         references.
	 */
	public Iterable<FStoreObject> fAllContents() {
		return new FluentIterable<FStoreObject>() {
			@Override
			public Iterator<FStoreObject> iterator() {
				return new AbstractIterator<FStoreObject>() {
					Iterator<FStoreObject> contentIterator = fContents().iterator();
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
							if (nextDirectContent.fIsProxy()) {
								nextDirectContent = nextDirectContent.fFragmentation().resolve(nextDirectContent.fProxyURI());
							}
							currentSubContentIterator = nextDirectContent.fAllContents().iterator();
							return nextDirectContent;
						} else {
							return endOfData();
						}
					}
				};
			}
		};
	}

	@Override
	public String toString() {
		if (fIsProxy()) {
			return "proxy: " + fProxyURI().toString();
		} else {
			String str = fClass().getName() + "(" + System.identityHashCode(this) + ")";
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
			str += "]";
			return str;
		}
	}
	
	
}
