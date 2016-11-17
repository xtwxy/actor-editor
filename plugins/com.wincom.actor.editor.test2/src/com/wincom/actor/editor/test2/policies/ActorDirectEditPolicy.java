package com.wincom.actor.editor.test2.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActorDirectEditPolicy extends DirectEditPolicy {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		log.info("check");
		return null;
	}

	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		log.info("check");

	}

}
