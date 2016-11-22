package com.wincom.actor.editor.tutogef.editor;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.tutogef.Activator;
import com.wincom.actor.editor.tutogef.NodeCreationFactory;
import com.wincom.actor.editor.tutogef.action.AppContextMenuProvider;
import com.wincom.actor.editor.tutogef.action.CopyNodeAction;
import com.wincom.actor.editor.tutogef.action.MyTemplateTransferDropTargetListener;
import com.wincom.actor.editor.tutogef.action.PasteNodeAction;
import com.wincom.actor.editor.tutogef.action.RenameAction;
import com.wincom.actor.editor.tutogef.model.Employe;
import com.wincom.actor.editor.tutogef.model.Enterprise;
import com.wincom.actor.editor.tutogef.model.Service;
import com.wincom.actor.editor.tutogef.part.AppEditPartFactory;
import com.wincom.actor.editor.tutogef.part.tree.AppTreeEditPartFactory;

public class MyGraphicalEditor extends GraphicalEditorWithPalette {
	Logger log = LoggerFactory.getLogger(this.getClass());

	public static final String ID = "com.wincom.actor.editor.tutogef.editor2";
	private Enterprise model;
	private KeyHandler keyHandler;

	protected class OutlinePage extends ContentOutlinePage {
		private SashForm sash;
		private ScrollableThumbnail thumbnail;
		private DisposeListener disposeListener;

		public OutlinePage() {
			super(new TreeViewer());
		}

		public void createControl(Composite parent) {
			sash = new SashForm(parent, SWT.VERTICAL);
			getViewer().createControl(sash);
			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new AppTreeEditPartFactory());
			getViewer().setContents(model);
			getSelectionSynchronizer().addViewer(getViewer());

			Canvas canvas = new Canvas(sash, SWT.BORDER);
			LightweightSystem lws = new LightweightSystem(canvas);
			thumbnail = new ScrollableThumbnail(
					(Viewport) ((ScalableRootEditPart) getGraphicalViewer().getRootEditPart()).getFigure());
			thumbnail.setSource(((ScalableRootEditPart) getGraphicalViewer().getRootEditPart())
					.getLayer(LayerConstants.PRINTABLE_LAYERS));
			lws.setContents(thumbnail);
			disposeListener = new DisposeListener() {
				@Override
				public void widgetDisposed(DisposeEvent e) {
					if (thumbnail != null) {
						thumbnail.deactivate();
						thumbnail = null;
					}
				}
			};
			getGraphicalViewer().getControl().addDisposeListener(disposeListener);
			
			IActionBars bars = getSite().getActionBars();
			ActionRegistry ar = getActionRegistry();
			bars.setGlobalActionHandler(ActionFactory.COPY.getId(),
			ar.getAction(ActionFactory.COPY.getId()));
			bars.setGlobalActionHandler(ActionFactory.PASTE.getId(),
			ar.getAction(ActionFactory.PASTE.getId()));
		}

