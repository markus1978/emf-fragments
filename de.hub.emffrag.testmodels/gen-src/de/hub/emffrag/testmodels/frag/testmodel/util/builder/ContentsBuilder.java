package de.hub.emffrag.testmodels.frag.testmodel.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>de.hub.emffrag.testmodels.frag.testmodel.Contents</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ContentsBuilder implements de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<de.hub.emffrag.testmodels.frag.testmodel.Contents> {
  // features and builders
  private java.lang.String m_value;
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.Contents> m_contents = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.Contents>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents>> m_featureContentsBuilder = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents>>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.Contents> m_fragmentedContents = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.Contents>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents>> m_featureFragmentedContentsBuilder = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents>>();
  // helper attributes
  private boolean m_featureContentsSet = false;
  private boolean m_featureFragmentedContentsSet = false;
  private boolean m_featureValueSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newContentsBuilder()
   */
  private ContentsBuilder() {
  }

  /**
   * This method creates a new instance of the ContentsBuilder.
   * @return new instance of the ContentsBuilder
   */
  public static ContentsBuilder newContentsBuilder() {
    return new ContentsBuilder();
  }

  /**
   * This method creates a new instance of the ContentsBuilder. 
   * The builder is initialized using an existing '<em><b>de.hub.emffrag.testmodels.frag.testmodel.Contents</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>de.hub.emffrag.testmodels.frag.testmodel.Contents</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param contents The existing '<em><b>de.hub.emffrag.testmodels.frag.testmodel.Contents</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the ContentsBuilder
   */
  public static ContentsBuilder newContentsBuilder(de.hub.emffrag.testmodels.frag.testmodel.Contents p_contents) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    de.hub.emffrag.testmodels.frag.testmodel.Contents _contents = (de.hub.emffrag.testmodels.frag.testmodel.Contents) c.copy(((de.hub.emffrag.testmodels.frag.testmodel.Contents) p_contents));
    c.copyReferences();
    ContentsBuilder _builder = newContentsBuilder();
    _builder.value(_contents.getValue());
    if (_contents.getContents() != null) {
      _builder.contents(_contents.getContents());
    }
    if (_contents.getFragmentedContents() != null) {
      _builder.fragmentedContents(_contents.getFragmentedContents());
    }
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public ContentsBuilder but() {
    ContentsBuilder _builder = newContentsBuilder();
    _builder.m_featureContentsSet = m_featureContentsSet;
    _builder.m_contents = m_contents;
    _builder.m_featureContentsBuilder = m_featureContentsBuilder;
    _builder.m_featureFragmentedContentsSet = m_featureFragmentedContentsSet;
    _builder.m_fragmentedContents = m_fragmentedContents;
    _builder.m_featureFragmentedContentsBuilder = m_featureFragmentedContentsBuilder;
    _builder.m_featureValueSet = m_featureValueSet;
    _builder.m_value = m_value;
    return _builder;
  }

  /**
   * This method constructs the final de.hub.emffrag.testmodels.frag.testmodel.Contents type.
   * @return new instance of the de.hub.emffrag.testmodels.frag.testmodel.Contents type
   */
  public de.hub.emffrag.testmodels.frag.testmodel.Contents build() {
    final de.hub.emffrag.testmodels.frag.testmodel.Contents _newInstance = (de.hub.emffrag.testmodels.frag.testmodel.Contents) de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory.eINSTANCE
        .create(de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage.eINSTANCE.getContents());
    if (m_featureValueSet) {
      _newInstance.setValue(m_value);
    }
    if (m_featureContentsSet) {
      _newInstance.getContents().addAll(m_contents);
    } else {
      if (!m_featureContentsBuilder.isEmpty()) {
        for (de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents> builder : m_featureContentsBuilder) {
          _newInstance.getContents().add(builder.build());
        }
      }
    }
    if (m_featureFragmentedContentsSet) {
      _newInstance.getFragmentedContents().addAll(m_fragmentedContents);
    } else {
      if (!m_featureFragmentedContentsBuilder.isEmpty()) {
        for (de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents> builder : m_featureFragmentedContentsBuilder) {
          _newInstance.getFragmentedContents().add(builder.build());
        }
      }
    }
    return _newInstance;
  }

  public ContentsBuilder value(java.lang.String p_value) {
    m_value = p_value;
    m_featureValueSet = true;
    return this;
  }

  public ContentsBuilder contents(de.hub.emffrag.testmodels.frag.testmodel.Contents p_contents) {
    m_contents.add(p_contents);
    m_featureContentsSet = true;
    return this;
  }

  public ContentsBuilder contents(java.util.Collection<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents> p_contents) {
    m_contents.addAll(p_contents);
    m_featureContentsSet = true;
    return this;
  }

  public ContentsBuilder contents(de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents> p_contentsBuilder) {
    m_featureContentsBuilder.add(p_contentsBuilder);
    return this;
  }

  public ContentsBuilder fragmentedContents(de.hub.emffrag.testmodels.frag.testmodel.Contents p_fragmentedContents) {
    m_fragmentedContents.add(p_fragmentedContents);
    m_featureFragmentedContentsSet = true;
    return this;
  }

  public ContentsBuilder fragmentedContents(java.util.Collection<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents> p_fragmentedContents) {
    m_fragmentedContents.addAll(p_fragmentedContents);
    m_featureFragmentedContentsSet = true;
    return this;
  }

  public ContentsBuilder fragmentedContents(de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents> p_contentsBuilder) {
    m_featureFragmentedContentsBuilder.add(p_contentsBuilder);
    return this;
  }
}
