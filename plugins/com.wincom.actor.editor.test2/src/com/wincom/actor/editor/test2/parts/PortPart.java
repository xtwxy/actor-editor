package com.wincom.actor.editor.test2.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.figures.PortFigure;
import com.wincom.actor.editor.test2.model.ConnectionModel;
import com.wincom.actor.editor.test2.model.PortModel;
import com.wincom.actor.editor.test2.policies.AppRenamePolicy;
import com.wincom.actor.editor.test2.policies.PortDeletePolicy;
import com.wincom.actor.editor.test2.policies.PortNodeEditPolicy;

public class PortPart extends ElementPart {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected IFigure createFigure() {
		return new PortFigure();
	}
	
	@Override
	protected void refreshVisuals() {
		PortFigure figure = (PortFigure) getFigure();
		PortModel model = (PortModel) getModel();

		log.info("port name = " + model.getName());
		figure.setName(model.getName());
		
		figure.setBackgroundColor(model.getBackgroundColor());
		figure.setForegroundColor(model.getForegroundColor());
		
		figure.setLayout(model.getLayout());
	}
	
	@Override
	protected List<ConnectionModel> getModelSourceConnections() {
		log.info("check");
		return ((PortModel)getModel()).getOutgoingConnections();
	}

	@Override
	protected List<ConnectionModel> getModelTargetConnections() {
		log.info("check");
		return ((PortModel)getModel()).getIncomingConnections();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		log.info(evt.toString());

		if(evt.getPropertyName().equals(PortModel.INPUTS)) {
			refreshTargetConnections();
		} else if(evt.getPropertyName().equals(PortModel.OUTPUTS)) {
			refreshSourceConnections();
		} else {
			super.propertyChange(evt);
		}

		((GraphicalEditPart) (getViewer().getContents())).getFigure()
				.revalidate();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new PortNodeEditPolicy());
		//installEditPolicy(EditPolicy.LAYOUT_ROLE, new ActorEditLayoutPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new PortDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
	}


}
