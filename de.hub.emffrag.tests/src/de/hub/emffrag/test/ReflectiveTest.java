/*******************************************************************************
 * Copyright 2012 Markus Scheidgen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.hub.emffrag.test;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.junit.Before;

import de.hub.emffrag.testmodels.reflective.Core.CorePackage;
import de.hub.emffrag.testmodels.reflective.DOM.DOMPackage;
import de.hub.emffrag.testmodels.reflective.PrimitiveTypes.PrimitiveTypesPackage;

public class ReflectiveTest extends AbstractTests {
	@Before
    public void registerPackages() {
         if (!EPackage.Registry.INSTANCE.containsKey(EcorePackage.eINSTANCE.getNsURI())) {
             EPackage.Registry.INSTANCE.put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);
         }
         if (!EPackage.Registry.INSTANCE.containsKey(XMLTypePackage.eINSTANCE.getNsURI())) {
             EPackage.Registry.INSTANCE.put(XMLTypePackage.eINSTANCE.getNsURI(), XMLTypePackage.eINSTANCE);
         }
         if (!EPackage.Registry.INSTANCE.containsKey(CorePackage.eINSTANCE.getNsURI())) {
             EPackage.Registry.INSTANCE.put(CorePackage.eINSTANCE.getNsURI(), CorePackage.eINSTANCE);
         }
         if (!EPackage.Registry.INSTANCE.containsKey(DOMPackage.eINSTANCE.getNsURI())) {
             EPackage.Registry.INSTANCE.put(DOMPackage.eINSTANCE.getNsURI(), DOMPackage.eINSTANCE);
         }
         if (!EPackage.Registry.INSTANCE.containsKey(PrimitiveTypesPackage.eINSTANCE.getNsURI())) {
             EPackage.Registry.INSTANCE.put(PrimitiveTypesPackage.eINSTANCE.getNsURI(), PrimitiveTypesPackage.eINSTANCE);
         }
    }
}
