package com.wincom.actor.editor.test1.views;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;

import com.wincom.actor.editor.test1.TestEditorPaletteFactory;
import com.wincom.actor.editor.test1.model.DiagramModel;
import com.wincom.actor.editor.test1.parts.SampleEditPartFactory;

public class SampleEditor extends GraphicalEditorWithPalette {
	private static final Log log = LogFactory.getLog(SampleEditor.class);

	private DiagramModel diagram = new DiagramModel();
	
	public SampleEditor() {
		log.info("check");
		DefaultEditDomain defaultEditDomain = new DefaultEditDomain(this);
		setEditDomain(defaultEditDomain);
	}
	@Override
	protected PaletteRoot getPaletteRoot() {
		log.info("check");
		return TestEditorPaletteFactory.createPalette();
	}
	protected void configureGraphicalViewer() {
		log.info("check");
		super.configureGraphicalViewer();
		getGraphicalViewer().setRootEditPart(new ScalableFreeformRootEditPart());
		getGraphicalViewer().setEditPartFactory(new SampleEditPartFactory());
	}

	@Override
	protected void initializeGraphicalViewer() {
		log.info("check");
		getGraphicalViewer().setContents(diagram);
		getGraphicalViewer().addDropTargetListener(
				new TemplateTransferDropTargetListener(getGraphicalViewer()));
	
	}
	protected void initializePaletteViewer() {
		log.info("check");
		super.initializePaletteViewer();
		getPaletteViewer().addDragSourceListener(
				new TemplateTransferDragSourceListener(getPaletteViewer()));
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		log.info("check");
		
	}

}
