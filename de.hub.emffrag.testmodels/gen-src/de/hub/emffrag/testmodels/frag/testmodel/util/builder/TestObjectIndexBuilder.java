package de.hub.emffrag.testmodels.frag.testmodel.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class TestObjectIndexBuilder implements de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex> {
  // features and builders
  private java.lang.String m_prefix;
  // helper attributes
  private boolean m_featurePrefixSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newTestObjectIndexBuilder()
   */
  private TestObjectIndexBuilder() {
  }

  /**
   * This method creates a new instance of the TestObjectIndexBuilder.
   * @return new instance of the TestObjectIndexBuilder
   */
  public static TestObjectIndexBuilder newTestObjectIndexBuilder() {
    return new TestObjectIndexBuilder();
  }

  /**
   * This method creates a new instance of the TestObjectIndexBuilder. 
   * The builder is initialized using an existing '<em><b>de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param testObjectIndex The existing '<em><b>de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the TestObjectIndexBuilder
   */
  public static TestObjectIndexBuilder newTestObjectIndexBuilder(de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex p_testObjectIndex) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex _testObjectIndex = (de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex) c
        .copy(((de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex) p_testObjectIndex));
    c.copyReferences();
    TestObjectIndexBuilder _builder = newTestObjectIndexBuilder();
    _builder.prefix(_testObjectIndex.getPrefix());
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public TestObjectIndexBuilder but() {
    TestObjectIndexBuilder _builder = newTestObjectIndexBuilder();
    _builder.m_featurePrefixSet = m_featurePrefixSet;
    _builder.m_prefix = m_prefix;
    return _builder;
  }

  /**
   * This method constructs the final de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex type.
   * @return new instance of the de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex type
   */
  public de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex build() {
    final de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex _newInstance = (de.hub.emffrag.testmodels.frag.testmodel.TestObjectIndex) de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory.eINSTANCE
        .create(de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage.eINSTANCE.getTestObjectIndex());
    if (m_featurePrefixSet) {
      _newInstance.setPrefix(m_prefix);
    }
    return _newInstance;
  }

  public TestObjectIndexBuilder prefix(java.lang.String p_prefix) {
    m_prefix = p_prefix;
    m_featurePrefixSet = true;
    return this;
  }
}
