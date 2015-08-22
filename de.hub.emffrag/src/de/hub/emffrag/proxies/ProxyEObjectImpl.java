package de.hub.emffrag.proxies;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;

import de.hub.emffrag.fragmentation.FObjectImpl;

public abstract class ProxyEObjectImpl extends AbstractRootProxyImpl<FObjectImpl> implements RootProxy, InternalEObject, EStructuralFeature.Internal.DynamicValueHolder {

	public ProxyEObjectImpl() {
		super(null);
	}
	
	@Override
	public Object fSource() {
		if (source == null) {
			// this is a user created proxy, we must create an internal representation for it.
			FObjectImpl source = new FObjectImpl();
			source.eSetClass(eStaticClass());
			fSetSource(source);
		}
		return super.fSource();
	}
	
	/**
	 * Implemented by generated EObject implementation.
	 */
	protected abstract EClass eStaticClass();

	/**
	 * Implemented by generated EObject implementation.
	 */
	protected abstract int eStaticFeatureCount();

	@Override
	public ProxyContainer fContainer() {
		return source.fFragment();
	}

	public int hashCode() {
		return source.hashCode();
	}

	public boolean equals(Object obj) {
		return source.equals(obj);
	}

	public String eURIFragmentSegment(EStructuralFeature eStructuralFeature, EObject eObject) {
		return source.eURIFragmentSegment(eStructuralFeature, fSource(eObject));
	}

	public void eNotify(Notification notification) {
		disgurageUsage();
		source.eNotify(notification);
	}

	public boolean eNotificationRequired() {
		return source.eNotificationRequired();
	}

	public EList<Adapter> eAdapters() {
		disgurageUsage();
		return source.eAdapters();
	}

	public EObject eObjectForURIFragmentSegment(String uriFragmentSegment) {
		return fProxy(source.eObjectForURIFragmentSegment(uriFragmentSegment));
	}

	public boolean eDeliver() {
		return source.eDeliver();
	}

	public void eSetDeliver(boolean deliver) {
		source.eSetDeliver(deliver);
	}

	public boolean eIsProxy() {
		return source.eIsProxy();
	}

	public URI eProxyURI() {
		return source.eProxyURI();
	}

	public void eSetProxyURI(URI uri) {
		disgurageUsage();
		source.eSetProxyURI(uri);
	}

	public InternalEObject eInternalContainer() {
		return fProxy(source.eInternalContainer());
	}

	public int eContainerFeatureID() {
		return source.eContainerFeatureID();
	}

	public EClass eClass() {
		return source.eClass();
	}

	public void eSetClass(EClass eClass) {
		source.eSetClass(eClass);
	}

	public Internal eDirectResource() {
		return fProxy(source.eDirectResource());
	}

	public EList<EObject> eContents() {
		return fProxy(source.eContents());
	}

	public EList<EObject> eCrossReferences() {
		return fProxy(source.eCrossReferences());
	}

	public Object dynamicGet(int dynamicFeatureID) {
		return fProxy(source.dynamicGet(dynamicFeatureID));
	}

	public void dynamicSet(int dynamicFeatureID, Object newValue) {
		source.dynamicSet(dynamicFeatureID, fSource(newValue));
	}

	public void dynamicUnset(int dynamicFeatureID) {
		source.dynamicUnset(dynamicFeatureID);
	}

	public boolean eContains(EObject eObject) {
		return source.eContains(fSource(eObject));
	}

	public EObject eContainer() {
		return fProxy(source.eContainer());
	}

	public TreeIterator<EObject> eAllContents() {
		return fProxy(source.eAllContents());
	}

	public EReference eContainmentFeature() {
		return source.eContainmentFeature();
	}

	public EStructuralFeature eContainingFeature() {
		return source.eContainingFeature();
	}

	public Resource eResource() {
		return fProxy(source.eResource());
	}

	public Internal eInternalResource() {
		return fProxy(source.eInternalResource());
	}

	public NotificationChain eSetResource(Internal resource, NotificationChain notifications) {
		disgurageUsage();
		return source.eSetResource(fSource(resource), notifications);
	}

