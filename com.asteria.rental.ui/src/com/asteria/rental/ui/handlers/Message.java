 
package com.asteria.rental.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class Message {
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,@Named("com.asteria.rental.eap.commandparameter.title") String title) {
		MessageDialog.openInformation(shell, title, "Message très important");
	}
		
}