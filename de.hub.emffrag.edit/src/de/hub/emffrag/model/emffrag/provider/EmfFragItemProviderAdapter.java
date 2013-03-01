package de.hub.emffrag.model.emffrag.provider;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.AttributeValueWrapperItemProvider;
import org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;

import de.hub.emffrag.fragmentation.FObjectImpl;

public class EmfFragItemProviderAdapter extends ItemProviderAdapter implements IItemColorProvider {

	public EmfFragItemProviderAdapter(AdapterFactory adapterFactory) {
		super(adapterFactory);	
	}

	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			if (object instanceof EObject) {
				for (EStructuralFeature feature: ((EObject) object).eClass().getEAllStructuralFeatures()) {
					if (feature instanceof EAttribute) {
						childrenFeatures.add(feature);				
					}
				}
				for (EStructuralFeature feature: ((EObject) object).eClass().getEAllStructuralFeatures()) {
					if (feature instanceof EReference) {
						EReference reference = (EReference) feature;
						if (!(reference.isContainment() || reference.isContainer())) {
							childrenFeatures.add(feature);
						}					
					}
				}				
			}
		}
		return childrenFeatures;
	}

	@Override
	public Object getBackground(Object object) {
		if (object instanceof FObjectImpl && ((FObjectImpl)object).fInternalObject().isFragmentRoot()) {
			return PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW);
		} else {
			return null;
		}
	}

	@Override
	protected boolean isWrappingNeeded(Object object) {
		if (object instanceof EObject) {
			for (EStructuralFeature feature: ((EObject) object).eClass().getEAllStructuralFeatures()) {
				if (feature instanceof EReference) {
					EReference reference = (EReference) feature;
					if (!(reference.isContainment() || reference.isContainer())) {
						return true;
					}					
				} else if (feature instanceof EAttribute) {
					return true;
				}
			}
		}
		return super.isWrappingNeeded(object);
	}
	
	@Override
	protected Object createWrapper(EObject object, final EStructuralFeature feature, Object value, int index) {
		if (!isWrappingNeeded(object))
			return value;

		if (feature instanceof EReference && !((EReference) feature).isContainment()) {
			return new DelegatingWrapperItemProvider(value, object, feature, index, adapterFactory) {

				@Override
				public String getText(Object object) {
					return feature.getName() + ":" + super.getText(object);
				}

				@Override
				public Object getForeground(Object object) {
					return PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE);
				}
			};
		} else if (feature instanceof EAttribute) {
			return new MyAttributeValueWrapperItemProvider(value, object, (EAttribute)feature, index, adapterFactory, getResourceLocator());
		} else {
			return super.createWrapper(object, feature, value, index);
		}
	}
	
	private static class MyAttributeValueWrapperItemProvider extends AttributeValueWrapperItemProvider implements
			IItemColorProvider {

		public MyAttributeValueWrapperItemProvider(Object value, EObject owner, EAttribute attribute, int index,
				AdapterFactory adapterFactory, ResourceLocator resourceLocator) {
			super(value, owner, attribute, index, adapterFactory, resourceLocator);
		}

		@Override
		public String getText(Object object) {
			return feature.getName() + ":" + super.getText(object);
		}

		@Override
		public Object getForeground(Object object) {
			return PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN);
		}
	}

}
