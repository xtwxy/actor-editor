package com.wincom.actor.editor.test2.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.model.ActorModel;
import com.wincom.actor.editor.test2.model.ElementModel;

public abstract class ElementPart extends AbstractGraphicalEditPart implements PropertyChangeListener {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void activate() {
		super.activate();
		((ElementModel) getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate() {
		super.deactivate();
		((ElementModel) getModel()).removePropertyChangeListener(this);
	}

	@Override
	public void performRequest(Request req) {
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
		log.info(evt.toString());
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
		}
	}

}
