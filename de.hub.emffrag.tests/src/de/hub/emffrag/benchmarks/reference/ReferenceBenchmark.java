package de.hub.emffrag.benchmarks.reference;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;

import de.hub.emffrag.EmfFragActivator;
import de.hub.emffrag.fragmentation.AbstractFragmentationTests;
import de.hub.emffrag.fragmentation.IndexBasedIdSemantics.IdBehaviour;
import de.hub.emffrag.fragmentation.NoReferencesIdSemantics;
import de.hub.emffrag.testmodels.testmodel.TestObjectWithIndexes;

public class ReferenceBenchmark extends AbstractFragmentationTests implements IBenchmark<ReferenceBenchmark.Parameters> {
	
	public enum Command { importModel, runTask };
	
	public static class Parameters implements IParameters {
		public final Command command;
		public final long references;
		public final long accessReferences;
		
		public Parameters(Command command, long references, long accessReferences) {
			super();
			this.command = command;
			this.references = references;
			this.accessReferences = accessReferences;
		}
		
		public String getModelName() {
			return "referenceBenchmark" + references;
		}

		@Override
		public String toString() {
			return command + "," + references + "," + accessReferences;
		}		
	}
	
	public EReference reference = de.hub.emffrag.testmodels.testmodel.TestModelPackage.eINSTANCE.getTestObject_CrossReferences();
	public de.hub.emffrag.testmodels.testmodel.TestModelFactory factory = de.hub.emffrag.testmodels.testmodel.TestModelFactory.eINSTANCE;
	
	protected Parameters parameters = null;
	
	@Override
	protected boolean doInitializeModel() {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void runTask(Parameters parameters, IGauge gauge) {
		this.parameters = parameters;
		
		setUp();
		EmfFragActivator.instance.idSemantics = new NoReferencesIdSemantics(IdBehaviour.defaultModel);
		EmfFragActivator.instance.cacheSize = 1000;
		
		resetErrorsAndWarnings();
		registerPackages();
		standardInitialization();
		
		gauge.onStart();
		model = createFragmentedModel(dataStore, metaModel);
		
		EmfFragActivator.instance.defaultModel = model;
		
		if (parameters.command == Command.importModel) {
			TestObjectWithIndexes o1 = factory.createTestObjectWithIndexes();
			TestObjectWithIndexes o2 = factory.createTestObjectWithIndexes();
			model.getContents().add(o1);
			model.getContents().add(o2);
			o1.setName("o1fsfdsdfdfdfgdfgdgfdfgdfgdfgdfgdfgdfgdfgdfgdfg");
			o2.setName("o2");
			for (long i = 0; i < parameters.references; i++) {
				((EList<TestObjectWithIndexes>)o1.eGet(reference)).add(o2);
			}			
			model.save(null);
		} else if (parameters.command == Command.runTask) {
			TestObjectWithIndexes o1 = (TestObjectWithIndexes)model.getContents().get(0);
			EList<TestObjectWithIndexes> list = (EList<TestObjectWithIndexes>) o1.eGet(reference);
			for (long i = 0; i < parameters.accessReferences; i++) {
				list.get((int)i).getName();
			}
		}
		gauge.onStop();
	}
}
