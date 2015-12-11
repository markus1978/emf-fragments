package de.hub.emffrag;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

public class FStoreObject extends FAbstractStoreObject {
	
	private static final int CLASS = 1 << 0;
	private static final int CONTAINER = 1 << 1;
	private static final int FRAGMENT = 1 << 2;
	private static final int SETTING = 1 << 3;
	private static final int PROXY = 1 << 4;

	private static final int FIELD_MASK = CONTAINER | CLASS | SETTING | PROXY;

	protected FStoreObject(EClass eClass) {
		fSetClass(eClass);
	}

	public FStoreObject(FURI proxyURI) {
		fSetProxyURI(proxyURI);
	}

	private Object[] fSetting() {
		if (hasField(SETTING)) {
			return (Object[]) getField(SETTING);
		} else {
			int size = eClass().getFeatureCount();
			Object[] setting = new Object[size];
			setField(SETTING, setting);
			return setting;
		}
	}
	
	public EClass eClass() {
		if (hasField(CLASS)) {
			return (EClass) getField(CLASS);
		} else {
			return null;
		}
	}
	
	protected void fSetClass(EClass eClass) {
		setField(CLASS, eClass);
	}
	
	public Object fGet(EStructuralFeature feature) {
		Object result = (hasField(SETTING)) ? ((Object[])getField(SETTING))[feature.getFeatureID()] : null;
		if (feature.isMany() && result == null) {
			result = new ArrayList<Object>();
			fSetting()[feature.getFeatureID()] = result;
		}
		return result;
	}
	
	public void fSet(EStructuralFeature feature, Object value) {
		fSetting()[feature.getFeatureID()] = value;
	}
	
	public boolean fIsSet(EStructuralFeature feature) {
		return fSetting()[feature.getFeatureID()] != null;
	}
	
	public void fUnSet(EStructuralFeature feature) {
		fSetting()[feature.getFeatureID()] = null;
	}
	
	public boolean fIsProxy() {
		return hasField(PROXY);
	}
	
	public void fSetProxyURI(FURI uri) {
		setField(PROXY, uri);
	}
	
	public FURI fProxyURI() {
		return (FURI) getField(PROXY);
	}

	public FStoreObject fContainer() {
		return (FStoreObject)getField(CONTAINER);
	}
	
	public EStructuralFeature fContainingFeature() {
		FStoreObject fContainer = fContainer();
		if (fContainer != null) {
			int containingFeatureId = fFlags >> 16;			
			return fContainer.eClass().getEStructuralFeature(containingFeatureId);
		} else {
			return null;
		}
	}
	
	public void fSetContainer(FStoreObject newContainer, int newContainingFeatureID) {
		fFlags = newContainingFeatureID << 16 | (fFlags & 0x00FF);
		setField(CONTAINER, newContainer);
	}

	@Override
	protected int firstField() {
		return CLASS;
	}

	@Override
	protected int lastField() {
		return PROXY;
	}

	@Override
	protected int fieldMask() {
		return FIELD_MASK;
	}

	public Fragment fFragment() {
		return (Fragment)getField(FRAGMENT);
	}	
	
	protected void fSetFragment(Fragment fragment) {
		setField(FRAGMENT, fragment);
	}

	public void fUnload(FURI uri) {
		setField(SETTING, null);
		setField(CONTAINER, null);
		setField(FRAGMENT, null);
		fSetProxyURI(uri);
	}
}
