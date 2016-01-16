package de.hub.emffrag.tests

import cern.jet.random.Poisson
import cern.jet.random.Uniform
import cern.jet.random.engine.DRand
import java.util.List
import java.util.Map
import org.apache.commons.lang.RandomStringUtils
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EcorePackage

class Random {

	val DRand rand
	
	new(int seed) {
		rand = new DRand(seed)
	}
	
	def randomChoice() {
		return new Uniform(0,1,rand).nextDouble() <= 0.5;
	}
	
	def randomCount() {
		return new Poisson(0.1, rand).nextInt();
	}
	
	def Object randomValue(EAttribute attribute) {
		return switch attribute.EType {
			case EcorePackage.eINSTANCE.EString: RandomStringUtils.random(randomCount*2)
			case EcorePackage.eINSTANCE.EInt: if (randomChoice) Integer.MAX_VALUE else Integer.MAX_VALUE
			default: attribute.defaultValue
		}
	}
	
	def <E> E randomElement(List<E> list) {
		return if (list == null || list.empty) {
			null
		} else if (list.size == 1) {
			list.get(0)
		} else {
			list.get(new Uniform(0, list.size-1, rand).nextInt())	
		}
	}
	
	def randomLink(EReference reference, Map<EClass,List<EObject>> instanceMap) {
		val eClass = reference.EType as EClass
		val List<EClass> allSuperClasses = newArrayList(eClass.EAllSuperTypes)
		allSuperClasses.add(eClass)
		var size = 0
		for(superClass:allSuperClasses) {
			size += if (instanceMap.get(superClass) == null) 0 else instanceMap.get(superClass).size
		}
		if (size == 0) return null
		val numberOfInstances = size
		val index = if (size == 1) 0 else new Uniform(0, numberOfInstances-1, rand).nextInt()
		size = 0
		for(superClass:allSuperClasses) {
			val oldSize = size
			val thisSize = if (instanceMap.get(superClass) == null) 0 else instanceMap.get(superClass).size
			size += thisSize
			if (index < size) {
				return instanceMap.get(superClass).get(index-oldSize)
			}
		}
		throw new IllegalStateException("unreachable")
	}
	
	def EClass randomConcreteClass(EClass abstractClass) {
		val possibleClasses = eAllConcreteSubClasses(abstractClass)
		return possibleClasses.randomElement
	}
	
	val Map<EClass, List<EClass>> allSubClasses = newHashMap() 
	
	def eAllConcreteSubClasses(EClass eClass) {
		var result = allSubClasses.get(eClass)
		if (result == null) {
			result = newArrayList
			if (!eClass.abstract) result.add(eClass)
			result.addAll(eClass.EPackage.EClassifiers
				.filter[it instanceof EClass].map[it as EClass]
				.filter[EAllSuperTypes.contains(eClass) && !abstract])
			allSubClasses.put(eClass, result)		
		}
		return result
	}
}

class ModelGenerator {
	
	val extension Random random
	var extension EFactory factory = null
	val int maxDepth
	val Map<EClass, List<EObject>> instanceMap = newHashMap
	
	new(int seed, int maxDepth) {
		this.random = new Random(seed)
		this.maxDepth = maxDepth
	}
	
	def isExcludeFeatured(EStructuralFeature feature) {
		return if (feature.derived || feature.transient) {
			true
		} else if (feature instanceof EReference) {
			if (feature.EOpposite != null) {
				feature.EOpposite.containment
			} else {
				false
			}
		} else {
			false
		}
	}
	
	def generateValue(EStructuralFeature feature, EObject obj, (EStructuralFeature)=>Object valueGenerator) {
		if (!feature.excludeFeatured) {
			if (feature.many) {
				for(i:0..randomCount) {
					val value = valueGenerator.apply(feature)
					if (value != null) (obj.eGet(feature) as List<Object>).add(value)
				}
			} else {
				if (randomChoice) {
					val value = valueGenerator.apply(feature)
					if (value != null) obj.eSet(feature, value)
				} 
			}				
		}
	}
	
	def EObject generateContainmentHierarchy(EClass eClass, int depth) {
		val obj = create(eClass);
		var instances = instanceMap.get(eClass)
		if (instances == null) {
			instances = newArrayList
			instanceMap.put(eClass, instances)
		}
		instances.add(obj)
		for(feature: eClass.EAllStructuralFeatures) {
			feature.generateValue(obj) [
				return switch feature {
					EAttribute: feature.randomValue
					EReference: 
						if (feature.containment) 
							if (depth < maxDepth) {
								generateContainmentHierarchy((feature.EType as EClass).randomConcreteClass, depth+1)	
							} else {
								null
							}
						else {
							null			
						}  
					default: throw new IllegalStateException("unreachable")
				}
			]
		}
		return obj
	}
	
	def void generateLinks(EObject obj) {
		val eClass = obj.eClass
		for(feature: eClass.EAllStructuralFeatures) {
			feature.generateValue(obj) [
				return switch feature {
					EAttribute: null
					EReference: 
						if (feature.containment) 
							null
						else {
							feature.randomLink(instanceMap)			
						}  
					default: throw new IllegalStateException("unreachable")
				}
			]
		}	
	}
	
	def void generateAllLinks(EObject obj) {
		obj.generateLinks
		obj.eAllContents.forEach[it.generateLinks]	
	}
	
	public def generateModel(EClass rootClass) {
		instanceMap.clear
		factory = rootClass.EPackage.EFactoryInstance
		val root = generateContainmentHierarchy(rootClass, 0)
		root.generateAllLinks
		return root
	}
}