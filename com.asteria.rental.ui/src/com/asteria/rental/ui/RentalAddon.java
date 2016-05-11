 
package com.asteria.rental.ui;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.IEclipseContext;

import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.core.RentalCoreActivator;

public class RentalAddon {
	@PostConstruct
	public void init(IEclipseContext context) {
		context.set(RentalAgency.class, RentalCoreActivator.getAgency());
	}
}
