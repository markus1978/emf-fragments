/**
 */
package de.hub.emffrag.testmodels.eobject.testmodel.proxy.meta;

import de.hub.emffrag.testmodels.eobject.testmodel.TestObject;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.hub.emffrag.testmodels.eobject.testmodel.proxy.meta.TestmodelPackage
 * @generated
 */
public interface TestmodelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TestmodelFactory eINSTANCE = de.hub.emffrag.testmodels.eobject.testmodel.proxy.impl.TestmodelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Test Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Object</em>'.
	 * @generated
	 */
	TestObject createTestObject();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TestmodelPackage getTestmodelPackage();

} //TestmodelFactory
