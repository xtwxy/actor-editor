package com.wincom.actor.editor.test2.parts.tree;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.model.ActorModel;
import com.wincom.actor.editor.test2.model.ElementModel;

public abstract class ElementTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void activate() {
		super.activate();
		((ElementModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate() {
		((ElementModel) getModel()).removePropertyChangeListener(this);
		super.deactivate();
	}

	@Override
	public DragTracker getDragTracker(Request req) {
		return new SelectEditPartTracker(this);
	}

	@Override
	public void performRequest(Request req) {
		log.info(req.toString());
		if (req.getType().equals(RequestConstants.REQ_OPEN)) {
			try {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				page.showView(IPageLayout.ID_PROP_SHEET);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(ElementModel.PROPERTY_LAYOUT)) {
			refreshVisuals();
		} else if (evt.getPropertyName().equals(ElementModel.PROPERTY_RENAME)) {
			refreshVisuals();
		} else if(evt.getPropertyName().equals(ActorModel.BACKGROUND_COLOR)) {
			refreshVisuals();
		} else if(evt.getPropertyName().equals(ActorModel.FOREGROUND_COLOR)) {
			refreshVisuals();
		} else if(evt.getPropertyName().equals(ActorModel.NAME)) {
			refreshVisuals();
		} else if(evt.getPropertyName().equals(ActorModel.PARENT)) {
			refreshVisuals();
		} else {
			log.info(evt.toString());
		}
	}
}
