package de.hub.emffrag.benchmarks.reference1;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import de.hub.emffrag.benchmarks.reference1.ReferenceRepresentationBenchmark.ReferenceRepresentationBenchmarkConfiguration;
import de.hub.emffrag.testmodels.testmodel.TestModelFactory;
import de.hub.emffrag.testmodels.testmodel.TestObjectWithIndexes;

public class ReferenceRepresentationBenchmarkConfigurationImpl implements ReferenceRepresentationBenchmarkConfiguration {
	
	private final TestModelFactory factory;
	private final EReference reference;
	


	public ReferenceRepresentationBenchmarkConfigurationImpl(TestModelFactory factory, EReference reference) {
		super();
		this.factory = factory;
		this.reference = reference;
	}

	@Override
	public EObject create() {
		return factory.createTestObjectWithIndexes();
	}

	@Override
	public void add(EObject owner, EObject child, boolean containment, boolean fragmenting) {
		if (containment) {
			if (fragmenting) {
				((TestObjectWithIndexes)owner).getFragmentedContents().add((TestObjectWithIndexes)child);
			} else {
				((TestObjectWithIndexes)owner).getRegularContents().add((TestObjectWithIndexes)child);					
			}
		} else {
			addReference(owner, child);
		}
	}			

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addReference(EObject owner, EObject child) {
		((EList)owner.eGet(reference)).add(child);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Iterable<EObject> iterate(EObject owner) {
		return (List)owner.eGet(reference);
	}
}