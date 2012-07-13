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

import java.io.File;
import java.util.ArrayList;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.net4j.CDOSessionConfiguration;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.util.CommitException;
import org.eclipse.emf.cdo.view.CDOView;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.connector.IConnector;
import org.eclipse.net4j.tcp.TCPUtil;
import org.eclipse.net4j.util.container.ContainerUtil;
import org.eclipse.net4j.util.container.IManagedContainer;
import org.eclipse.net4j.util.om.OMPlatform;
import org.eclipse.net4j.util.om.log.PrintLogHandler;
import org.eclipse.net4j.util.om.trace.PrintTraceHandler;

import com.google.common.base.Throwables;

import de.hub.emffrag.bm.benchmark.LoadBenchmark;
import de.hub.emffrag.bm.benchmark.QueryBenchmark;
import de.hub.emffrag.bm.benchmark.SaveBenchmark;
import de.hub.emffrag.testmodels.cdo.Core.CorePackage;
import de.hub.emffrag.testmodels.cdo.Core.IJavaModel;
import de.hub.emffrag.testmodels.cdo.Core.IJavaProject;
import de.hub.emffrag.testmodels.cdo.DOM.DOMPackage;

@SuppressWarnings("deprecation")
public class CDOImpl extends XMIImpl {
    
    private ResourceSet rs = null;
    private IManagedContainer container = null;
    private IConnector connector = null;
    private CDOSession session = null;
    
    private void initializeResourceSet() {
        registerPackages(EPackage.Registry.INSTANCE, getCDOPackages());
        rs = new ResourceSetImpl();
        registerResourceFactories(rs.getResourceFactoryRegistry());
    }
    
    private void initializeCDO() {
        // Enable logging and tracing
        OMPlatform.INSTANCE.setDebugging(false);
        OMPlatform.INSTANCE.addLogHandler(PrintLogHandler.CONSOLE);
        OMPlatform.INSTANCE.addTraceHandler(PrintTraceHandler.CONSOLE);

        // Prepare container
        container = ContainerUtil.createContainer();
        Net4jUtil.prepareContainer(container); // Register Net4j factories
        TCPUtil.prepareContainer(container); // Register TCP factories
        CDONet4jUtil.prepareContainer(container); // Register CDO factories
        container.activate();

        // Create connector
        connector = TCPUtil.getConnector(container, "127.0.0.1:2036"); //$NON-NLS-1$
        connector.setOpenChannelTimeout(Long.MAX_VALUE);
        
        // Create configuration
        CDOSessionConfiguration configuration = CDONet4jUtil.createSessionConfiguration();
        configuration.setConnector(connector);
        configuration.setRepositoryName("repo1"); //$NON-NLS-1$
        
        session = configuration.openSession();   
        ((org.eclipse.emf.cdo.net4j.CDOSession)session).options().setCommitTimeout(Integer.MAX_VALUE);
        for (EPackage ePackage: getCDOPackages()) {
            session.getPackageRegistry().putEPackage(ePackage);
        }
    }
    
    private void closeCDO() {
        session.close();
        connector.close();
        container.deactivate();
    }

