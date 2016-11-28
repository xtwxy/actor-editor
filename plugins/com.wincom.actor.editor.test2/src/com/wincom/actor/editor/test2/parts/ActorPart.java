package com.wincom.actor.editor.test2.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.figures.ActorFigure;
import com.wincom.actor.editor.test2.model.ActorModel;
import com.wincom.actor.editor.test2.model.ElementModel;
import com.wincom.actor.editor.test2.policies.ActorEditLayoutPolicy;
import com.wincom.actor.editor.test2.policies.AppDeletePolicy;
import com.wincom.actor.editor.test2.policies.AppRenamePolicy;
import com.wincom.actor.editor.test2.policies.PortDeletePolicy;

public class ActorPart extends ElementPart {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public ActorPart() { 
		log.info("new ActorPart()");
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(ActorModel.ID)) {
			refreshVisuals();
		} else if(evt.getPropertyName().equals(ActorModel.INPUT)) {
			refreshVisuals();
		} else if(evt.getPropertyName().equals(ActorModel.OUTPUTS)) {
			refreshChildren();
		} else {
			super.propertyChange(evt);
		}
	}
	
	@Override
	protected IFigure createFigure() {
		ActorFigure figure = new ActorFigure();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new ActorEditLayoutPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new PortDeletePolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
	}
	
	@Override
	protected void refreshVisuals() {
		ActorFigure figure = (ActorFigure) getFigure();
		ActorModel model = (ActorModel) getModel();
		
		figure.setId(model.getId());
		figure.setName(model.getName());
		
		figure.setBackgroundColor(model.getBackgroundColor());
		figure.setForegroundColor(model.getForegroundColor());
		
		figure.setLayout(model.getLayout());
	}
	
	@Override
	public List<ElementModel> getModelChildren() {
		log.info("check");
		return ((ActorModel) getModel()).getChildren();
	}

}
