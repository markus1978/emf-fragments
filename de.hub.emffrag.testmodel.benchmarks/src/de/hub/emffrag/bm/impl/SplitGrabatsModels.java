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
package de.hub.emffrag.bm.impl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Test;

import de.hub.emffrag.test.RegularTest;
import de.hub.emffrag.testmodels.regular.Core.IJavaModel;
import de.hub.emffrag.testmodels.regular.Core.IJavaProject;

public class SplitGrabatsModels extends RegularTest {
    
    @Test
    public void splitGrabatsModels() throws Exception {
//        for (int i = 0; i <= 4; i++) {
//            splitGrabatsModel(i);
//        }
    	splitGrabatsModel(3);
    }
    
    public void splitGrabatsModel(int modelId)   throws Exception {
        String modelFileURI = "../de.hub.emffrag/models/set" + modelId + ".xmi";
        String targetDir = "../de.hub.emffrag/models/set" + modelId;
        
        ResourceSet rs = new ResourceSetImpl();        
        Resource resource = rs.getResource(URI.createURI(modelFileURI), true);
        System.out.println("loaded " + resource.getURI());
        
        IJavaModel javaModel = (IJavaModel)resource.getContents().get(0);
        int index = 0;
        for (IJavaProject project: javaModel.getJavaProjects()) {
            Resource newResource = rs.createResource(URI.createURI(targetDir + "/" + "javaProjects" + index + ".xmi"));
            newResource.getContents().add(project);
            index++;
            System.out.println("added " + newResource.getURI());
        }
        javaModel.getJavaProjects().clear();
        
        Resource newResource = rs.createResource(URI.createURI(targetDir + "/root.xmi"));
        newResource.getContents().add(javaModel);
        System.out.println("added " + newResource.getURI());
        for (Resource aResource: rs.getResources()) {
        	if (aResource != resource) {
        		aResource.save(null);
        		System.out.println("saved " + aResource.getURI());
        	}
        }
                
        for (Resource r: rs.getResources()) {
            r.unload();
        }
    }
}
