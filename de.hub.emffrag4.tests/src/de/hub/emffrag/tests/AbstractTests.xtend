package de.hub.emffrag.tests

import de.hub.emffrag.tests.model.TestModelFactory
import de.hub.emffrag.tests.model.Container

class AbstractTests {
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
}