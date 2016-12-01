package com.wincom.actor.editor.test2.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.commands.ConnectionCreateCommand;
import com.wincom.actor.editor.test2.commands.ReconnectSourceCommand;
import com.wincom.actor.editor.test2.commands.ReconnectTargetCommand;
import com.wincom.actor.editor.test2.model.ConnectionModel;
import com.wincom.actor.editor.test2.model.PortModel;
import com.wincom.actor.editor.test2.parts.ElementPart;

public class PortNodeEditPolicy extends GraphicalNodeEditPolicy {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		log.info("check");
		ConnectionCreateCommand cmd = (ConnectionCreateCommand) request.getStartCommand();
		cmd.setTarget(getPortModel());
		return cmd;
	}

	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		log.info("check");
		ConnectionCreateCommand cmd = new ConnectionCreateCommand();
		cmd.setSource(getPortModel());
		request.setStartCommand(cmd);
		return cmd;
	}

	protected ElementPart getActivityPart() {
		log.info("check");
		return (ElementPart) getHost();
	}

	protected PortModel getPortModel() {
		log.info("check");
		return (PortModel) getHost().getModel();
	}

	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		log.info("check");
		ReconnectSourceCommand cmd = new ReconnectSourceCommand();
		cmd.setConnection((ConnectionModel) request.getConnectionEditPart().getModel());
		cmd.setSource(getPortModel());
		return cmd;
	}

	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		log.info("check");
		ReconnectTargetCommand cmd = new ReconnectTargetCommand();
		cmd.setTransition((ConnectionModel) request.getConnectionEditPart().getModel());
		cmd.setTarget(getPortModel());
		return cmd;
	}
}