	public Object eGet(EStructuralFeature eFeature) {
		return fProxy(source.eGet(eFeature));
	}

	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		return fProxy(source.eGet(eFeature, resolve));
	}

	public Object eGet(EStructuralFeature eFeature, boolean resolve, boolean coreType) {
		return fProxy(source.eGet(eFeature, resolve, coreType));
	}

	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		return fProxy(source.eGet(featureID, resolve, coreType));
	}

	public Object eDynamicGet(EStructuralFeature eFeature, boolean resolve) {
		return fProxy(source.eDynamicGet(eFeature, resolve));
	}

	public Object eDynamicGet(int featureID, boolean resolve, boolean coreType) {
		return fProxy(source.eDynamicGet(featureID, resolve, coreType));
	}

	public Object eOpenGet(EStructuralFeature eFeature, boolean resolve) {
		return fProxy(source.eOpenGet(eFeature, resolve));
	}

	public void eSet(EStructuralFeature eFeature, Object newValue) {
		source.eSet(eFeature, fSource(newValue));
	}

	public void eSet(int featureID, Object newValue) {
		source.eSet(featureID, fSource(newValue));
	}

	public void eDynamicSet(EStructuralFeature eFeature, Object newValue) {
		source.eDynamicSet(eFeature, fSource(newValue));
	}

	public void eDynamicSet(int featureID, Object newValue) {
		source.eDynamicSet(featureID, fSource(newValue));
	}

	public void eOpenSet(EStructuralFeature eFeature, Object newValue) {
		source.eOpenSet(eFeature, fSource(newValue));
	}

	public void eUnset(EStructuralFeature eFeature) {
		source.eUnset(eFeature);
	}

	public void eUnset(int featureID) {
		source.eUnset(featureID);
	}

	public void eDynamicUnset(EStructuralFeature eFeature) {
		source.eDynamicUnset(eFeature);
	}

	public void eDynamicUnset(int featureID) {
		source.eDynamicUnset(featureID);
	}

	public void eOpenUnset(EStructuralFeature eFeature) {
		source.eOpenUnset(eFeature);
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		return source.eIsSet(eFeature);
	}

	public boolean eIsSet(int featureID) {
		return source.eIsSet(featureID);
	}

	public boolean eDynamicIsSet(EStructuralFeature eFeature) {
		return source.eDynamicIsSet(eFeature);
	}

	public boolean eDynamicIsSet(int featureID) {
		return source.eDynamicIsSet(featureID);
	}

	public boolean eOpenIsSet(EStructuralFeature eFeature) {
		return source.eOpenIsSet(eFeature);
	}

	public NotificationChain eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID, NotificationChain msgs) {
		disgurageUsage();
		return source.eBasicSetContainer(fSource(newContainer), newContainerFeatureID, msgs);
	}

	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		disgurageUsage();
		return source.eBasicRemoveFromContainer(msgs);
	}

	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		disgurageUsage();
		return source.eBasicRemoveFromContainerFeature(msgs);
	}

	public NotificationChain eDynamicBasicRemoveFromContainer(NotificationChain msgs) {
		disgurageUsage();
		return source.eDynamicBasicRemoveFromContainer(msgs);
	}

	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class<?> baseClass, NotificationChain msgs) {
		disgurageUsage();
		return source.eInverseAdd(fSource(otherEnd), featureID, baseClass, msgs);
	}

	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		disgurageUsage();
		return source.eInverseAdd(fSource(otherEnd), featureID, msgs);
	}

	public NotificationChain eDynamicInverseAdd(InternalEObject otherEnd, int featureID, Class<?> inverseClass, NotificationChain msgs) {
		disgurageUsage();
		return source.eDynamicInverseAdd(fSource(otherEnd), featureID, inverseClass, msgs);
	}

	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class<?> baseClass, NotificationChain msgs) {
		disgurageUsage();
		return source.eInverseRemove(fSource(otherEnd), featureID, baseClass, msgs);
	}

	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		disgurageUsage();
		return source.eInverseRemove(fSource(otherEnd), featureID, msgs);
	}

	public NotificationChain eDynamicInverseRemove(InternalEObject otherEnd, int featureID, Class<?> inverseClass, NotificationChain msgs) {
		disgurageUsage();
		return source.eDynamicInverseRemove(fSource(otherEnd), featureID, inverseClass, msgs);
	}

	public EObject eResolveProxy(InternalEObject proxy) {
		return fProxy(source.eResolveProxy(fSource(proxy)));
	}

	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		return source.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		return source.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	public int eDerivedStructuralFeatureID(EStructuralFeature eStructuralFeature) {
		return source.eDerivedStructuralFeatureID(eStructuralFeature);
	}

	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		return source.eDerivedOperationID(baseOperationID, baseClass);
	}

	public int eDerivedOperationID(EOperation eOperation) {
		return source.eDerivedOperationID(eOperation);
	}

	public Setting eSetting(EStructuralFeature eFeature) {
		return fProxy(source.eSetting(eFeature));
	}

	public EStore eStore() {
		return null;
	}

	public void eSetStore(EStore store) {
		// empty
	}

	public Object eVirtualGet(int eDerivedStructuralFeatureID) {
		return fProxy(source.eVirtualGet(eDerivedStructuralFeatureID));
	}

	public Object eVirtualGet(int eDerivedStructuralFeatureID, Object defaultValue) {
		return fProxy(source.eVirtualGet(eDerivedStructuralFeatureID, fSource(defaultValue)));
	}

	public boolean eVirtualIsSet(int eDerivedStructuralFeatureID) {
		return source.eVirtualIsSet(eDerivedStructuralFeatureID);
	}

	public Object eVirtualSet(int eDerivedStructuralFeatureID, Object value) {
		return fProxy(source.eVirtualSet(eDerivedStructuralFeatureID, fSource(value)));
	}

	public Object eVirtualUnset(int eDerivedStructuralFeatureID) {
		return fProxy(source.eVirtualUnset(eDerivedStructuralFeatureID));
	}

	public Object eInvoke(EOperation eOperation, EList<?> arguments) throws InvocationTargetException {
		return fProxy(source.eInvoke(eOperation, fSource(arguments)));
	}

	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		return fProxy(source.eInvoke(operationID, fSource(arguments)));
	}

	public Object eDynamicInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		return fProxy(source.eDynamicInvoke(operationID, fSource(arguments)));
	}

	public String toString() {
		return source.toString();
	}
}
