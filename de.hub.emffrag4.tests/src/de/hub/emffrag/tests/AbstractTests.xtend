package de.hub.emffrag.tests

import de.hub.emffrag.EmfFragActivator
import de.hub.emffrag.FObject
import de.hub.emffrag.internal.FStoreObject
import de.hub.emffrag.internal.FStoreObjectImpl
import de.hub.emffrag.tests.model.AbstractClass
import de.hub.emffrag.tests.model.Container
import de.hub.emffrag.tests.model.TestModelFactory
import de.hub.emffrag.tests.model.TestModelPackage
import de.hub.jstattrack.JStatTrackActivator
import java.io.ByteArrayInputStream
import java.text.ParseException
import java.util.HashMap
import java.util.List
import java.util.Map
import java.util.Scanner
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EcorePackage
import org.junit.BeforeClass

class AbstractTests {
	
	@BeforeClass
	static def activateRequiredPlugins() {
		JStatTrackActivator.standalone
		EmfFragActivator.standalone(EcorePackage.eINSTANCE, TestModelPackage.eINSTANCE)
	}
	
	public static val String complexFragmentText = '''
		Container f1 {
			contents = Contents c2;
			contents = Container c3 {
				content = Contents c4;
				ref referenced = c3
			}
			content = Container c5 {
				contents = Contents c6;
				contents = Contents c7;
				ref referenceds = f1
				ref referenceds = c3
			}
		}
	'''
	
	public static val String complexFragmentedModelText = '''
		Container f1 {
			contents = Contents c2;
			fragments = Container f3 {
				content = Contents c4;
				ref referenced = f3
			}
			fragment = Container f5 {
				contents = Contents c6;
				contents = Contents c7;
				ref referenceds = f1
				ref referenceds = c4
			}
		}
	'''
	
	protected extension val TestModelPackage testModelPackage = TestModelPackage.eINSTANCE
	
	protected def contents(String name) {
		val obj = TestModelFactory.eINSTANCE.createContents
		obj.name = name
		return obj
	}
	
	protected def contents(Container container, String name) {
		val obj = TestModelFactory.eINSTANCE.createContents
		obj.name = name
		container.content = obj
		return obj
	}

	protected def container(String name) {
		val obj = TestModelFactory.eINSTANCE.createContainer
		obj.name = name
		return obj
	}
	
	private def String pretty(Object value, EStructuralFeature feature) '''
		«IF feature instanceof EReference»
			«IF feature.containment»
				«feature.name» = «value.pretty»
			«ELSE»
				«IF value instanceof FStoreObject»
					ref «feature.name» = «(value as FStoreObject).fCreateURI.toString»
				«ELSE»
					ref «feature.name» = «(value as AbstractClass).name»
				«ENDIF»
			«ENDIF»
		«ELSE»
			«feature.name» = «value.toString»
		«ENDIF»
	'''
	
	protected dispatch def String pretty(FObject fObject) '''
		«fObject.eClass.name» («fObject.fStoreObject»){
			«FOR feature:fObject.eClass.EAllStructuralFeatures.filter[fObject.eIsSet(it)]»
				«IF feature.isMany»
					«FOR value:fObject.eGet(feature) as List<?>»
						«value.pretty(feature)»
					«ENDFOR»
				«ELSE»
					«fObject.eGet(feature).pretty(feature)»
				«ENDIF»
			«ENDFOR»
		}
	'''
	
	protected dispatch def String pretty(FStoreObject fStoreObject) '''
		«fStoreObject.fClass.name» {
			«FOR feature:fStoreObject.fClass.EAllStructuralFeatures.filter[fStoreObject.fIsSet(it)]»
				«IF feature.isMany»
					«FOR value:fStoreObject.fGet(feature) as List<?>»
						«value.pretty(feature)»
					«ENDFOR»
				«ELSE»
					«fStoreObject.fGet(feature).pretty(feature)»
				«ENDIF»
			«ENDFOR»
		}
	'''
}

public class FStoreObjectBuilder {
	
	private var FStoreObject object = null;
	
