package com.asteria.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.URLTransfer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.asteria.rental.ui.RentalUIConstants;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.helpers.RentalAgencyGenerator;

public class RentalAgencyView implements RentalUIConstants
{
	public static final String VIEW_ID = "com.opcoach.rental.ui.rentalagencyview";

	private TreeViewer agencyViewer;

	private RentalProvider provider;

	public RentalAgencyView()
	{
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void createPartControl(Composite parent, ESelectionService selectionService, IEclipseContext context, @Named(RentalUIConstants.RENTAL_UI_IMAGE_REGISTRY) ImageRegistry imgReg, EMenuService menuService)
	{
		parent.setLayout(new GridLayout(1, false));

		final Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(2, false));
		Button expandAll = new Button(comp, SWT.FLAT);
		expandAll.setImage(imgReg.get(IMG_EXPAND_ALL));
		expandAll.setToolTipText("Expand agency tree");
		expandAll.addSelectionListener(new SelectionListener()
			{
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					agencyViewer.expandAll();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e)
				{
				}
			});
		Button collapseAll = new Button(comp, SWT.FLAT);
		collapseAll.setImage(imgReg.get(IMG_COLLAPSE_ALL));
		collapseAll.setToolTipText("Collapse agency tree");
		collapseAll.addSelectionListener(new SelectionListener()
			{

				@Override
				public void widgetSelected(SelectionEvent e)
				{
					agencyViewer.collapseAll();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e)
				{
				}
			});

		agencyViewer = new TreeViewer(parent);
		agencyViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true));
		
		provider = ContextInjectionFactory.make(RentalProvider.class, context);
		
		agencyViewer.setContentProvider(provider);
		agencyViewer.setLabelProvider(provider);

		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(RentalCoreActivator.getAgency());

		RentalAgency lyon = RentalAgencyGenerator.createSampleAgency();
		lyon.setName("Lyon");
		agencies.add(lyon);

		agencyViewer.setInput(agencies);

		// Association de la vue sur un contexte d'aide
		//E34: Help ?
//		PlatformUI.getWorkbench().getHelpSystem()
//				.setHelp(agencyViewer.getControl(), "com.opcoach.training.rental.ui.rentalContext");


		// L'arbre est draggable
		DragSource ds = new DragSource(agencyViewer.getControl(), DND.DROP_COPY);
		Transfer[] ts = new Transfer[] { TextTransfer.getInstance(), RTFTransfer.getInstance(), URLTransfer.getInstance() };
		ds.setTransfer(ts);
		ds.addDragListener(new AgencyTreeDragSourceListener(agencyViewer, (ImageRegistry) context.get(RENTAL_UI_IMAGE_REGISTRY)));

		
		agencyViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				selectionService.setSelection(selection.size() == 1 ? selection.getFirstElement() : selection.toArray());
				
			}
		});

		// Autorise le popup sur le treeviewer
		menuService.registerContextMenu(agencyViewer.getControl(), "com.asteria.rental.eap.popupmenu.message");

	}
	@Inject
	public void propertyChange(@Preference(PREF_CUSTOMER_COLOR) String custColor,
			@Preference(PREF_RENTAL_COLOR) String renttColor,
			@Preference(PREF_RENTAL_OBJECT_COLOR) String rentObjtColor)
	{
		if (provider != null) {
			provider.initPalette();
			agencyViewer.refresh();
		}
	}

	@Focus
	public void setFocus()
	{
		// TODO Auto-generated method stub

	}

//E34: gestion retour de selection du dashboard
//	public void selectionChanged(IWorkbenchPart part, ISelection selection)
//	{
//		// Must check if this selection is coming from this part or from another
//		// one.
//		if (part != this)
//			agencyViewer.setSelection(selection, true);
//
//	}

}