    public int importModel(SaveBenchmark bm, String xmiFileName, String modelName, Gauge gauge) {
        registerPackages(EPackage.Registry.INSTANCE, getCDOPackages());
        initializeResourceSet();
        initializeCDO();
        
        int count = 0;
        if (xmiFileName.endsWith(".xmi")) {
	        CDOTransaction transaction = session.openTransaction();
	        ((org.eclipse.emf.cdo.net4j.CDOSession)session).options().setCommitTimeout(Integer.MAX_VALUE);
	        CDOResource resource = transaction.getOrCreateResource(modelName); //$NON-NLS-1$
	
	        resource.getContents().clear();
	        gauge.startTimeMeasure();
	        ResourceSet xmiRS = new ResourceSetImpl();
	        Resource xmiResource = xmiRS.getResource(URI.createURI(xmiFileName), true);
	        	       
	        TreeIterator<EObject> allContents = xmiResource.getAllContents();
	        while(allContents.hasNext()) {
	            allContents.next();
	            count++;
	        }	        
	        gauge.takeMemoryMeasure();
	        System.out.println("# model loaded");
	        for (EObject content: new ArrayList<EObject>(xmiResource.getContents())) {
	            resource.getContents().add(content);
	            gauge.takeMemoryMeasure();
	        }
	        System.out.println("# model added to transaction");
	        try {
	        	((org.eclipse.emf.cdo.net4j.CDOSession)session).options().setCommitTimeout(Integer.MAX_VALUE);
	            transaction.commit();
	        } catch (CommitException e) {
	            Throwables.propagate(e);
	        }
	        System.out.println("# transaction commited");
	        gauge.takeTimeMeasure();        
        } else {
        	try {
		        gauge.startTimeMeasure();
		        ResourceSet xmiRS = new ResourceSetImpl();
		        Resource xmiResource = xmiRS.getResource(URI.createURI(xmiFileName + "/root.xmi"), true);
		        xmiResource.load(null);
		        int projectIndex = 0;
		        File file = new File(xmiFileName + "/javaProjects" + projectIndex + ".xmi");
		        while (file.exists()) {
		        	xmiResource = xmiRS.getResource(URI.createURI(xmiFileName + "/javaProjects" + projectIndex  + ".xmi"), true);
		        	xmiResource.load(null);
		        	System.out.println("# model loaded " + xmiResource.getURI());
		        	file = new File(xmiFileName + "/javaProjects" + ++projectIndex + ".xmi");
		        	countXmiResourceContents(xmiResource);
		        }
		        xmiResource = xmiRS.getResource(URI.createURI(xmiFileName + "/root.xmi"), true);
		        countXmiResourceContents(xmiResource);
		        
		        count += countXmiResourceContents(xmiResource);	        
		        gauge.takeMemoryMeasure();
		        CDOTransaction transaction = session.openTransaction();
		        CDOResource resource = transaction.getOrCreateResource(modelName); //$NON-NLS-1$
		        resource.getContents().clear();
		        
		        System.out.println("# model loaded " + xmiResource.getURI());
		        for (EObject content: new ArrayList<EObject>(xmiResource.getContents())) {
		            resource.getContents().add(content);
		            gauge.takeMemoryMeasure();
		        }
		        System.out.println("# model added to transaction");
		        try {
		            transaction.commit();
		            transaction.close();
		        } catch (CommitException e) {
		            Throwables.propagate(e);
		        }
		        System.out.println("# transaction commited " + xmiResource.getContents().size());
		        xmiResource.getContents().clear();
		        xmiRS.getResources().remove(xmiResource);
		        
		        projectIndex = 0;
		        file = new File(xmiFileName + "/javaProjects" + projectIndex + ".xmi");
		        while (file.exists()) {
		        	xmiResource = xmiRS.getResource(URI.createURI(xmiFileName + "/javaProjects" + projectIndex  + ".xmi"), true);	
		        	System.out.println("# model loaded " + xmiResource.getURI());
		        	count += countXmiResourceContents(xmiResource);
		        	gauge.takeMemoryMeasure();
		        	transaction = session.openTransaction();
		        	resource = transaction.getOrCreateResource(modelName); //$NON-NLS-1$
		        	
		        	IJavaModel root = (IJavaModel)resource.getContents().get(0);
		        	IJavaProject iJavaProject = (IJavaProject)xmiResource.getContents().get(0);      	
					root.getJavaProjects().add(iJavaProject);
		        	System.out.println("# model added to transaction");
			        try {
			            transaction.commit();
			            transaction.close();
			        } catch (CommitException e) {
			            Throwables.propagate(e);
			        }
			        gauge.takeMemoryMeasure();
			        System.out.println("# transaction commited " + xmiResource.getContents().size());
			        xmiResource.getContents().clear();
			        xmiRS.getResources().remove(xmiResource);
			        file = new File(xmiFileName + "/javaProjects" + ++projectIndex + ".xmi");
		        }
		        gauge.takeTimeMeasure();
        	} catch (Exception e) {
        		Throwables.propagate(e);
        	}
        }

        closeCDO();
        return count;
    }
    
    private int countXmiResourceContents(Resource xmiResource) {
    	int count = 0;
    	TreeIterator<EObject> allContents = xmiResource.getAllContents();
        while(allContents.hasNext()) {
            EObject next = allContents.next();
            if (next.eIsProxy()) {
            	EcoreUtil.resolve(next, xmiResource.getResourceSet());
            }
            count++;
        }
        return count;
    }
    
    public int traverseModel(LoadBenchmark bm, String xmiFileName, String modelName,  Gauge gauge) {
        registerPackages(EPackage.Registry.INSTANCE, getCDOPackages());
        initializeCDO();

        gauge.startTimeMeasure();
        CDOView view = session.openView();      
        CDOResource resource = view.getResource(modelName); //$NON-NLS-1$
        
        int count = 0;
        TreeIterator<EObject> allContents = resource.getAllContents();
        while(allContents.hasNext()) {
            allContents.next();
            if ((count - 1000) % 100000 == 0) {
            	view.unlockObjects();
            	gauge.takeMemoryMeasure();    
            	System.out.println("# " + count);
            }
            count++;
        }
        gauge.takeMemoryMeasure();                       
        gauge.takeTimeMeasure();        

        closeCDO();
        return count;
    }
    
    public int grabatsQueryModel(QueryBenchmark benchmark, Gauge gauge) {
        registerPackages(EPackage.Registry.INSTANCE, getCDOPackages());
        initializeCDO();

        gauge.startTimeMeasure();
        CDOView view = session.openView();      
        CDOResource resource = view.getResource(benchmark.getPModelName()); //$NON-NLS-1$
        int result = grabatsQueryAsCode(resource.getContents().get(0), gauge, CorePackage.eINSTANCE, DOMPackage.eINSTANCE);     
        gauge.takeMemoryMeasure();                       
        gauge.takeTimeMeasure();        

        closeCDO();
        return result;       
    }
}
