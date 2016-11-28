package com.wincom.actor.editor.test2.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.wincom.actor.editor.test2.commands.DeleteCommand;
import com.wincom.actor.editor.test2.commands.DeletePortCommand;
import com.wincom.actor.editor.test2.model.ActorModel;
import com.wincom.actor.editor.test2.model.DiagramModel;
import com.wincom.actor.editor.test2.model.ElementModel;
import com.wincom.actor.editor.test2.model.PortModel;

public class AppDeletePolicy extends ComponentEditPolicy {

	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		Object parent = getHost().getParent().getModel();
		if (parent instanceof DiagramModel) {
			DeleteCommand command = new DeleteCommand();
			command.setModel((ElementModel) getHost().getModel());
			command.setParentModel((DiagramModel) parent);
			return command;
		} else if (parent instanceof ActorModel) {
			DeletePortCommand command = new DeletePortCommand();
			command.setModel((PortModel) getHost().getModel());
			command.setParentModel((ActorModel) parent);
			return command;
		} else {
			return null;
		}
	}
}
