package com.wincom.actor.editor.test2.parts;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.policies.PortConnectionEditPolicy;

public class ConnectionPart extends AbstractConnectionEditPart {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void createEditPolicies() {
		log.info("check");
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new ConnectionEndpointEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ROLE,
				new PortConnectionEditPolicy());
	}
	
	@Override
	protected IFigure createFigure() {
		log.info("check");
		PolylineConnection conn = (PolylineConnection) super.createFigure();
		conn.setConnectionRouter(new BendpointConnectionRouter());

		conn.setTargetDecoration(new PolygonDecoration());
		return conn;
	}
	
	@Override
	public void setSelected(int value) {
		log.info("check");
		super.setSelected(value);
		if (value != EditPart.SELECTED_NONE)
			((PolylineConnection) getFigure()).setLineWidth(2);
		else
			((PolylineConnection) getFigure()).setLineWidth(1);
	}

}
