 
package com.asteria.rental.ui;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.UIEvents.EventTags;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.core.RentalCoreActivator;

public class RentalAddon implements RentalUIConstants {
	@PostConstruct
	public void init(IEclipseContext context) {
		context.set(RentalAgency.class, RentalCoreActivator.getAgency());
		ImageRegistry reg = new ImageRegistry();
		initializeImageRegistry(reg);
		context.set(RENTAL_UI_IMAGE_REGISTRY, reg);
	}

	@Inject
	@Optional
	public void reactOnWindowMove(@UIEventTopic(UIEvents.Window.TOPIC_X) org.osgi.service.event.Event event) {
		System.out.println("Window(x)=" + event.getProperty(EventTags.NEW_VALUE));
	}

	@Inject
	@Optional
	public void reactOnCopyCustomer(@UIEventTopic("customer/*") Customer c) {
		System.out.println("Client copié  " + c.getDisplayName());
	}

	protected void initializeImageRegistry(ImageRegistry reg)
	{
		Bundle b = FrameworkUtil.getBundle(getClass());

		// Then fill the values...
		reg.put(IMG_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_RENTAL_OBJECT, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL_OBJECT)));
		reg.put(IMG_AGENCY, ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));
		reg.put(IMG_COLLAPSE_ALL, ImageDescriptor.createFromURL(b.getEntry(IMG_COLLAPSE_ALL)));
		reg.put(IMG_EXPAND_ALL, ImageDescriptor.createFromURL(b.getEntry(IMG_EXPAND_ALL)));

	}
	
}
