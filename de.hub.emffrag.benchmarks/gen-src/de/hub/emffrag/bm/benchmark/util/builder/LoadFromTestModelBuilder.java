package de.hub.emffrag.bm.benchmark.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object ' <em><b>de.hub.emffrag.bm.benchmark.LoadFromTestModel</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class LoadFromTestModelBuilder implements de.hub.emffrag.bm.benchmark.util.builder.IBenchmarkBuilder<de.hub.emffrag.bm.benchmark.LoadFromTestModel> {
  // features and builders
  private de.hub.emffrag.bm.benchmark.ImportTestModel m_importBenchmark;
  private de.hub.emffrag.bm.benchmark.util.builder.IBenchmarkBuilder<? extends de.hub.emffrag.bm.benchmark.ImportTestModel> m_featureImportBenchmarkBuilder;
  private Integer m_loadSize;
  private Integer m_loads;
  private Integer m_maxActiveFagments;
  private Integer m_maxWeakFragments;
  private java.lang.String m_pModelName;
  private long m_rMemoryUsage;
  private Integer m_rNumberOfObjects;
  private long m_rTime;
  private java.lang.String m_remarks;
  private Integer m_unloads;
  private Integer m_weakLoads;
  private Integer m_weakUnloads;
  // helper attributes
  private boolean m_featureImportBenchmarkSet = false;
  private boolean m_featureLoadSizeSet = false;
  private boolean m_featureLoadsSet = false;
  private boolean m_featureMaxActiveFagmentsSet = false;
  private boolean m_featureMaxWeakFragmentsSet = false;
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
   * @see #newLoadFromTestModelBuilder()
   */
  private LoadFromTestModelBuilder() {
  }

  /**
   * This method creates a new instance of the LoadFromTestModelBuilder.
   * @return new instance of the LoadFromTestModelBuilder
   */
  public static LoadFromTestModelBuilder newLoadFromTestModelBuilder() {
    return new LoadFromTestModelBuilder();
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public LoadFromTestModelBuilder but() {
    LoadFromTestModelBuilder _builder = newLoadFromTestModelBuilder();
    _builder.m_featureImportBenchmarkSet = m_featureImportBenchmarkSet;
    _builder.m_importBenchmark = m_importBenchmark;
    _builder.m_featureImportBenchmarkBuilder = m_featureImportBenchmarkBuilder;
    _builder.m_featureLoadSizeSet = m_featureLoadSizeSet;
    _builder.m_loadSize = m_loadSize;
    _builder.m_featureLoadsSet = m_featureLoadsSet;
    _builder.m_loads = m_loads;
    _builder.m_featureMaxActiveFagmentsSet = m_featureMaxActiveFagmentsSet;
    _builder.m_maxActiveFagments = m_maxActiveFagments;
    _builder.m_featureMaxWeakFragmentsSet = m_featureMaxWeakFragmentsSet;
    _builder.m_maxWeakFragments = m_maxWeakFragments;
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
   * This method constructs the final de.hub.emffrag.bm.benchmark.LoadFromTestModel type.
   * @return new instance of the de.hub.emffrag.bm.benchmark.LoadFromTestModel type
   */
  public de.hub.emffrag.bm.benchmark.LoadFromTestModel build() {
    final de.hub.emffrag.bm.benchmark.LoadFromTestModel _newInstance = de.hub.emffrag.bm.benchmark.BenchmarkFactory.eINSTANCE.createLoadFromTestModel();
    if (m_featureImportBenchmarkSet) {
      _newInstance.setImportBenchmark(m_importBenchmark);
    } else {
      if (m_featureImportBenchmarkBuilder != null) {
        _newInstance.setImportBenchmark(m_featureImportBenchmarkBuilder.build());
      }
    }
    if (m_featureLoadSizeSet) {
      _newInstance.setLoadSize(m_loadSize);
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

  public LoadFromTestModelBuilder withImportBenchmark(de.hub.emffrag.bm.benchmark.ImportTestModel p_importBenchmark) {
    m_importBenchmark = p_importBenchmark;
    m_featureImportBenchmarkSet = true;
    return this;
  }

  public LoadFromTestModelBuilder withImportBenchmark(de.hub.emffrag.bm.benchmark.util.builder.IBenchmarkBuilder<? extends de.hub.emffrag.bm.benchmark.ImportTestModel> p_importTestModelBuilder) {
    m_featureImportBenchmarkBuilder = p_importTestModelBuilder;
    return this;
  }

  public LoadFromTestModelBuilder withLoadSize(Integer p_loadSize) {
    m_loadSize = p_loadSize;
    m_featureLoadSizeSet = true;
    return this;
  }

  public LoadFromTestModelBuilder withLoads(Integer p_loads) {
    m_loads = p_loads;
    m_featureLoadsSet = true;
    return this;
  }

  public LoadFromTestModelBuilder withMaxActiveFagments(Integer p_maxActiveFagments) {
    m_maxActiveFagments = p_maxActiveFagments;
    m_featureMaxActiveFagmentsSet = true;
    return this;
  }

  public LoadFromTestModelBuilder withMaxWeakFragments(Integer p_maxWeakFragments) {
    m_maxWeakFragments = p_maxWeakFragments;
    m_featureMaxWeakFragmentsSet = true;
    return this;
  }

  public LoadFromTestModelBuilder withPModelName(java.lang.String p_pModelName) {
    m_pModelName = p_pModelName;
    m_featurePModelNameSet = true;
    return this;
  }

  public LoadFromTestModelBuilder withRMemoryUsage(long p_rMemoryUsage) {
    m_rMemoryUsage = p_rMemoryUsage;
    m_featureRMemoryUsageSet = true;
    return this;
  }

  public LoadFromTestModelBuilder withRNumberOfObjects(Integer p_rNumberOfObjects) {
    m_rNumberOfObjects = p_rNumberOfObjects;
    m_featureRNumberOfObjectsSet = true;
    return this;
  }

  public LoadFromTestModelBuilder withRTime(long p_rTime) {
    m_rTime = p_rTime;
    m_featureRTimeSet = true;
    return this;
  }

  public LoadFromTestModelBuilder withRemarks(java.lang.String p_remarks) {
    m_remarks = p_remarks;
    m_featureRemarksSet = true;
    return this;
  }

  public LoadFromTestModelBuilder withUnloads(Integer p_unloads) {
    m_unloads = p_unloads;
    m_featureUnloadsSet = true;
    return this;
  }

  public LoadFromTestModelBuilder withWeakLoads(Integer p_weakLoads) {
    m_weakLoads = p_weakLoads;
    m_featureWeakLoadsSet = true;
    return this;
  }

  public LoadFromTestModelBuilder withWeakUnloads(Integer p_weakUnloads) {
    m_weakUnloads = p_weakUnloads;
    m_featureWeakUnloadsSet = true;
    return this;
  }
}
