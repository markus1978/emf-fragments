package de.hub.emffrag.testmodels.frag.testmodel.util.builder;

/**
 * <!-- begin-user-doc --> 
 *   A builder for the model object '<em><b>de.hub.emffrag.testmodels.frag.testmodel.Container</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ContainerBuilder implements de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<de.hub.emffrag.testmodels.frag.testmodel.Container> {
  // features and builders
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.Contents> m_contents = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.Contents>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents>> m_featureContentsBuilder = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents>>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.Contents> m_fragmentedContents = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.Contents>();
  private java.util.Collection<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents>> m_featureFragmentedContentsBuilder = new java.util.LinkedList<de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents>>();
  // helper attributes
  private boolean m_featureContentsSet = false;
  private boolean m_featureFragmentedContentsSet = false;

  /**
   * Builder is not instantiated with a constructor.
   * @see #newContainerBuilder()
   */
  private ContainerBuilder() {
  }

  /**
   * This method creates a new instance of the ContainerBuilder.
   * @return new instance of the ContainerBuilder
   */
  public static ContainerBuilder newContainerBuilder() {
    return new ContainerBuilder();
  }

  /**
   * This method creates a new instance of the ContainerBuilder. 
   * The builder is initialized using an existing '<em><b>de.hub.emffrag.testmodels.frag.testmodel.Container</b></em>' model object.
   * In order to avoid changes to the provided '<em><b>de.hub.emffrag.testmodels.frag.testmodel.Container</b></em>' model object, a copy is created using <em><b>org.eclipse.emf.ecore.util.EcoreUtil.Copier</b></em>.
   * @param container The existing '<em><b>de.hub.emffrag.testmodels.frag.testmodel.Container</b></em>' model object to be used for the initialization of the builder  
   * @return new initialized instance of the ContainerBuilder
   */
  public static ContainerBuilder newContainerBuilder(de.hub.emffrag.testmodels.frag.testmodel.Container p_container) {
    org.eclipse.emf.ecore.util.EcoreUtil.Copier c = new org.eclipse.emf.ecore.util.EcoreUtil.Copier();
    de.hub.emffrag.testmodels.frag.testmodel.Container _container = (de.hub.emffrag.testmodels.frag.testmodel.Container) c.copy(((de.hub.emffrag.testmodels.frag.testmodel.Container) p_container));
    c.copyReferences();
    ContainerBuilder _builder = newContainerBuilder();
    if (_container.getContents() != null) {
      _builder.contents(_container.getContents());
    }
    if (_container.getFragmentedContents() != null) {
      _builder.fragmentedContents(_container.getFragmentedContents());
    }
    return _builder;
  }

  /**
   * This method can be used to override attributes of the builder. It constructs a new builder and copies the current values to it.
   */
  public ContainerBuilder but() {
    ContainerBuilder _builder = newContainerBuilder();
    _builder.m_featureContentsSet = m_featureContentsSet;
    _builder.m_contents = m_contents;
    _builder.m_featureContentsBuilder = m_featureContentsBuilder;
    _builder.m_featureFragmentedContentsSet = m_featureFragmentedContentsSet;
    _builder.m_fragmentedContents = m_fragmentedContents;
    _builder.m_featureFragmentedContentsBuilder = m_featureFragmentedContentsBuilder;
    return _builder;
  }

  /**
   * This method constructs the final de.hub.emffrag.testmodels.frag.testmodel.Container type.
   * @return new instance of the de.hub.emffrag.testmodels.frag.testmodel.Container type
   */
  public de.hub.emffrag.testmodels.frag.testmodel.Container build() {
    final de.hub.emffrag.testmodels.frag.testmodel.Container _newInstance = (de.hub.emffrag.testmodels.frag.testmodel.Container) de.hub.emffrag.testmodels.frag.testmodel.TestModelFactory.eINSTANCE
        .create(de.hub.emffrag.testmodels.frag.testmodel.TestModelPackage.eINSTANCE.getContainer());
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

  public ContainerBuilder contents(de.hub.emffrag.testmodels.frag.testmodel.Contents p_contents) {
    m_contents.add(p_contents);
    m_featureContentsSet = true;
    return this;
  }

  public ContainerBuilder contents(java.util.Collection<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents> p_contents) {
    m_contents.addAll(p_contents);
    m_featureContentsSet = true;
    return this;
  }

  public ContainerBuilder contents(de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents> p_contentsBuilder) {
    m_featureContentsBuilder.add(p_contentsBuilder);
    return this;
  }

  public ContainerBuilder fragmentedContents(de.hub.emffrag.testmodels.frag.testmodel.Contents p_fragmentedContents) {
    m_fragmentedContents.add(p_fragmentedContents);
    m_featureFragmentedContentsSet = true;
    return this;
  }

  public ContainerBuilder fragmentedContents(java.util.Collection<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents> p_fragmentedContents) {
    m_fragmentedContents.addAll(p_fragmentedContents);
    m_featureFragmentedContentsSet = true;
    return this;
  }

  public ContainerBuilder fragmentedContents(de.hub.emffrag.testmodels.frag.testmodel.util.builder.ITestmodelBuilder<? extends de.hub.emffrag.testmodels.frag.testmodel.Contents> p_contentsBuilder) {
    m_featureFragmentedContentsBuilder.add(p_contentsBuilder);
    return this;
  }
}
