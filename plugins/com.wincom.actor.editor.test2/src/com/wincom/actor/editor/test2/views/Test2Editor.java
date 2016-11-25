package com.wincom.actor.editor.test2.views;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Viewport;
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

import com.wincom.actor.editor.test2.Activator;
import com.wincom.actor.editor.test2.ElementCreationFactory;
import com.wincom.actor.editor.test2.actions.ActorContextMenuProvider;
import com.wincom.actor.editor.test2.actions.CopyElementAction;
import com.wincom.actor.editor.test2.actions.MyTemplateTransferDropTargetListener;
import com.wincom.actor.editor.test2.actions.PasteElementAction;
import com.wincom.actor.editor.test2.actions.RenameAction;
import com.wincom.actor.editor.test2.model.ActorModel;
import com.wincom.actor.editor.test2.model.DiagramModel;
import com.wincom.actor.editor.test2.model.PortModel;
import com.wincom.actor.editor.test2.parts.ActorEditPartFactory;
import com.wincom.actor.editor.test2.parts.tree.ActorTreeEditPartFactory;

public class Test2Editor extends GraphicalEditorWithPalette {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	DiagramModel diagram;
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
			getViewer().setEditPartFactory(new ActorTreeEditPartFactory());
			getViewer().setContents(diagram);
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

			ContextMenuProvider provider = new ActorContextMenuProvider(getViewer(), getActionRegistry());
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

	public Test2Editor() {
		setEditDomain(new DefaultEditDomain(this));
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
		
		drawer.add(
				new CombinedTemplateCreationEntry("Component", "Create Component", new ElementCreationFactory(ActorModel.class),
						AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/component-low.gif"),
						AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/component-high.gif")));
		drawer.add(
				new CombinedTemplateCreationEntry("Port", "Create Port", new ElementCreationFactory(PortModel.class),
						AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/port-low.gif"),
						AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/port-high.gif")));
		root.add(drawer);
		log.info("components added.");
		return root;
	}


	@Override
	protected void configureGraphicalViewer() {
		log.info("check");
		double[] zoomLevels;
		ArrayList<String> zoomContributions;
		super.configureGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new ActorEditPartFactory());

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

		ContextMenuProvider provider = new ActorContextMenuProvider(viewer, getActionRegistry());
		viewer.setContextMenu(provider);
		
		getPaletteViewer().addDragSourceListener(
				new TemplateTransferDragSourceListener(getPaletteViewer()));
	}

	@Override
	protected void initializeGraphicalViewer() {
		GraphicalViewer viewer = getGraphicalViewer();
		diagram = createDiagram();
		viewer.setContents(diagram);
		
		viewer.addDropTargetListener(new MyTemplateTransferDropTargetListener(viewer));
	}

	private DiagramModel createDiagram() {
		return new DiagramModel();
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
		
		action = new CopyElementAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		
		action = new PasteElementAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
	}

}
