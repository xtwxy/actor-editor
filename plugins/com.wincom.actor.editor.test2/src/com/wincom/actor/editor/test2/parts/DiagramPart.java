package com.wincom.actor.editor.test2.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import com.wincom.actor.editor.test2.figures.DiagramFigure;
import com.wincom.actor.editor.test2.model.DiagramModel;
import com.wincom.actor.editor.test2.model.ElementModel;
import com.wincom.actor.editor.test2.policies.AppDeletePolicy;
import com.wincom.actor.editor.test2.policies.AppEditLayoutPolicy;

public class DiagramPart extends ElementPart {

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		log.info(evt.toString());
		if (evt.getPropertyName().equals(DiagramModel.AGGREGATE_ID)) {
			refreshVisuals();
		} else if(evt.getPropertyName().equals(DiagramModel.CHILDREN)) {
			refreshChildren();
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
		DiagramFigure figure = (DiagramFigure) getFigure();
		DiagramModel model = (DiagramModel) getModel();
		
		figure.setAggregateId(model.getAggregateId());
		figure.setName(model.getName());
		
		figure.setBackgroundColor(model.getBackgroundColor());
		figure.setForegroundColor(model.getForegroundColor());
		
		figure.setLayout(model.getLayout());
	}
	
	@Override
	public List<ElementModel> getModelChildren() {
		log.info("check");
		return ((DiagramModel) getModel()).getChildren();
	}

}
