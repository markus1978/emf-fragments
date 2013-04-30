package de.hub.emffrag.benchmarks.reference1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.eclipse.emf.ecore.EObject;

import de.hub.emffrag.testmodels.testmodel.TestObjectWithIndexes;

public class ReferenceRepresentationBenchmark implements IBenchmark<EObject> {

	private final long numberOfObjects;	
	private final long numberOfObjectsToTraverse;
	private final long numberOfChildren;
	private final int fragmentSize;
	private final ReferenceRepresentationBenchmarkConfiguration factory;
	
	public ReferenceRepresentationBenchmark(long numberOfObjects, long numberOfObjectsToTraverse, long numberOfChildren,
			int fragmentSize, ReferenceRepresentationBenchmarkConfiguration factory) {
		super();
		this.numberOfObjects = numberOfObjects;
		this.numberOfObjectsToTraverse = numberOfObjectsToTraverse;
		this.numberOfChildren = numberOfChildren;
		this.fragmentSize = fragmentSize;
		this.factory = factory;
	}

	public interface ReferenceRepresentationBenchmarkConfiguration {
		public EObject create();
		public void add(EObject owner, EObject child, boolean containment, boolean fragmenting);
		public Iterable<EObject> iterate(EObject owner);
	}
	
	private class ContainmentTree {
		private Queue<EObject> parentQueue = new LinkedList<EObject>();
		private int currentFragmentSize = 0;
		private EObject parent = null;
		private final EObject root;
		
		public ContainmentTree(EObject root) {
			super();
			this.root = root;
		}

		private EObject createObject() {
			if (parent == null) {
				parent = root;
			}
			EObject newObject = factory.create();
			
			if (currentFragmentSize >= fragmentSize) {
				currentFragmentSize = 0;
				parent = parentQueue.poll();
				factory.add(parent, newObject, true, true);
				parent = newObject;
				currentFragmentSize++;
			} else {
				parentQueue.add(newObject);
				currentFragmentSize++;
				factory.add(parent, newObject, true, false);
			}
						
			return newObject;
		} 
	}

	@Override
	public void populateModel(EObject root, IBenchmarkContext context) {
		context.onStart();
		long id = 0;
		ContainmentTree ct = new ContainmentTree(root);

		Queue<EObject> parentQueue = new LinkedList<EObject>();
		EObject parent = root;
		int currentChildren = 0;
		
		for (long i = 0; i < numberOfObjects; i++) {
			
			EObject newObject = ct.createObject();
			((TestObjectWithIndexes)newObject).setName("" + id++);
			parentQueue.add(newObject);
			factory.add(parent, newObject, false, false);
			currentChildren++;
			if (currentChildren >= numberOfChildren) {
				parent = parentQueue.poll();
				currentChildren = 0;
			}			
		}
		context.onStop();
	}

	@Override
	public boolean runTask(EObject model, de.hub.emffrag.benchmarks.reference1.IBenchmark.IBenchmarkContext benchmarkContext) {
		Stack<EObject> objects = new Stack<EObject>();
		
		benchmarkContext.onStart();
		
		objects.push(model);
		long numberOfVisitedObjects = 0;
		numberOfVisitedObjects++;
		loop: while (!objects.isEmpty() && numberOfVisitedObjects != numberOfObjectsToTraverse) {
			EObject parent = objects.pop();
			for (EObject child: factory.iterate(parent)) {
				objects.push(child);
				numberOfVisitedObjects++;
				if (numberOfVisitedObjects == numberOfObjectsToTraverse) {
					break loop;
				}
			}
		}
		
		benchmarkContext.onStop();
		
		return numberOfVisitedObjects == numberOfObjectsToTraverse;
	}	
}
