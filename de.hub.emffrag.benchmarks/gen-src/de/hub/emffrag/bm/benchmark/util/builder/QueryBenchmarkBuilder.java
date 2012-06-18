package de.hub.emffrag.bm.benchmark.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object ' <em><b>de.hub.emffrag.bm.benchmark.QueryBenchmark</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class QueryBenchmarkBuilder implements de.hub.emffrag.bm.benchmark.util.builder.IBenchmarkBuilder<de.hub.emffrag.bm.benchmark.QueryBenchmark> {
  // features and builders
  private Boolean m_frag2;
  private Integer m_loads;
  private Integer m_maxActiveFagments;
  private Integer m_maxWeakFragments;
  private java.lang.String m_pModelName;
  private java.lang.String m_pSourceXmiURI;
  private long m_rMemoryUsage;
  private Integer m_rNumberOfObjects;
  private long m_rTime;
  private java.lang.String m_remarks;
  private Integer m_unloads;
  private Integer m_weakLoads;
  private Integer m_weakUnloads;
  private Boolean m_withIndex;
  // helper attributes
  private boolean m_featureFrag2Set = false;
  private boolean m_featureLoadsSet = false;
  private boolean m_featureMaxActiveFagmentsSet = false;
  private boolean m_featureMaxWeakFragmentsSet = false;
  private boolean m_featurePModelNameSet = false;
  private boolean m_featurePSourceXmiURISet = false;
  private boolean m_featureRMemoryUsageSet = false;
  private boolean m_featureRNumberOfObjectsSet = false;
  private boolean m_featureRTimeSet = false;
  private boolean m_featureRemarksSet = false;
  private boolean m_featureUnloadsSet = false;
  private boolean m_featureWeakLoadsSet = false;
  private boolean m_featureWeakUnloadsSet = false;
  private boolean m_featureWithIndexSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newQueryBenchmarkBuilder()
   */
  private QueryBenchmarkBuilder() {
  }

  /**
   * This method creates a new instance of the QueryBenchmarkBuilder.
   * @return new instance of the QueryBenchmarkBuilder
   */
  public static QueryBenchmarkBuilder newQueryBenchmarkBuilder() {
    return new QueryBenchmarkBuilder();
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public QueryBenchmarkBuilder but() {
    QueryBenchmarkBuilder _builder = newQueryBenchmarkBuilder();
    _builder.m_featureFrag2Set = m_featureFrag2Set;
    _builder.m_frag2 = m_frag2;
    _builder.m_featureLoadsSet = m_featureLoadsSet;
    _builder.m_loads = m_loads;
    _builder.m_featureMaxActiveFagmentsSet = m_featureMaxActiveFagmentsSet;
    _builder.m_maxActiveFagments = m_maxActiveFagments;
    _builder.m_featureMaxWeakFragmentsSet = m_featureMaxWeakFragmentsSet;
    _builder.m_maxWeakFragments = m_maxWeakFragments;
    _builder.m_featurePModelNameSet = m_featurePModelNameSet;
    _builder.m_pModelName = m_pModelName;
    _builder.m_featurePSourceXmiURISet = m_featurePSourceXmiURISet;
    _builder.m_pSourceXmiURI = m_pSourceXmiURI;
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
    _builder.m_featureWithIndexSet = m_featureWithIndexSet;
    _builder.m_withIndex = m_withIndex;
    return _builder;
  }

  /**
   * This method constructs the final de.hub.emffrag.bm.benchmark.QueryBenchmark type.
   * @return new instance of the de.hub.emffrag.bm.benchmark.QueryBenchmark type
   */
  public de.hub.emffrag.bm.benchmark.QueryBenchmark build() {
    final de.hub.emffrag.bm.benchmark.QueryBenchmark _newInstance = de.hub.emffrag.bm.benchmark.BenchmarkFactory.eINSTANCE.createQueryBenchmark();
    if (m_featureFrag2Set) {
      _newInstance.setFrag2(m_frag2);
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
    if (m_featurePSourceXmiURISet) {
      _newInstance.setPSourceXmiURI(m_pSourceXmiURI);
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
    if (m_featureWithIndexSet) {
      _newInstance.setWithIndex(m_withIndex);
    }
    return _newInstance;
  }

  public QueryBenchmarkBuilder withFrag2(Boolean p_frag2) {
    m_frag2 = p_frag2;
    m_featureFrag2Set = true;
    return this;
  }

  public QueryBenchmarkBuilder withLoads(Integer p_loads) {
    m_loads = p_loads;
    m_featureLoadsSet = true;
    return this;
  }

  public QueryBenchmarkBuilder withMaxActiveFagments(Integer p_maxActiveFagments) {
    m_maxActiveFagments = p_maxActiveFagments;
    m_featureMaxActiveFagmentsSet = true;
    return this;
  }

  public QueryBenchmarkBuilder withMaxWeakFragments(Integer p_maxWeakFragments) {
    m_maxWeakFragments = p_maxWeakFragments;
    m_featureMaxWeakFragmentsSet = true;
    return this;
  }

  public QueryBenchmarkBuilder withPModelName(java.lang.String p_pModelName) {
    m_pModelName = p_pModelName;
    m_featurePModelNameSet = true;
    return this;
  }

  public QueryBenchmarkBuilder withPSourceXmiURI(java.lang.String p_pSourceXmiURI) {
    m_pSourceXmiURI = p_pSourceXmiURI;
    m_featurePSourceXmiURISet = true;
    return this;
  }

  public QueryBenchmarkBuilder withRMemoryUsage(long p_rMemoryUsage) {
    m_rMemoryUsage = p_rMemoryUsage;
    m_featureRMemoryUsageSet = true;
    return this;
  }

  public QueryBenchmarkBuilder withRNumberOfObjects(Integer p_rNumberOfObjects) {
    m_rNumberOfObjects = p_rNumberOfObjects;
    m_featureRNumberOfObjectsSet = true;
    return this;
  }

  public QueryBenchmarkBuilder withRTime(long p_rTime) {
    m_rTime = p_rTime;
    m_featureRTimeSet = true;
    return this;
  }

  public QueryBenchmarkBuilder withRemarks(java.lang.String p_remarks) {
    m_remarks = p_remarks;
    m_featureRemarksSet = true;
    return this;
  }

  public QueryBenchmarkBuilder withUnloads(Integer p_unloads) {
    m_unloads = p_unloads;
    m_featureUnloadsSet = true;
    return this;
  }

  public QueryBenchmarkBuilder withWeakLoads(Integer p_weakLoads) {
    m_weakLoads = p_weakLoads;
    m_featureWeakLoadsSet = true;
    return this;
  }

  public QueryBenchmarkBuilder withWeakUnloads(Integer p_weakUnloads) {
    m_weakUnloads = p_weakUnloads;
    m_featureWeakUnloadsSet = true;
    return this;
  }

  public QueryBenchmarkBuilder withWithIndex(Boolean p_withIndex) {
    m_withIndex = p_withIndex;
    m_featureWithIndexSet = true;
    return this;
  }
}
