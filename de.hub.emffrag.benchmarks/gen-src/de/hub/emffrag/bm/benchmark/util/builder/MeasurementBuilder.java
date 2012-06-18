package de.hub.emffrag.bm.benchmark.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object ' <em><b>de.hub.emffrag.bm.benchmark.Measurement</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class MeasurementBuilder implements de.hub.emffrag.bm.benchmark.util.builder.IBenchmarkBuilder<de.hub.emffrag.bm.benchmark.Measurement> {
  // features and builders
  private java.lang.String m_name;
  private Integer m_runs;
  private java.util.Collection<de.hub.emffrag.bm.benchmark.Benchmark> m_benchmarks = new java.util.LinkedList<de.hub.emffrag.bm.benchmark.Benchmark>();
  private java.util.Collection<de.hub.emffrag.bm.benchmark.util.builder.IBenchmarkBuilder<? extends de.hub.emffrag.bm.benchmark.Benchmark>> m_featureBenchmarksBuilder = new java.util.LinkedList<de.hub.emffrag.bm.benchmark.util.builder.IBenchmarkBuilder<? extends de.hub.emffrag.bm.benchmark.Benchmark>>();
  private java.util.Collection<de.hub.emffrag.bm.benchmark.EMFFragCandidates> m_candidates = new java.util.LinkedList<de.hub.emffrag.bm.benchmark.EMFFragCandidates>();
  private java.util.Collection<de.hub.emffrag.bm.benchmark.util.IConstraint> m_constraints = new java.util.LinkedList<de.hub.emffrag.bm.benchmark.util.IConstraint>();
  // helper attributes
  private boolean m_featureBenchmarksSet = false;
  private boolean m_featureCandidatesSet = false;
  private boolean m_featureConstraintsSet = false;
  private boolean m_featureNameSet = false;
  private boolean m_featureRunsSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newMeasurementBuilder()
   */
  private MeasurementBuilder() {
  }

  /**
   * This method creates a new instance of the MeasurementBuilder.
   * @return new instance of the MeasurementBuilder
   */
  public static MeasurementBuilder newMeasurementBuilder() {
    return new MeasurementBuilder();
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public MeasurementBuilder but() {
    MeasurementBuilder _builder = newMeasurementBuilder();
    _builder.m_featureBenchmarksSet = m_featureBenchmarksSet;
    _builder.m_benchmarks = m_benchmarks;
    _builder.m_featureBenchmarksBuilder = m_featureBenchmarksBuilder;
    _builder.m_featureCandidatesSet = m_featureCandidatesSet;
    _builder.m_candidates = m_candidates;
    _builder.m_featureConstraintsSet = m_featureConstraintsSet;
    _builder.m_constraints = m_constraints;
    _builder.m_featureNameSet = m_featureNameSet;
    _builder.m_name = m_name;
    _builder.m_featureRunsSet = m_featureRunsSet;
    _builder.m_runs = m_runs;
    return _builder;
  }

  /**
   * This method constructs the final de.hub.emffrag.bm.benchmark.Measurement type.
   * @return new instance of the de.hub.emffrag.bm.benchmark.Measurement type
   */
  public de.hub.emffrag.bm.benchmark.Measurement build() {
    final de.hub.emffrag.bm.benchmark.Measurement _newInstance = de.hub.emffrag.bm.benchmark.BenchmarkFactory.eINSTANCE.createMeasurement();
    if (m_featureNameSet) {
      _newInstance.setName(m_name);
    }
    if (m_featureRunsSet) {
      _newInstance.setRuns(m_runs);
    }
    if (m_featureBenchmarksSet) {
      _newInstance.getBenchmarks().addAll(m_benchmarks);
    } else {
      if (!m_featureBenchmarksBuilder.isEmpty()) {
        for (de.hub.emffrag.bm.benchmark.util.builder.IBenchmarkBuilder<? extends de.hub.emffrag.bm.benchmark.Benchmark> builder : m_featureBenchmarksBuilder) {
          _newInstance.getBenchmarks().add(builder.build());
        }
      }
    }
    if (m_featureCandidatesSet) {
      _newInstance.getCandidates().addAll(m_candidates);
    }
    if (m_featureConstraintsSet) {
      _newInstance.getConstraints().addAll(m_constraints);
    }
    return _newInstance;
  }

  public MeasurementBuilder withName(java.lang.String p_name) {
    m_name = p_name;
    m_featureNameSet = true;
    return this;
  }

  public MeasurementBuilder withRuns(Integer p_runs) {
    m_runs = p_runs;
    m_featureRunsSet = true;
    return this;
  }

  public MeasurementBuilder withBenchmarks(de.hub.emffrag.bm.benchmark.Benchmark p_benchmarks) {
    m_benchmarks.add(p_benchmarks);
    m_featureBenchmarksSet = true;
    return this;
  }

  public MeasurementBuilder withBenchmarks(java.util.Collection<? extends de.hub.emffrag.bm.benchmark.Benchmark> p_benchmarks) {
    m_benchmarks.addAll(p_benchmarks);
    m_featureBenchmarksSet = true;
    return this;
  }

  public MeasurementBuilder withBenchmarks(de.hub.emffrag.bm.benchmark.util.builder.IBenchmarkBuilder<? extends de.hub.emffrag.bm.benchmark.Benchmark> p_benchmarkBuilder) {
    m_featureBenchmarksBuilder.add(p_benchmarkBuilder);
    return this;
  }

  public MeasurementBuilder withCandidates(de.hub.emffrag.bm.benchmark.EMFFragCandidates p_candidates) {
    m_candidates.add(p_candidates);
    m_featureCandidatesSet = true;
    return this;
  }

  public MeasurementBuilder withCandidates(java.util.Collection<? extends de.hub.emffrag.bm.benchmark.EMFFragCandidates> p_candidates) {
    m_candidates.addAll(p_candidates);
    m_featureCandidatesSet = true;
    return this;
  }

  public MeasurementBuilder withConstraints(de.hub.emffrag.bm.benchmark.util.IConstraint p_constraints) {
    m_constraints.add(p_constraints);
    m_featureConstraintsSet = true;
    return this;
  }

  public MeasurementBuilder withConstraints(java.util.Collection<? extends de.hub.emffrag.bm.benchmark.util.IConstraint> p_constraints) {
    m_constraints.addAll(p_constraints);
    m_featureConstraintsSet = true;
    return this;
  }
}