	public static  def FStoreObjectBuilder newBuilder(EClass eClass) {
		val builder = new FStoreObjectBuilder
		builder.object = new FStoreObjectImpl(eClass)
		return builder;
	}
	
	private def checkContainment(EStructuralFeature feature, Object value) {
		if (feature instanceof EReference) {
			if (feature.containment) {
				(value as FStoreObject).fSetContainer(object, feature, false)
			}
		}
	}
	
	public def FStoreObjectBuilder set(EStructuralFeature feature, Object value) {
		object.fSet(feature, value)
		checkContainment(feature, value)
		return this
	}
	
	public def FStoreObjectBuilder add(EStructuralFeature feature, Object value) {
		(object.fGet(feature) as List<Object>).add(value)
		checkContainment(feature, value)
		return this
	}
	
	public def FStoreObject build() {
		return object
	}
}



public abstract class TestModelParser {
	private static extension TestModelPackage ePackage = TestModelPackage.eINSTANCE
	
	private final static int ADD_ASSIGN_TOKEN = 0
	private final static int REF_TOKEN = 4
	private final static int OPEN_TOKEN = 5
	private final static int CLOSE_TOKEN = 6
	private final static int NAME_TOKEN = 7
	private final static int END_TOKEN = 8
	private final static int ERROR_TOKEN = -1
	private final static int EOF = -2
	
	private final static int START_STATE = 0
	private final static int DECLARATION_STATE = 1
	private final static int CONTENT_STATE = 2
	private final static int FEATURE_STATE = 3
	private final static int ASSIGN_STATE = 4

	var Scanner in = null
	var int state = 0
	var Object result = null
	var List<Object> currents = newArrayList
	var EClass currentClass = null
	var EStructuralFeature currentFeature = null
	var boolean isRef = false
	val static Map<String,Object> nametable = new HashMap

	public static def <T> T objectWithName(String name) {
		return nametable.get(name) as T
	}
	
	public static def clearNames() {
		nametable.clear
	}

	private def Pair<Integer,String> scan() {
		if (in.hasNext) {
			val next = in.next
			val token = switch next {
				case "=" : ADD_ASSIGN_TOKEN
				case "ref" : REF_TOKEN
				case "{" : OPEN_TOKEN
				case "}" : CLOSE_TOKEN
				case ";" : END_TOKEN
				case next.matches("[a-zA-Z_][a-zA-Z0-9_]*") : NAME_TOKEN
				default : ERROR_TOKEN
			}
			return token -> next	
		} else {
			return EOF->null
		}
		
	}
	
	def Object parse(String text) throws ParseException {
		var varText = text
		for(token:newArrayList("=","{", "}", ";")) {
			varText = varText.replaceAll("\\" + token, " % ")
			varText = varText.replaceAll("%", token)
		}
		
		in = new Scanner(new ByteArrayInputStream(varText.bytes))
		state = START_STATE
		currents.clear
		currentFeature = null
		currentClass = null
		isRef = false
		var token = scan
		while (token.key != EOF) {
			if (token.key == ERROR_TOKEN) {
				throw new RuntimeException("Unknown token " + token.value)
			}
			switch state {
				case START_STATE : start(token)
				case DECLARATION_STATE : declaration(token)
				case CONTENT_STATE : content(token)
				case FEATURE_STATE : feature(token)
				case ASSIGN_STATE : assign(token)
				default: throw new RuntimeException("Unexpected state. Should not be possible.")
			}
			token = scan
		}
		
		return result
	}
	