		public void init(IPageSite pageSite) {
			super.init(pageSite);
			IActionBars bars = getSite().getActionBars();
			bars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
					getActionRegistry().getAction(ActionFactory.UNDO.getId()));
			bars.setGlobalActionHandler(ActionFactory.REDO.getId(),
					getActionRegistry().getAction(ActionFactory.REDO.getId()));
			bars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
					getActionRegistry().getAction(ActionFactory.DELETE.getId()));
			bars.updateActionBars();
			getViewer().setKeyHandler(keyHandler);

			ContextMenuProvider provider = new AppContextMenuProvider(getViewer(), getActionRegistry());
			getViewer().setContextMenu(provider);

		}

		public Control getControl() {
			return sash;
		}

		public void dispose() {
			getSelectionSynchronizer().removeViewer(getViewer());

			if (getGraphicalViewer().getControl() != null && !getGraphicalViewer().getControl().isDisposed())
				getGraphicalViewer().getControl().removeDisposeListener(disposeListener);
			super.dispose();
		}
	}

	public MyGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	public Enterprise createEnterprise() {
		log.info("check");
		Enterprise e = new Enterprise();
		e.setAddress("No Such an Address");
		e.setCapital(1000000);
		e.setName("Wincom");

		Service s1 = new Service();
		s1.setStockNo(2);
		s1.setLayout(new Rectangle(30, 50, 250, 150));
		s1.setName("XOXO Service");

		Employe e1 = new Employe();
		e1.setName("Nameless1");
		e1.setPrenom("P1");
		e1.setLayout(new Rectangle(25, 40, 60, 40));
		s1.addChild(e1);

		Employe e2 = new Employe();
		e2.setName("Nameless2");
		e2.setPrenom("P2");
		e2.setLayout(new Rectangle(100, 60, 60, 40));
		s1.addChild(e2);

		Employe e3 = new Employe();
		e3.setName("Nameless3");
		e3.setPrenom("P3");
		e3.setLayout(new Rectangle(180, 90, 60, 40));
		s1.addChild(e3);

		e.addChild(s1);

		Service s2 = new Service();
		s2.setStockNo(1);
		s2.setName("F**K Service");
		s2.setLayout(new Rectangle(220, 230, 250, 150));

		Employe e4 = new Employe();
		e4.setName("Nameless4");
		e4.setPrenom("P4");
		e4.setLayout(new Rectangle(40, 70, 60, 40));
		s2.addChild(e4);

		Employe e5 = new Employe();
		e5.setName("Nameless5");
		e5.setPrenom("P5");
		e5.setLayout(new Rectangle(170, 100, 60, 40));
		s2.addChild(e5);

		e.addChild(s2);

		setPartName(e.getName());

		return e;
	}

	@Override
	protected void configureGraphicalViewer() {
		log.info("check");
		double[] zoomLevels;
		ArrayList<String> zoomContributions;
		super.configureGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new AppEditPartFactory());

		ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
		viewer.setRootEditPart(rootEditPart);

		ZoomManager manager = rootEditPart.getZoomManager();
		getActionRegistry().registerAction(new ZoomInAction(manager));
		getActionRegistry().registerAction(new ZoomOutAction(manager));
		zoomLevels = new double[] { 0.25, 0.5, 0.75, 1.0, 1.5, 2.0, 2.5, 3.0, 4.0, 5.0, 10.0, 20.0 };

		manager.setZoomLevels(zoomLevels);
		zoomContributions = new ArrayList<String>();
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);

		KeyHandler keyHandler = new KeyHandler();
		keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
				getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		keyHandler.put(KeyStroke.getPressed('+', SWT.KEYPAD_ADD, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		keyHandler.put(KeyStroke.getPressed('-', SWT.KEYPAD_SUBTRACT, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));
		viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.NONE), MouseWheelZoomHandler.SINGLETON);
		viewer.setKeyHandler(keyHandler);

		ContextMenuProvider provider = new AppContextMenuProvider(viewer, getActionRegistry());
		viewer.setContextMenu(provider);
		
		getPaletteViewer().addDragSourceListener(
				new TemplateTransferDragSourceListener(getPaletteViewer()));
	}

	@Override
	protected void initializeGraphicalViewer() {
		GraphicalViewer viewer = getGraphicalViewer();
		model = createEnterprise();
		viewer.setContents(model);
		
		viewer.addDropTargetListener(new MyTemplateTransferDropTargetListener(viewer));
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		log.info("check");

	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {
		log.info(type.getName());
		if (type == ZoomManager.class) {
			return ((ScalableRootEditPart) getGraphicalViewer().getRootEditPart()).getZoomManager();
		} else if (type == IContentOutlinePage.class) {
			return new OutlinePage();
		} else {
			return super.getAdapter(type);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createActions() {
		super.createActions();
		ActionRegistry registry = getActionRegistry();
		
		IAction action = new RenameAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		action = new CopyNodeAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		action = new PasteNodeAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		PaletteRoot root = new PaletteRoot();
		PaletteGroup manipGroup = new PaletteGroup("Select Element(s)");
		root.add(manipGroup);
		SelectionToolEntry selectionToolEntry = new SelectionToolEntry();
		manipGroup.add(selectionToolEntry);
		manipGroup.add(new MarqueeToolEntry());
		root.setDefaultEntry(selectionToolEntry);

		PaletteDrawer drawer = new PaletteDrawer("Components", null);

		PaletteSeparator sep2 = new PaletteSeparator();
		drawer.add(sep2);
		// PaletteGroup instGroup = new PaletteGroup("Create Element(s)");
		// drawer.add(instGroup);
		drawer.add(
				new CombinedTemplateCreationEntry("Service", "Creation d'un service type", new NodeCreationFactory(Service.class),
						AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/services-low.gif"),
						AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/services-high.gif")));
		drawer.add(
				new CombinedTemplateCreationEntry("Employe", "Creation d'un employe model", new NodeCreationFactory(Employe.class),
						AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/employe-low.gif"),
						AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/employe-high.gif")));
		root.add(drawer);
		return root;
	}
}
