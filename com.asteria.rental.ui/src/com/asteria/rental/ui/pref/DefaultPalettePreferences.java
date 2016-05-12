package com.asteria.rental.ui.pref;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;

import com.asteria.rental.ui.RentalUIActivator;
import com.asteria.rental.ui.RentalUIConstants;

public class DefaultPalettePreferences extends FieldEditorPreferencePage implements RentalUIConstants

{

	public DefaultPalettePreferences()
	{
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors()
	{
	
		addField(new ColorFieldEditor(PREF_CUSTOMER_COLOR, "Customer : ", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_RENTAL_COLOR, "Rental : ", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_RENTAL_OBJECT_COLOR, "Objects : ", getFieldEditorParent()));

	}

}
