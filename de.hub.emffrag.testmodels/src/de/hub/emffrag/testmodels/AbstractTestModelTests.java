package de.hub.emffrag.testmodels;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import de.hub.emffrag.testmodels.eobject.testmodel.eobject.meta.TestModelPackage;

public abstract class AbstractTestModelTests<T extends EObject, P extends EPackage> {
	private final EClass testObjectClass = (EClass)testModelPackage().getEClassifier(TestModelPackage.eINSTANCE.getTestObject().getName());
	private final EStructuralFeature nameFeature = testObjectClass.getEStructuralFeature(TestModelPackage.eINSTANCE.getTestObject_Name().getName());
	
	protected EFactory tmFactory;
	protected EPackage tmPackage;
	protected List<Object> modifiedTester = new ArrayList<Object>();
	private Map<String, T> savedTestObject = new HashMap<String, T>();

	protected void save(T object) {
		savedTestObject.put((String)object.eGet(nameFeature), object);
	}
	
	protected T getSaved(String name) {
		return savedTestObject.get(name);
	}
	

	@Before
	public void initializeTestModel() {
		savedTestObject.clear();
		modifiedTester.clear();
		tmPackage = testModelPackage();
		tmFactory = tmPackage.getEFactoryInstance();
	}
	
	@BeforeClass
	public static void initializeEMF() {
		
	}
	
	protected abstract P testModelPackage();

