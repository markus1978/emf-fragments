package de.hub.emffrag.testmodels.frag.testmodel.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class TestContainmentIndexBuilder implements de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex> {
  // features and builders
  private java.lang.String m_prefix;
  // helper attributes
  private boolean m_featurePrefixSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newTestContainmentIndexBuilder()
   */
  private TestContainmentIndexBuilder() {
  }

  /**
   * This method creates a new instance of the TestContainmentIndexBuilder.
   * @return new instance of the TestContainmentIndexBuilder
   */
  public static TestContainmentIndexBuilder newTestContainmentIndexBuilder() {
    return new TestContainmentIndexBuilder();
  }

  /**
   * This method creates a new instance of the TestContainmentIndexBuilder. 
   * The builder is initialized using an existing '<em><b>de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param testContainmentIndex The existing '<em><b>de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the TestContainmentIndexBuilder
   */
  public static TestContainmentIndexBuilder newTestContainmentIndexBuilder(de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex p_testContainmentIndex) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex _testContainmentIndex = (de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex) c
        .copy(((de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex) p_testContainmentIndex));
    c.copyReferences();
    TestContainmentIndexBuilder _builder = newTestContainmentIndexBuilder();
    _builder.prefix(_testContainmentIndex.getPrefix());
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public TestContainmentIndexBuilder but() {
    TestContainmentIndexBuilder _builder = newTestContainmentIndexBuilder();
    _builder.m_featurePrefixSet = m_featurePrefixSet;
    _builder.m_prefix = m_prefix;
    return _builder;
  }

  /**
   * This method constructs the final de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex type.
   * @return new instance of the de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex type
   */
  public de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex build() {
    final de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex _newInstance = (de.hub.emffrag.testmodels.frag.testmodel.TestContainmentIndex) de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory.eINSTANCE
        .create(de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage.eINSTANCE.getTestContainmentIndex());
    if (m_featurePrefixSet) {
      _newInstance.setPrefix(m_prefix);
    }
    return _newInstance;
  }

  public TestContainmentIndexBuilder prefix(java.lang.String p_prefix) {
    m_prefix = p_prefix;
    m_featurePrefixSet = true;
    return this;
  }
}
