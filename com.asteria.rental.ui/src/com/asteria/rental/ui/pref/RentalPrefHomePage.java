package com.asteria.rental.ui.pref;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.asteria.rental.ui.RentalUIActivator;
import com.asteria.rental.ui.RentalUIConstants;

public class RentalPrefHomePage extends FieldEditorPreferencePage implements RentalUIConstants

{

	public RentalPrefHomePage()
	{
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		// noDefaultAndApplyButton();
	}



	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	@Override
	protected void createFieldEditors()
	{
		addField(new BooleanFieldEditor(PREF_DISPLAY_COUNT, "Affiche les totaux par groupes d'objets", getFieldEditorParent()));
		
	}



	@Override
	protected Control createContents(Composite parent)
	{
		Label lab = new Label(parent, SWT.NONE);
		lab.setText("Pages de préférences de rental.");
		
		// TODO Auto-generated method stub
		return super.createContents(parent);
	}
	
	
	
	


}
