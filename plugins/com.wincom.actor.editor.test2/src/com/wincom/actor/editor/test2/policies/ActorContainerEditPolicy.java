package com.wincom.actor.editor.test2.policies;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActorContainerEditPolicy extends ContainerEditPolicy {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		log.info("check");
		return null;
	}
	
	protected Command getOrphanChildrenCommand(GroupRequest request) {
		log.info("check");
		List parts = request.getEditParts();
		CompoundCommand result = new CompoundCommand();
		return result.unwrap();
	}

}
