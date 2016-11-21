package com.wincom.actor.editor.tutogef.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wincom.actor.editor.tutogef.command.DeleteCommand;
import com.wincom.actor.editor.tutogef.model.Node;

public class AppDeletePolicy extends ComponentEditPolicy {

	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		Object parent = getHost().getParent().getModel();
		if (parent instanceof Node) {
			DeleteCommand command = new DeleteCommand();
			command.setModel(getHost().getModel());
			command.setParentModel(parent);
			return command;
		} else {
			return null;
		}
	}
}
