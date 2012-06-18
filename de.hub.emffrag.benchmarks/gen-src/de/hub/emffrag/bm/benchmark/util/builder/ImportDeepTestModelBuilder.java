package de.hub.emffrag.bm.benchmark.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object ' <em><b>de.hub.emffrag.bm.benchmark.ImportDeepTestModel</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ImportDeepTestModelBuilder implements de.hub.emffrag.bm.benchmark.util.builder.IBenchmarkBuilder<de.hub.emffrag.bm.benchmark.ImportDeepTestModel> {
  // features and builders
  private Integer m_base;
  private Integer m_fragmentSize;
  private Integer m_loads;
  private Integer m_maxActiveFagments;
  private Integer m_maxWeakFragments;
  private Integer m_modelSize;
  private java.lang.String m_pModelName;
  private long m_rMemoryUsage;
  private Integer m_rNumberOfObjects;
  private long m_rTime;
  private java.lang.String m_remarks;
  private Integer m_unloads;
  private Integer m_weakLoads;
  private Integer m_weakUnloads;
  // helper attributes
  private boolean m_featureBaseSet = false;
  private boolean m_featureFragmentSizeSet = false;
  private boolean m_featureLoadsSet = false;
  private boolean m_featureMaxActiveFagmentsSet = false;
  private boolean m_featureMaxWeakFragmentsSet = false;
  private boolean m_featureModelSizeSet = false;
  private boolean m_featurePModelNameSet = false;
  private boolean m_featureRMemoryUsageSet = false;
  private boolean m_featureRNumberOfObjectsSet = false;
  private boolean m_featureRTimeSet = false;
  private boolean m_featureRemarksSet = false;
  private boolean m_featureUnloadsSet = false;
  private boolean m_featureWeakLoadsSet = false;
  private boolean m_featureWeakUnloadsSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newImportDeepTestModelBuilder()
   */
  private ImportDeepTestModelBuilder() {
  }

  /**
   * This method creates a new instance of the ImportDeepTestModelBuilder.
   * @return new instance of the ImportDeepTestModelBuilder
   */
  public static ImportDeepTestModelBuilder newImportDeepTestModelBuilder() {
    return new ImportDeepTestModelBuilder();
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public ImportDeepTestModelBuilder but() {
    ImportDeepTestModelBuilder _builder = newImportDeepTestModelBuilder();
    _builder.m_featureBaseSet = m_featureBaseSet;
    _builder.m_base = m_base;
    _builder.m_featureFragmentSizeSet = m_featureFragmentSizeSet;
    _builder.m_fragmentSize = m_fragmentSize;
    _builder.m_featureLoadsSet = m_featureLoadsSet;
    _builder.m_loads = m_loads;
    _builder.m_featureMaxActiveFagmentsSet = m_featureMaxActiveFagmentsSet;
    _builder.m_maxActiveFagments = m_maxActiveFagments;
    _builder.m_featureMaxWeakFragmentsSet = m_featureMaxWeakFragmentsSet;
    _builder.m_maxWeakFragments = m_maxWeakFragments;
    _builder.m_featureModelSizeSet = m_featureModelSizeSet;
    _builder.m_modelSize = m_modelSize;
    _builder.m_featurePModelNameSet = m_featurePModelNameSet;
    _builder.m_pModelName = m_pModelName;
    _builder.m_featureRMemoryUsageSet = m_featureRMemoryUsageSet;
    _builder.m_rMemoryUsage = m_rMemoryUsage;
    _builder.m_featureRNumberOfObjectsSet = m_featureRNumberOfObjectsSet;
    _builder.m_rNumberOfObjects = m_rNumberOfObjects;
    _builder.m_featureRTimeSet = m_featureRTimeSet;
    _builder.m_rTime = m_rTime;
    _builder.m_featureRemarksSet = m_featureRemarksSet;
    _builder.m_remarks = m_remarks;
    _builder.m_featureUnloadsSet = m_featureUnloadsSet;
    _builder.m_unloads = m_unloads;
    _builder.m_featureWeakLoadsSet = m_featureWeakLoadsSet;
    _builder.m_weakLoads = m_weakLoads;
    _builder.m_featureWeakUnloadsSet = m_featureWeakUnloadsSet;
    _builder.m_weakUnloads = m_weakUnloads;
    return _builder;
  }

  /**
   * This method constructs the final de.hub.emffrag.bm.benchmark.ImportDeepTestModel type.
   * @return new instance of the de.hub.emffrag.bm.benchmark.ImportDeepTestModel type
   */
  public de.hub.emffrag.bm.benchmark.ImportDeepTestModel build() {
    final de.hub.emffrag.bm.benchmark.ImportDeepTestModel _newInstance = de.hub.emffrag.bm.benchmark.BenchmarkFactory.eINSTANCE.createImportDeepTestModel();
    if (m_featureBaseSet) {
      _newInstance.setBase(m_base);
    }
    if (m_featureFragmentSizeSet) {
      _newInstance.setFragmentSize(m_fragmentSize);
    }
    if (m_featureLoadsSet) {
      _newInstance.setLoads(m_loads);
    }
    if (m_featureMaxActiveFagmentsSet) {
      _newInstance.setMaxActiveFagments(m_maxActiveFagments);
    }
    if (m_featureMaxWeakFragmentsSet) {
      _newInstance.setMaxWeakFragments(m_maxWeakFragments);
    }
    if (m_featureModelSizeSet) {
      _newInstance.setModelSize(m_modelSize);
    }
    if (m_featurePModelNameSet) {
      _newInstance.setPModelName(m_pModelName);
    }
    if (m_featureRMemoryUsageSet) {
      _newInstance.setRMemoryUsage(m_rMemoryUsage);
    }
    if (m_featureRNumberOfObjectsSet) {
      _newInstance.setRNumberOfObjects(m_rNumberOfObjects);
    }
    if (m_featureRTimeSet) {
      _newInstance.setRTime(m_rTime);
    }
    if (m_featureRemarksSet) {
      _newInstance.setRemarks(m_remarks);
    }
    if (m_featureUnloadsSet) {
      _newInstance.setUnloads(m_unloads);
    }
    if (m_featureWeakLoadsSet) {
      _newInstance.setWeakLoads(m_weakLoads);
    }
    if (m_featureWeakUnloadsSet) {
      _newInstance.setWeakUnloads(m_weakUnloads);
    }
    return _newInstance;
  }

  public ImportDeepTestModelBuilder withBase(Integer p_base) {
    m_base = p_base;
    m_featureBaseSet = true;
    return this;
  }

  public ImportDeepTestModelBuilder withFragmentSize(Integer p_fragmentSize) {
    m_fragmentSize = p_fragmentSize;
    m_featureFragmentSizeSet = true;
    return this;
  }

  public ImportDeepTestModelBuilder withLoads(Integer p_loads) {
    m_loads = p_loads;
    m_featureLoadsSet = true;
    return this;
  }

  public ImportDeepTestModelBuilder withMaxActiveFagments(Integer p_maxActiveFagments) {
    m_maxActiveFagments = p_maxActiveFagments;
    m_featureMaxActiveFagmentsSet = true;
    return this;
  }

  public ImportDeepTestModelBuilder withMaxWeakFragments(Integer p_maxWeakFragments) {
    m_maxWeakFragments = p_maxWeakFragments;
    m_featureMaxWeakFragmentsSet = true;
    return this;
  }

  public ImportDeepTestModelBuilder withModelSize(Integer p_modelSize) {
    m_modelSize = p_modelSize;
    m_featureModelSizeSet = true;
    return this;
  }

  public ImportDeepTestModelBuilder withPModelName(java.lang.String p_pModelName) {
    m_pModelName = p_pModelName;
    m_featurePModelNameSet = true;
    return this;
  }

  public ImportDeepTestModelBuilder withRMemoryUsage(long p_rMemoryUsage) {
    m_rMemoryUsage = p_rMemoryUsage;
    m_featureRMemoryUsageSet = true;
    return this;
  }

  public ImportDeepTestModelBuilder withRNumberOfObjects(Integer p_rNumberOfObjects) {
    m_rNumberOfObjects = p_rNumberOfObjects;
    m_featureRNumberOfObjectsSet = true;
    return this;
  }

  public ImportDeepTestModelBuilder withRTime(long p_rTime) {
    m_rTime = p_rTime;
    m_featureRTimeSet = true;
    return this;
  }

  public ImportDeepTestModelBuilder withRemarks(java.lang.String p_remarks) {
    m_remarks = p_remarks;
    m_featureRemarksSet = true;
    return this;
  }

  public ImportDeepTestModelBuilder withUnloads(Integer p_unloads) {
    m_unloads = p_unloads;
    m_featureUnloadsSet = true;
    return this;
  }

  public ImportDeepTestModelBuilder withWeakLoads(Integer p_weakLoads) {
    m_weakLoads = p_weakLoads;
    m_featureWeakLoadsSet = true;
    return this;
  }

  public ImportDeepTestModelBuilder withWeakUnloads(Integer p_weakUnloads) {
    m_weakUnloads = p_weakUnloads;
    m_featureWeakUnloadsSet = true;
    return this;
  }
}
