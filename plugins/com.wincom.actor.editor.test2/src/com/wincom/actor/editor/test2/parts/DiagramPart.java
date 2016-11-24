package com.wincom.actor.editor.test2.parts;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import com.wincom.actor.editor.test2.figures.ActorFigure;
import com.wincom.actor.editor.test2.figures.DiagramFigure;
import com.wincom.actor.editor.test2.model.ActorModel;
import com.wincom.actor.editor.test2.model.DiagramModel;
import com.wincom.actor.editor.test2.policies.AppDeletePolicy;
import com.wincom.actor.editor.test2.policies.AppEditLayoutPolicy;

public class DiagramPart extends ElementPart {

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(DiagramModel.AGGREGATE_ID)) {
			refreshVisuals();
		} else if(evt.getPropertyName().equals(DiagramModel.CHILDREN)) {
			refreshVisuals();
		} else {
			super.propertyChange(evt);
		}
	}

	@Override
	protected IFigure createFigure() {
		return new DiagramFigure();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
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

}
