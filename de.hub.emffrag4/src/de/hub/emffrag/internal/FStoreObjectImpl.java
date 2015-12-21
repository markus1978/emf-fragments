package de.hub.emffrag.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.FluentIterable;

import de.hub.emffrag.FURI;
import de.hub.emffrag.Fragmentation;
import de.hub.emffrag.FragmentationUtil;

public class FStoreObjectImpl implements FStoreObject {
	
	private static final int ROOT = 1 << 0;
	private static final int MODIFIED = 1 << 1;
	
	private EClass fClass = null;
	private FStoreObject container = null;
	private int fragmentID = -1;
	private FURI proxyURI = null;
	private Object[] settings = null;
	
	private Fragmentation fragmentation;
	
	private int flags = 0;
	
	public FStoreObjectImpl(FURI uri) {
		this();
		this.proxyURI = uri;
	}
	
	public FStoreObjectImpl() {
		flags |= ROOT;
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
		return (flags & ROOT) != 0;
	}

	@Override
	public FStoreObject fRoot() {
		if (fIsRoot()) {
			return this;
		} else {
			return container.fRoot();
		}
	}

	@Override
	public int fFragmentID() {
		if (fIsRoot()) {
			return fragmentID;
		} else {
			if (fragmentID == -1) {
				fragmentID = fRoot().fFragmentID();
			}
			return fragmentID;
		}
	}

	@Override
	public void fSetFragmentID(Fragmentation fragmentation, int fragmentID) {
		if (fIsRoot()) {
			this.fragmentID = fragmentID;
			this.fragmentation = fragmentation;
		} else {
			fRoot().fSetFragmentID(fragmentation, fragmentID);
		}
	}

	@Override
	public Fragmentation fFragmentation() {
		if (fIsRoot()) {
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
			boolean isAddedToFragmentation = fFragmentation() == null && fFragmentation() != newContainer.fFragmentation();
			
			flags = containingFeature.getFeatureID() << 16 | (flags & 0x00FF);
			container = newContainer;		
			if (FragmentationUtil.isFragmenting(containingFeature)) {
				flags |= ROOT;
			} else {
				flags &= ~ROOT;
			}
			
			if (isAddedToFragmentation) {
				newContainer.fFragmentation().onAddToFragmentation(this);
			}
		} else {
			Fragmentation oldFragmentation = fFragmentation();			
			container = null;
			if (oldFragmentation != null) {
				oldFragmentation.onRemoveFromFragmentation(this);
			}
			flags |= ROOT;				
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
		FURI uri = new FURI();		
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
	public FURI fUnload() {
		FURI uri = fCreateURI();
		
		settings = null;
		container = null;
		flags = 0;
		proxyURI = uri;
		
		return uri;
	}

	@Override
	public boolean fModified() {
		if (fIsRoot()) {
			return (flags & MODIFIED) != 0;
		} else {
			return fRoot().fModified();
		}
	}
	
	@Override
	public void fMarkModified(boolean modified) {
		if (fIsRoot()) {
			if (modified) {
				flags |= MODIFIED;	
			} else {
				flags &= ~MODIFIED;
			}
				
		} else {
			fRoot().fMarkModified(modified);
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
}
