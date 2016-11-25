package com.wincom.actor.editor.test2.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.figures.ProvidedPortFigure;
import com.wincom.actor.editor.test2.model.ProvidedPortModel;
import com.wincom.actor.editor.test2.policies.ActorEditLayoutPolicy;
import com.wincom.actor.editor.test2.policies.AppRenamePolicy;

public class ProvidedPortPart extends ElementPart {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected IFigure createFigure() {
		return new ProvidedPortFigure();
	}
	
	@Override
	protected void refreshVisuals() {
		log.info("check");
		ProvidedPortFigure figure = (ProvidedPortFigure) getFigure();
		ProvidedPortModel model = (ProvidedPortModel) getModel();
		
		figure.setName(model.getName());
		
		figure.setBackgroundColor(model.getBackgroundColor());
		figure.setForegroundColor(model.getForegroundColor());
		
		figure.setLayout(model.getLayout());
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new ActorEditLayoutPolicy());
		//installEditPolicy(EditPolicy.COMPONENT_ROLE, new PortDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
	}

}
