package com.wincom.actor.editor.test2.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.commands.DeleteCommand;

public class ActorEditPolicy extends ComponentEditPolicy {
	Logger log = LoggerFactory.getLogger(this.getClass());

	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		log.info("check");
		return new DeleteCommand();
	}

}
