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

import java.io.IOException;

import junit.framework.Assert;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hub.emffrag.testmodels.regular.Core.CorePackage;
import de.hub.emffrag.testmodels.regular.DOM.DOMPackage;
import de.hub.emffrag.testmodels.regular.PrimitiveTypes.PrimitiveTypesPackage;

public class RegularTest {
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
    
    @BeforeClass
    public static void setUp() {    	
        Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap().put("hbase", new XMIResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());               
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    }

    @Test
    public void complexJObjectTest() throws Exception {	
    	loadAndTraverseGrabatsModel();                 
    }
    
	private void loadAndTraverseGrabatsModel() throws IOException {
    	long start = System.currentTimeMillis();
    	ResourceSet rs = new ResourceSetImpl();
        Resource resource = rs.getResource(URI.createURI("models/set0.xmi"), true);
        resource.load(null);
        long end = System.currentTimeMillis();
        
        System.out.println("## " + (end - start));
        
        start = System.currentTimeMillis();
        TreeIterator<EObject> allContents = resource.getAllContents();
        int count = 0;
        while(allContents.hasNext()) {
        	allContents.next();
        	count++;
        }
        end = System.currentTimeMillis();
        System.out.println("## " + count + " in " + (end - start));
        Assert.assertTrue(resource.getContents().size() > 0);
	}
}
