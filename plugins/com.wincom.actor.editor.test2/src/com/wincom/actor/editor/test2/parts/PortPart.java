package com.wincom.actor.editor.test2.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import com.wincom.actor.editor.test2.figures.PortFigure;
import com.wincom.actor.editor.test2.model.PortModel;
import com.wincom.actor.editor.test2.policies.AppRenamePolicy;
import com.wincom.actor.editor.test2.policies.PortDeletePolicy;

public class PortPart extends ElementPart {

	@Override
	protected IFigure createFigure() {
		return new PortFigure();
	}
	@Override
	protected void refreshVisuals() {
		PortFigure figure = (PortFigure) getFigure();
		PortModel model = (PortModel) getModel();
		
		figure.setName(model.getName());
		
		figure.setBackgroundColor(model.getBackgroundColor());
		figure.setForegroundColor(model.getForegroundColor());
		
		figure.setLayout(model.getLayout());
	}

	@Override
	protected void createEditPolicies() {
		//installEditPolicy(EditPolicy.LAYOUT_ROLE, new ActorEditLayoutPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new PortDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
	}


}
