package com.wincom.actor.editor.test2.policies;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

import com.wincom.actor.editor.test2.commands.RenameCommand;
import com.wincom.actor.editor.test2.model.ElementModel;

public class AppRenamePolicy extends AbstractEditPolicy {
	public Command getCommand(Request request) {
		if (request.getType().equals("rename"))
			return createRenameCommand(request);
		return null;
	}

	protected Command createRenameCommand(Request renameRequest) {
		RenameCommand command = new RenameCommand();
		command.setModel((ElementModel) getHost().getModel());
		command.setNewName((String) renameRequest.getExtendedData().get("newName"));
		return command;
	}
}
