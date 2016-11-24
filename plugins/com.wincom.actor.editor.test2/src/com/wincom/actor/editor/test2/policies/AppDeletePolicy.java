package com.wincom.actor.editor.test2.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wincom.actor.editor.test2.commands.DeleteCommand;
import com.wincom.actor.editor.test2.model.DiagramModel;
import com.wincom.actor.editor.test2.model.ElementModel;

public class AppDeletePolicy extends ComponentEditPolicy {

	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		Object parent = getHost().getParent().getModel();
		if (parent instanceof ElementModel) {
			DeleteCommand command = new DeleteCommand();
			command.setModel((ElementModel) getHost().getModel());
			command.setParentModel((DiagramModel) parent);
			return command;
		} else {
			return null;
		}
	}
}
