package com.wincom.actor.editor.test2.policies;

import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.commands.DeleteConnectionCommand;
import com.wincom.actor.editor.test2.model.ConnectionModel;
import com.wincom.actor.editor.test2.parts.ConnectionPart;

public class PortConnectionEditPolicy extends ConnectionEditPolicy {
	Logger log = LoggerFactory.getLogger(this.getClass());

	private PolylineConnection getConnectionFigure() {
		log.info("check");
		return ((PolylineConnection) ((ConnectionPart) getHost()).getFigure());
	}

	@Override
	protected Command getDeleteCommand(GroupRequest request) {
		log.info(request.getType().toString());
		log.info(getHost().toString());
		DeleteConnectionCommand cmd = new DeleteConnectionCommand();
		ConnectionModel t = (ConnectionModel) getHost().getModel();
		cmd.setConnection(t);
		cmd.setSource(t.getSource());
		cmd.setTarget(t.getTarget());
		return cmd;
	}

	@Override
	public EditPart getTargetEditPart(Request request) {
		log.info(request.getType().toString());
		log.info(getHost().toString());
		if (REQ_CREATE.equals(request.getType())) {
			return getHost();
		} else if (REQ_CONNECTION_START.equals(request.getType())) {
			return getHost();
		}
		return null;
	}

	@Override
	public void eraseTargetFeedback(Request request) {
		log.info(request.getType().toString());
		log.info(getHost().toString());
		if (REQ_CONNECTION_START.equals(request.getType())) {
			getConnectionFigure().setLineWidth(1);
		}
	}

	@Override
	public void showTargetFeedback(Request request) {
		log.info(request.getType().toString());
		log.info(getHost().toString());
		if (REQ_CONNECTION_START.equals(request.getType())) {
			getConnectionFigure().setLineWidth(2);
		}
	}

}
