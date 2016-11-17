package com.wincom.actor.editor.test2.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PortNodeEditPolicy extends GraphicalNodeEditPolicy {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		log.info("check");
		return null;
	}

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		log.info("check");
		return null;
	}

	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		log.info("check");
		return null;
	}

	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		log.info("check");
		return null;
	}
}
