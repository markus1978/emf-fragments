package de.hub.emffrag.testmodels.frag.testmodel.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class TestObjectWithIndexesBuilder implements de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes> {
  // features and builders
  private de.hub.emffrag.testmodels.frag.testmodel.TestEnum m_enumAttribute;
  private java.lang.String m_name;
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.TestObject> m_crossReferences = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.TestObject>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject>> m_featureCrossReferencesBuilder = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject>>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.TestObject> m_fragmentedContents = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.TestObject>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject>> m_featureFragmentedContentsBuilder = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject>>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.TestObject> m_indexedContents = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.TestObject>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject>> m_featureIndexedContentsBuilder = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject>>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.TestObject> m_indexedReferences = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.TestObject>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject>> m_featureIndexedReferencesBuilder = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject>>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.TestObject> m_regularContents = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.TestObject>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject>> m_featureRegularContentsBuilder = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject>>();
  // helper attributes
  private boolean m_featureCrossReferencesSet = false;
  private boolean m_featureEnumAttributeSet = false;
  private boolean m_featureFragmentedContentsSet = false;
  private boolean m_featureIndexedContentsSet = false;
  private boolean m_featureIndexedReferencesSet = false;
  private boolean m_featureNameSet = false;
  private boolean m_featureRegularContentsSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newTestObjectWithIndexesBuilder()
   */
  private TestObjectWithIndexesBuilder() {
  }

  /**
   * This method creates a new instance of the TestObjectWithIndexesBuilder.
   * @return new instance of the TestObjectWithIndexesBuilder
   */
  public static TestObjectWithIndexesBuilder newTestObjectWithIndexesBuilder() {
    return new TestObjectWithIndexesBuilder();
  }

  /**
   * This method creates a new instance of the TestObjectWithIndexesBuilder. 
   * The builder is initialized using an existing '<em><b>de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param testObjectWithIndexes The existing '<em><b>de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the TestObjectWithIndexesBuilder
   */
  public static TestObjectWithIndexesBuilder newTestObjectWithIndexesBuilder(de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes p_testObjectWithIndexes) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes _testObjectWithIndexes = (de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes) c
        .copy(((de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes) p_testObjectWithIndexes));
    c.copyReferences();
    TestObjectWithIndexesBuilder _builder = newTestObjectWithIndexesBuilder();
    _builder.enumAttribute(_testObjectWithIndexes.getEnumAttribute());
    _builder.name(_testObjectWithIndexes.getName());
    if (_testObjectWithIndexes.getCrossReferences() != null) {
      _builder.crossReferences(_testObjectWithIndexes.getCrossReferences());
    }
    if (_testObjectWithIndexes.getFragmentedContents() != null) {
      _builder.fragmentedContents(_testObjectWithIndexes.getFragmentedContents());
    }
    if (_testObjectWithIndexes.getIndexedContents() != null) {
      _builder.indexedContents(_testObjectWithIndexes.getIndexedContents());
    }
    if (_testObjectWithIndexes.getIndexedReferences() != null) {
      _builder.indexedReferences(_testObjectWithIndexes.getIndexedReferences());
    }
    if (_testObjectWithIndexes.getRegularContents() != null) {
      _builder.regularContents(_testObjectWithIndexes.getRegularContents());
    }
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public TestObjectWithIndexesBuilder but() {
    TestObjectWithIndexesBuilder _builder = newTestObjectWithIndexesBuilder();
    _builder.m_featureCrossReferencesSet = m_featureCrossReferencesSet;
    _builder.m_crossReferences = m_crossReferences;
    _builder.m_featureCrossReferencesBuilder = m_featureCrossReferencesBuilder;
    _builder.m_featureEnumAttributeSet = m_featureEnumAttributeSet;
    _builder.m_enumAttribute = m_enumAttribute;
    _builder.m_featureFragmentedContentsSet = m_featureFragmentedContentsSet;
    _builder.m_fragmentedContents = m_fragmentedContents;
    _builder.m_featureFragmentedContentsBuilder = m_featureFragmentedContentsBuilder;
    _builder.m_featureIndexedContentsSet = m_featureIndexedContentsSet;
    _builder.m_indexedContents = m_indexedContents;
    _builder.m_featureIndexedContentsBuilder = m_featureIndexedContentsBuilder;
    _builder.m_featureIndexedReferencesSet = m_featureIndexedReferencesSet;
    _builder.m_indexedReferences = m_indexedReferences;
    _builder.m_featureIndexedReferencesBuilder = m_featureIndexedReferencesBuilder;
    _builder.m_featureNameSet = m_featureNameSet;
    _builder.m_name = m_name;
    _builder.m_featureRegularContentsSet = m_featureRegularContentsSet;
    _builder.m_regularContents = m_regularContents;
    _builder.m_featureRegularContentsBuilder = m_featureRegularContentsBuilder;
    return _builder;
  }

  /**
   * This method constructs the final de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes type.
   * @return new instance of the de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes type
   */
  public de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes build() {
    final de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes _newInstance = (de.hub.emffrag.testmodels.frag.testmodel.TestObjectWithIndexes) de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory.eINSTANCE
        .create(de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage.eINSTANCE.getTestObjectWithIndexes());
    if (m_featureEnumAttributeSet) {
      _newInstance.setEnumAttribute(m_enumAttribute);
    }
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
    if (m_featureIndexedContentsSet) {
      _newInstance.getIndexedContents().addAll(m_indexedContents);
    } else {
      if (!m_featureIndexedContentsBuilder.isEmpty()) {
        for (de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> builder : m_featureIndexedContentsBuilder) {
          _newInstance.getIndexedContents().add(builder.build());
        }
      }
    }
    if (m_featureIndexedReferencesSet) {
      _newInstance.getIndexedReferences().addAll(m_indexedReferences);
    } else {
      if (!m_featureIndexedReferencesBuilder.isEmpty()) {
        for (de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> builder : m_featureIndexedReferencesBuilder) {
          _newInstance.getIndexedReferences().add(builder.build());
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

  public TestObjectWithIndexesBuilder enumAttribute(de.hub.emffrag.testmodels.frag.testmodel.TestEnum p_enumAttribute) {
    m_enumAttribute = p_enumAttribute;
    m_featureEnumAttributeSet = true;
    return this;
  }

  public TestObjectWithIndexesBuilder name(java.lang.String p_name) {
    m_name = p_name;
    m_featureNameSet = true;
    return this;
  }

  public TestObjectWithIndexesBuilder crossReferences(de.hub.emffrag.testmodels.frag.testmodel.TestObject p_crossReferences) {
    m_crossReferences.add(p_crossReferences);
    m_featureCrossReferencesSet = true;
    return this;
  }

  public TestObjectWithIndexesBuilder crossReferences(java.util.Collection<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> p_crossReferences) {
    m_crossReferences.addAll(p_crossReferences);
    m_featureCrossReferencesSet = true;
    return this;
  }

  public TestObjectWithIndexesBuilder crossReferences(
      de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> p_testObjectBuilder) {
    m_featureCrossReferencesBuilder.add(p_testObjectBuilder);
    return this;
  }

  public TestObjectWithIndexesBuilder fragmentedContents(de.hub.emffrag.testmodels.frag.testmodel.TestObject p_fragmentedContents) {
    m_fragmentedContents.add(p_fragmentedContents);
    m_featureFragmentedContentsSet = true;
    return this;
  }

  public TestObjectWithIndexesBuilder fragmentedContents(java.util.Collection<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> p_fragmentedContents) {
    m_fragmentedContents.addAll(p_fragmentedContents);
    m_featureFragmentedContentsSet = true;
    return this;
  }

  public TestObjectWithIndexesBuilder fragmentedContents(
      de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> p_testObjectBuilder) {
    m_featureFragmentedContentsBuilder.add(p_testObjectBuilder);
    return this;
  }

  public TestObjectWithIndexesBuilder indexedContents(de.hub.emffrag.testmodels.frag.testmodel.TestObject p_indexedContents) {
    m_indexedContents.add(p_indexedContents);
    m_featureIndexedContentsSet = true;
    return this;
  }

  public TestObjectWithIndexesBuilder indexedContents(java.util.Collection<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> p_indexedContents) {
    m_indexedContents.addAll(p_indexedContents);
    m_featureIndexedContentsSet = true;
    return this;
  }

  public TestObjectWithIndexesBuilder indexedContents(
      de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> p_testObjectBuilder) {
    m_featureIndexedContentsBuilder.add(p_testObjectBuilder);
    return this;
  }

  public TestObjectWithIndexesBuilder indexedReferences(de.hub.emffrag.testmodels.frag.testmodel.TestObject p_indexedReferences) {
    m_indexedReferences.add(p_indexedReferences);
    m_featureIndexedReferencesSet = true;
    return this;
  }

  public TestObjectWithIndexesBuilder indexedReferences(java.util.Collection<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> p_indexedReferences) {
    m_indexedReferences.addAll(p_indexedReferences);
    m_featureIndexedReferencesSet = true;
    return this;
  }

  public TestObjectWithIndexesBuilder indexedReferences(
      de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> p_testObjectBuilder) {
    m_featureIndexedReferencesBuilder.add(p_testObjectBuilder);
    return this;
  }

  public TestObjectWithIndexesBuilder regularContents(de.hub.emffrag.testmodels.frag.testmodel.TestObject p_regularContents) {
    m_regularContents.add(p_regularContents);
    m_featureRegularContentsSet = true;
    return this;
  }

  public TestObjectWithIndexesBuilder regularContents(java.util.Collection<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> p_regularContents) {
    m_regularContents.addAll(p_regularContents);
    m_featureRegularContentsSet = true;
    return this;
  }

  public TestObjectWithIndexesBuilder regularContents(
      de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.TestObject> p_testObjectBuilder) {
    m_featureRegularContentsBuilder.add(p_testObjectBuilder);
    return this;
  }
}
