package de.hub.emffrag.tests;

import de.hub.emffrag.tests.model.Container;
import de.hub.emffrag.tests.model.Contents;
import de.hub.emffrag.tests.model.TestModelFactory;
import de.hub.emffrag.tests.model.TestModelPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class EStoreTests {
  private Contents contents(final String name) {
    final Contents obj = TestModelFactory.eINSTANCE.createContents();
    obj.setName("Hello");
    return obj;
  }
  
  private Container container(final String name) {
    final Container obj = TestModelFactory.eINSTANCE.createContainer();
    obj.setName("Hello");
    return obj;
  }
  
  @Test
  public void simpleTest() {
    final Contents contents = TestModelFactory.eINSTANCE.createContents();
    contents.setName("Hello");
    String _name = contents.getName();
    Assert.assertSame("Hello", _name);
    EObject _eContainer = contents.eContainer();
    Assert.assertNull(_eContainer);
  }
  
  @Test
  public void manyContainmentTest() {
    final Contents contents = this.contents("contents");
    final Container container = this.container("container");
    EList<Contents> _contents = container.getContents();
    _contents.add(contents);
    EObject _eContainer = contents.eContainer();
    Assert.assertNotNull(_eContainer);
    EObject _eContainer_1 = contents.eContainer();
    Assert.assertSame(container, _eContainer_1);
    EReference _container_Contents = TestModelPackage.eINSTANCE.getContainer_Contents();
    EStructuralFeature _eContainingFeature = contents.eContainingFeature();
    Assert.assertSame(_container_Contents, _eContainingFeature);
  }
  
  @Test
  public void singleContainmentTest() {
    final Contents contents = this.contents("contents");
    final Container container = this.container("container");
    container.setContent(contents);
    EObject _eContainer = contents.eContainer();
    Assert.assertNotNull(_eContainer);
    EObject _eContainer_1 = contents.eContainer();
    Assert.assertSame(container, _eContainer_1);
    EReference _container_Content = TestModelPackage.eINSTANCE.getContainer_Content();
    EStructuralFeature _eContainingFeature = contents.eContainingFeature();
    Assert.assertSame(_container_Content, _eContainingFeature);
    final Contents newContents = this.contents("new contents");
    container.setContent(newContents);
    EObject _eContainer_2 = contents.eContainer();
    Assert.assertNull(_eContainer_2);
    EStructuralFeature _eContainingFeature_1 = contents.eContainingFeature();
    Assert.assertNull(_eContainingFeature_1);
    EObject _eContainer_3 = newContents.eContainer();
    Assert.assertNotNull(_eContainer_3);
    EObject _eContainer_4 = newContents.eContainer();
    Assert.assertSame(container, _eContainer_4);
    EReference _container_Content_1 = TestModelPackage.eINSTANCE.getContainer_Content();
    EStructuralFeature _eContainingFeature_2 = newContents.eContainingFeature();
    Assert.assertSame(_container_Content_1, _eContainingFeature_2);
  }
}
