package de.hub.emffrag.testmodels.frag.testmodel.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>de.hub.emffrag.testmodels.frag.testmodel.TestObject</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class TestObjectBuilder implements de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<de.hub.emffrag.testmodels.frag.testmodel.TestObject> {
  // features and builders
  private java.lang.String m_name;
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.TestObject> m_crossReferences = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.TestObject>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject>> m_featureCrossReferencesBuilder = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject>>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.TestObject> m_fragmentedContents = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.TestObject>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject>> m_featureFragmentedContentsBuilder = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject>>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.TestObject> m_regularContents = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.TestObject>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject>> m_featureRegularContentsBuilder = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject>>();
  // helper attributes
  private boolean m_featureCrossReferencesSet = false;
  private boolean m_featureFragmentedContentsSet = false;
  private boolean m_featureNameSet = false;
  private boolean m_featureRegularContentsSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newTestObjectBuilder()
   */
  private TestObjectBuilder() {
  }

  /**
   * This method creates a new instance of the TestObjectBuilder.
   * @return new instance of the TestObjectBuilder
   */
  public static TestObjectBuilder newTestObjectBuilder() {
    return new TestObjectBuilder();
  }

  /**
   * This method creates a new instance of the TestObjectBuilder. 
   * The builder is initialized using an existing '<em><b>de.hub.emffrag.testmodels.frag.testmodel.TestObject</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>de.hub.emffrag.testmodels.frag.testmodel.TestObject</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param testObject The existing '<em><b>de.hub.emffrag.testmodels.frag.testmodel.TestObject</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the TestObjectBuilder
   */
  public static TestObjectBuilder newTestObjectBuilder(de.hub.emffrag.testmodels.frag.testmodel.TestObject p_testObject) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    de.hub.emffrag.testmodels.frag.testmodel.TestObject _testObject = (de.hub.emffrag.testmodels.frag.testmodel.TestObject) c
        .copy(((de.hub.emffrag.testmodels.frag.testmodel.TestObject) p_testObject));
    c.copyReferences();
    TestObjectBuilder _builder = newTestObjectBuilder();
    _builder.name(_testObject.getName());
    if (_testObject.getCrossReferences() != null) {
      _builder.crossReferences(_testObject.getCrossReferences());
    }
    if (_testObject.getFragmentedContents() != null) {
      _builder.fragmentedContents(_testObject.getFragmentedContents());
    }
    if (_testObject.getRegularContents() != null) {
      _builder.regularContents(_testObject.getRegularContents());
    }
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public TestObjectBuilder but() {
    TestObjectBuilder _builder = newTestObjectBuilder();
    _builder.m_featureCrossReferencesSet = m_featureCrossReferencesSet;
    _builder.m_crossReferences = m_crossReferences;
    _builder.m_featureCrossReferencesBuilder = m_featureCrossReferencesBuilder;
    _builder.m_featureFragmentedContentsSet = m_featureFragmentedContentsSet;
    _builder.m_fragmentedContents = m_fragmentedContents;
    _builder.m_featureFragmentedContentsBuilder = m_featureFragmentedContentsBuilder;
    _builder.m_featureNameSet = m_featureNameSet;
    _builder.m_name = m_name;
    _builder.m_featureRegularContentsSet = m_featureRegularContentsSet;
    _builder.m_regularContents = m_regularContents;
    _builder.m_featureRegularContentsBuilder = m_featureRegularContentsBuilder;
    return _builder;
  }

  /**
   * This method constructs the final de.hub.emffrag.testmodels.frag.testmodel.TestObject type.
   * @return new instance of the de.hub.emffrag.testmodels.frag.testmodel.TestObject type
   */
  public de.hub.emffrag.testmodels.frag.testmodel.TestObject build() {
    final de.hub.emffrag.testmodels.frag.testmodel.TestObject _newInstance = (de.hub.emffrag.testmodels.frag.testmodel.TestObject) de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory.eINSTANCE
        .create(de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage.eINSTANCE.getTestObject());
    if (m_featureNameSet) {
      _newInstance.setName(m_name);
    }
    if (m_featureCrossReferencesSet) {
      _newInstance.getCrossReferences().addAll(m_crossReferences);
    } else {
      if (!m_featureCrossReferencesBuilder.isEmpty()) {
        for (de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> builder : m_featureCrossReferencesBuilder) {
          _newInstance.getCrossReferences().add(builder.build());
        }
      }
    }
    if (m_featureFragmentedContentsSet) {
      _newInstance.getFragmentedContents().addAll(m_fragmentedContents);
    } else {
      if (!m_featureFragmentedContentsBuilder.isEmpty()) {
        for (de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> builder : m_featureFragmentedContentsBuilder) {
          _newInstance.getFragmentedContents().add(builder.build());
        }
      }
    }
    if (m_featureRegularContentsSet) {
      _newInstance.getRegularContents().addAll(m_regularContents);
    } else {
      if (!m_featureRegularContentsBuilder.isEmpty()) {
        for (de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> builder : m_featureRegularContentsBuilder) {
          _newInstance.getRegularContents().add(builder.build());
        }
      }
    }
    return _newInstance;
  }

  public TestObjectBuilder name(java.lang.String p_name) {
    m_name = p_name;
    m_featureNameSet = true;
    return this;
  }

  public TestObjectBuilder crossReferences(de.hub.emffrag.testmodels.frag.testmodel.TestObject p_crossReferences) {
    m_crossReferences.add(p_crossReferences);
    m_featureCrossReferencesSet = true;
    return this;
  }

  public TestObjectBuilder crossReferences(java.util.Collection<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> p_crossReferences) {
    m_crossReferences.addAll(p_crossReferences);
    m_featureCrossReferencesSet = true;
    return this;
  }

  public TestObjectBuilder crossReferences(de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> p_testObjectBuilder) {
    m_featureCrossReferencesBuilder.add(p_testObjectBuilder);
    return this;
  }

  public TestObjectBuilder fragmentedContents(de.hub.emffrag.testmodels.frag.testmodel.TestObject p_fragmentedContents) {
    m_fragmentedContents.add(p_fragmentedContents);
    m_featureFragmentedContentsSet = true;
    return this;
  }

  public TestObjectBuilder fragmentedContents(java.util.Collection<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> p_fragmentedContents) {
    m_fragmentedContents.addAll(p_fragmentedContents);
    m_featureFragmentedContentsSet = true;
    return this;
  }

  public TestObjectBuilder fragmentedContents(de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> p_testObjectBuilder) {
    m_featureFragmentedContentsBuilder.add(p_testObjectBuilder);
    return this;
  }

  public TestObjectBuilder regularContents(de.hub.emffrag.testmodels.frag.testmodel.TestObject p_regularContents) {
    m_regularContents.add(p_regularContents);
    m_featureRegularContentsSet = true;
    return this;
  }

  public TestObjectBuilder regularContents(java.util.Collection<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> p_regularContents) {
    m_regularContents.addAll(p_regularContents);
    m_featureRegularContentsSet = true;
    return this;
  }

  public TestObjectBuilder regularContents(de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> p_testObjectBuilder) {
    m_featureRegularContentsBuilder.add(p_testObjectBuilder);
    return this;
  }
}
