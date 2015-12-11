package de.hub.emffrag.ui.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmt.modisco.infra.browser.uicore.CustomizableModelContentProvider;
import org.eclipse.gmt.modisco.infra.browser.uicore.CustomizableModelLabelProvider;
import org.eclipse.gmt.modisco.infra.browser.uicore.CustomizationManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

import de.hub.emffrag.datastore.DataStoreImpl;
import de.hub.emffrag.datastore.IDataStore;
import de.hub.emffrag.fragmentation.Fragmentation;
import de.hub.emffrag.fragmentation.FragmentationSet;

/** This view uses the MoDisco content and label providers. */
public class EmfFragView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "de.hub.emffrag.ui.views.EmfFragView";

	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
	private Action addModelAction;
	private Action refreshModelsAction;
	private Action doubleClickAction;
	
	private FragmentationSet fragmentationSet = null;
	private URI currentFragmentationURI = null;

	public EmfFragView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);		
		
		CustomizationManager customizationManager = new CustomizationManager();
		customizationManager.setShowAttributes(true);
		CustomizableModelContentProvider contentProvider = new CustomizableModelContentProvider(customizationManager) {

			@Override
			public Object[] getRootElements(Object inputElement) {
				List<EObject> result = new ArrayList<EObject>();
				if (inputElement instanceof ResourceSet) {
					for(Resource resource: ((ResourceSet)inputElement).getResources()) {										
						synchronized (resource) {
							result.addAll(resource.getContents());
						}						
					}
					return result.toArray();
				} else {
					return super.getRootElements(inputElement);
				}
			}
			
		};
		CustomizableModelLabelProvider labelProvider = new CustomizableModelLabelProvider(customizationManager);
				
		viewer.setLabelProvider(labelProvider);
		viewer.setContentProvider(contentProvider);
		
		viewer.setInput(new ResourceSetImpl());

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "de.hub.emffrag.ui.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				EmfFragView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(addModelAction);
		manager.add(refreshModelsAction);
		manager.add(new Separator());
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(addModelAction);
		manager.add(refreshModelsAction);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(addModelAction);
		manager.add(refreshModelsAction);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
	}

	private void makeActions() {
		addModelAction = new Action() {
			public void run() {
				addModel();
			}
		};
		addModelAction.setText("Add model");
		addModelAction.setToolTipText("Add a model");
		addModelAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		
		refreshModelsAction = new Action() {
			public void run() {
				refreshModels();
			}
		};
		refreshModelsAction.setText("Refresh models");
		refreshModelsAction.setToolTipText("Refresh models");
		refreshModelsAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));
				
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				showMessage("Double-click detected on "+obj.toString());
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}
	
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"EMF-Fragments View",
			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	protected IDataStore createDataStore(URI uri) {
		return DataStoreImpl.createDataStore(uri);
	}
	
	private void addModel(String uriString) {
		Resource resource = null;
		try {
			URI uri = URI.createURI(uriString);
			currentFragmentationURI = uri;
			Fragmentation.config = Fragmentation.READONLY;
			this.fragmentationSet = new FragmentationSet(100, new IDataStore.IDataStoreFactory() {				
				@Override
				public IDataStore createDataStore(URI uri) {
					return EmfFragView.this.createDataStore(uri);
				}
			});
			Fragmentation fragmentation = fragmentationSet.getFragmentation(uri);
			resource = fragmentation.getRootFragment();
			
			viewer.setInput(resource.getResourceSet());
		} catch (Exception e) {
			showMessage("Could not open the model at " + uriString + ": " + e.getMessage());
			e.printStackTrace();
			if (resource != null) {
				try {
					resource.delete(null);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}			
		}
	}
	
	private void refreshModels() {
		if (fragmentationSet != null) {
			fragmentationSet.close();
			fragmentationSet = null;
			addModel(currentFragmentationURI.toString());
		}
	}
	
	private void addModel() {
		final Shell dialog = new Shell (viewer.getControl().getShell(), SWT.DIALOG_TRIM | SWT.SHEET);
		dialog.setText("Add a model");
		FormLayout formLayout = new FormLayout ();
		formLayout.marginWidth = 10;
		formLayout.marginHeight = 10;
		formLayout.spacing = 10;
		dialog.setLayout (formLayout);

		Label label = new Label (dialog, SWT.NONE);
		label.setText ("Model URI:");
		FormData data = new FormData ();
		label.setLayoutData (data);

		Button cancel = new Button (dialog, SWT.PUSH);
		cancel.setText ("Cancel");
		data = new FormData ();
		data.width = 60;
		data.right = new FormAttachment (100, 0);
		data.bottom = new FormAttachment (100, 0);
		cancel.setLayoutData (data);
		cancel.addSelectionListener (new SelectionAdapter () {
			@Override
			public void widgetSelected (SelectionEvent e) {
				dialog.close ();
			}
		});

		final Text text = new Text (dialog, SWT.BORDER);
		text.setText("mongodb://localhost/git.eclipse.org");
		data = new FormData ();
		data.width = 200;
		data.left = new FormAttachment (label, 0, SWT.DEFAULT);
		data.right = new FormAttachment (100, 0);
		data.top = new FormAttachment (label, 0, SWT.CENTER);
		data.bottom = new FormAttachment (cancel, 0, SWT.DEFAULT);
		text.setLayoutData (data);

		Button ok = new Button (dialog, SWT.PUSH);
		ok.setText ("OK");
		data = new FormData ();
		data.width = 60;
		data.right = new FormAttachment (cancel, 0, SWT.DEFAULT);
		data.bottom = new FormAttachment (100, 0);
		ok.setLayoutData (data);
		ok.addSelectionListener (new SelectionAdapter () {
			@Override
			public void widgetSelected (SelectionEvent e) {
				addModel(text.getText());
				dialog.close ();
			}
		});

		dialog.setDefaultButton (ok);
		dialog.pack ();
		dialog.open ();
	}
}