	private def void start(Pair<Integer, String> token) {
		assign(token)
	}
	private def void declaration(Pair<Integer, String> token) {
		switch token.key {
			case NAME_TOKEN : {
				setAdd(abstractClass_Name, currents.last, token.value)
				nametable.put(token.value, currents.last)
			}
			case OPEN_TOKEN : {
				state = CONTENT_STATE
			}
			case END_TOKEN : {
				state = CONTENT_STATE
				currents.remove(currents.size-1)
				currentClass = currents.last?.eClass
			}
			default: token.unexpectedToken
		}
	}
	private def void content(Pair<Integer, String> token) {
		switch token.key {
			case NAME_TOKEN : {
				currentFeature = currentClass.getEStructuralFeature(token.value)
				if (currentFeature == null) {
					token.unknownFeature
				}
				state = FEATURE_STATE
			}
			case REF_TOKEN : {
				isRef = true
				state = CONTENT_STATE	
			}
			case CLOSE_TOKEN : {
				state = CONTENT_STATE
				currents.remove(currents.size-1)
				currentClass = currents.last?.eClass
			}
			default : token.unexpectedToken
		}
	}
	private def void feature(Pair<Integer,String> token){
		if (token.key==ADD_ASSIGN_TOKEN) {
			state = ASSIGN_STATE
		} else {
			token.unexpectedToken
		}
	}
	private def void assign(Pair<Integer,String> token) {
		try {
			if (isRef) {
				isRef = false
				val value = nametable.get(token.value)
				if (value == null) {
					throw new RuntimeException("Unknown reference " + token.value)
				} else {
					setAdd(currentFeature, currents.last, value)
				}
				state = CONTENT_STATE
			} else {
				currentClass = ePackage.getEClassifier(token.value) as EClass
				val value = create(currentClass)
				if (currents.size > 0) {
					setAdd(currentFeature, currents.last, value)			
				} else {
					result = value
				}
				currents += value
				state = DECLARATION_STATE	
			}
		} catch (Exception e) {
			token.unknownClass
		}
		if (currentClass == null) {
			token.unknownClass
		}	
	}
	private def void unexpectedToken(Pair<Integer,String> token) {
		throw new RuntimeException("Unexpected token " + token.value)
	}
	private def void unknownClass(Pair<Integer,String> token) {
		throw new RuntimeException("Unknown test model class " + token.value)
	}
	private def void unknownFeature(Pair<Integer,String> token) {
		throw new RuntimeException("Unknown feature " + token.value + " in " + currentClass.name)
	}
	protected abstract def EClass eClass(Object object);
	protected abstract def Object create(EClass eClass);
	protected abstract def void setAdd(EStructuralFeature feature, Object object, Object value);
}

public class FStoreObjectTestModelParser extends TestModelParser {
	
	private static val FStoreObjectTestModelParser INSTANCE = new FStoreObjectTestModelParser
	public static def FStoreObject create(String text) {
		return INSTANCE.parse(text) as FStoreObject
	}
	public static def FStoreObject withName(String name) {
		return TestModelParser::objectWithName(name)
	}
	
	protected override create(EClass eClass) {
		val result = new FStoreObjectImpl(eClass)
		result.fMarkModified(true)
		return result
	}
	
	protected override setAdd(EStructuralFeature feature, Object object, Object value) {
		if (feature.isMany) {
			((object as FStoreObject).fGet(feature) as List<Object>).add(value)
		} else {
			(object as FStoreObject).fSet(feature, value)
		}
		if (feature instanceof EReference) {
			if (feature.isContainment) {
				(value as FStoreObject).fSetContainer(object as FStoreObject, feature, false)		
			}
		}
	}
	
	override protected eClass(Object object) {
		return (object as FStoreObject).fClass
	}
	
}

public class FObjectTestModelParser extends TestModelParser {
	private static val FObjectTestModelParser INSTANCE = new FObjectTestModelParser
	public static def <T extends AbstractClass> T create(String text) {
		return INSTANCE.parse(text) as T
	}
	public static def <T extends AbstractClass> T withName(String name) {
		return TestModelParser::objectWithName(name)
	}
	
	override protected eClass(Object object) {
		return (object as EObject).eClass
	}
	
	override protected create(EClass eClass) {
		return TestModelFactory.eINSTANCE.create(eClass)
	}
	
	override protected setAdd(EStructuralFeature feature, Object object, Object value) {
		if (feature.isMany) {
			((object as EObject).eGet(feature) as EList<Object>).add(value)
		} else {
			(object as EObject).eSet(feature, value)
		}
	}	
}