	protected T createTO(String name) {
		return createTO(name, null, null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected T createTO(String name, T parent, EReference reference) {
		T testObject = (T)tmFactory.create((EClass)tmPackage.getEClassifier("TestObject"));
		testObject.eSet(testObject.eClass().getEStructuralFeature("name"), name);
		if (parent != null) {
			((List) parent.eGet(reference)).add(testObject);
		}

		return testObject;
	}
	
	@SuppressWarnings("unchecked")
	protected <R> R createFromModelString(String model) {
		try {
			return (R)new TestModelParser().create(model);
		} catch (ParseException e) {
			Assert.fail("Parse error: " + e.getMessage() + " at " + e.getErrorOffset());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected T createTOFromModelString(String model) {
		EObject object = createFromModelString(model);
		if (isTestObject(object)) {
			return (T)object;
		} else {
			Assert.fail("Model string describes not a test object.");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected <R> R query(EObject root, String query) {
		try {
			return (R)new TestModelParser().query(root, query);
		} catch (ParseException e) {
			Assert.fail("Parse error: " + e.getMessage() + " at " + e.getErrorOffset());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected T queryTO(EObject root, String query) {
		EObject object = query(root, query);
		if (isTestObject(object)) {
			return (T)object;
		} else {
			return null;
		}
	}
	
	protected String printTO(T root) {
		return printTO(root, true);
	}
	
	protected String printTO(T root, boolean printFragmentation) {
		StringBuffer result = new StringBuffer();
		printObject(result, root, true, printFragmentation);
		return result.toString();
	}
	
	private void printObject(StringBuffer result, T object, boolean printFeatures, boolean printFragmentation) {
		result.append(object.eGet(nameFeature));
		if (printFeatures) {
			if (printFragmentation) {
				printFeature(result, testObjectClass.getEStructuralFeature(TestModelPackage.eINSTANCE.getTestObject_FragmentedContents().getName()), object, printFragmentation);
			}
			printFeature(result, testObjectClass.getEStructuralFeature(TestModelPackage.eINSTANCE.getTestObject_RegularContents().getName()), object, printFragmentation);
			printFeature(result, testObjectClass.getEStructuralFeature(TestModelPackage.eINSTANCE.getTestObject_CrossReferences().getName()), object, printFragmentation);
		}
	}

	@SuppressWarnings("unchecked")
	private void printFeature(StringBuffer result, EStructuralFeature feature, T object, boolean printFragmentation) {	
		EList<T> valueSet = (EList<T>) object.eGet(feature);
		if (!valueSet.isEmpty()) {
			result.append(feature.getName().toLowerCase().charAt(0));
			result.append("(");
			boolean first = true;
			for (T value: valueSet) {
				if (first) {
					first = false;					
				} else {
					result.append(",");
				}
				printObject(result, value, ((EReference)feature).isContainment(), printFragmentation);
			}
			result.append(")");
		}
	}
	
	boolean isTestObject(EObject object) {
		return object.eClass() == testObjectClass;
	}

	private class TestModelParser {
		Map<String, T> objects = new HashMap<String, T>();
		int offset = 0;
		
		EObject getObject(String name) {
			if (name.equals("E")) {
				return EcoreFactory.eINSTANCE.createEObject();
			} else {
				T result = objects.get(name);
				if (result == null) {
					result = createTO(name);
					objects.put(name, result);
				}
				return result;
			}
		}
		
		EObject createParseObject(Queue<Character> src) throws ParseException {
			String name = "";
			while(!src.isEmpty() && (Character.isDigit(src.peek()) || src.peek() == 'E')) {
				name += src.poll();				
			}
			EObject object = getObject(name);
			if (isTestObject(object)) {
				createParseFeatures(src, object);
			}
			return object;
		}
		
		void createParseFeatures(Queue<Character> src, EObject object) throws ParseException {
			while (!src.isEmpty()) {
				EList<EObject> valueSet = getValueSet(src.peek(), object);
				if (valueSet != null) {
					src.poll();
					
					if (src.peek() != '(') {
						throw new ParseException("Expected '('", offset);
					}
					src.poll();
					
					list: while (true) {
						valueSet.add(createParseObject(src));
						if (src.peek() == ')') {
							src.poll();
							break list;
						} else if (src.peek() != ',') {
							throw new ParseException("Expected an object definition or ')' or ','", offset);
						} else {
							src.poll();
						}
					}					
				} else {
					return;
				}
			}
		}

		@SuppressWarnings({ "serial" })
		EObject create(String definition) throws ParseException {		
			Queue<Character> stream = new LinkedList<Character>() {
				@Override
				public Character poll() {
					offset++;
					return super.poll();
				}

				@Override
				public Character peek() {
					Character c = super.peek();
					if (c == null) {
						throw new RuntimeException(new ParseException("Unexpected end of stream", offset));
					}
					return c;
				}	
				
			};
			for(char c: definition.toCharArray()) {
				stream.add(c);
			}
			
			EObject object = null;
			try {
				object = createParseObject(stream);
			} catch (RuntimeException e) {
				if (e.getCause() instanceof ParseException) {
					throw (ParseException)e.getCause();
				} else {
					throw e;
				}
			}
			if (!stream.isEmpty()) {
				throw new ParseException("Definition does not end properly", offset);
			}
			return object;
		}
		
		private EObject find(EList<EObject> valueSet, String name) {
			if (name.equals("E") && valueSet.size() > 0) {
				return valueSet.get(0);
			}
			for (EObject o: valueSet) {
				if (name.equals(o.eGet(nameFeature))) {
					return o;
				}
			}
			return null;
		}
		
		EObject query(EObject root, String definition) throws ParseException {
			String currentName = "";
			EList<EObject> currentValueSet = null;
			EObject currentObject = null;
			
			EList<EObject> start = new BasicEList<EObject>();
			start.add(root);
			currentValueSet = start;
			
			for(char c: definition.toCharArray()) {							
				if (Character.isDigit(c) || c == 'E') {
					currentName += ""+c;
				} else {
					currentObject = find(currentValueSet, currentName);
					currentName = "";
					if (currentObject == null) {
						return null;
					}							
					currentValueSet = getValueSet(c, currentObject);												
				}				
				offset++;
			}
			return find(currentValueSet, currentName);			
		}

		@SuppressWarnings("unchecked")
		EList<EObject> getValueSet(char c, EObject object) {
			EStructuralFeature feature = null;
			if (c == 'f') {
				feature = testObjectClass.getEStructuralFeature(TestModelPackage.eINSTANCE.getTestObject_FragmentedContents().getName());
			} else if (c == 'r') {
				feature = testObjectClass.getEStructuralFeature(TestModelPackage.eINSTANCE.getTestObject_RegularContents().getName());
			} else if (c == 'c') {
				feature = testObjectClass.getEStructuralFeature(TestModelPackage.eINSTANCE.getTestObject_CrossReferences().getName());
			} else if (c == 'a') {
				feature = testObjectClass.getEStructuralFeature(TestModelPackage.eINSTANCE.getTestObject_ArbitraryContents().getName());
			}
			if (feature != null && isTestObject(object)) {
				return (EList<EObject>)object.eGet(feature);
			} else {
				return null;
			}
		}
	}
}
