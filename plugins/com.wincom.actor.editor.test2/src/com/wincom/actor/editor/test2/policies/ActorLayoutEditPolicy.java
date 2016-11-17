package com.wincom.actor.editor.test2.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActorLayoutEditPolicy extends LayoutEditPolicy {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		log.info("check");
		return null;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		log.info("check");
		return null;
	}

	@Override
	protected Command getMoveChildrenCommand(Request request) {
		log.info("check");
		return null;
	}

}
