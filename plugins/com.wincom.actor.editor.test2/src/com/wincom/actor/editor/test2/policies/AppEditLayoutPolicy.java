package com.wincom.actor.editor.test2.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.commands.AbstractLayoutCommand;
import com.wincom.actor.editor.test2.parts.ActorPart;
import com.wincom.actor.editor.test2.parts.ConnectionPart;
import com.wincom.actor.editor.test2.parts.ProvidedPortPart;
import com.wincom.actor.editor.test2.parts.RequiredPortPart;

public class AppEditLayoutPolicy extends XYLayoutEditPolicy {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		if (request.getType() == REQ_CREATE && getHost() instanceof ActorPart) {
		} else if (request.getType() == REQ_CREATE && getHost() instanceof ConnectionPart) {
		} else if (request.getType() == REQ_CREATE && getHost() instanceof ProvidedPortPart) {
		} else if (request.getType() == REQ_CREATE && getHost() instanceof RequiredPortPart) {
		}
		return null;
	}

	@Override
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		AbstractLayoutCommand command = null;
		if (child instanceof ActorPart) {
		} else if (child instanceof ConnectionPart) {
		} else if (child instanceof ProvidedPortPart) {
		} else if (child instanceof RequiredPortPart) {
		}
		command.setModel(child.getModel());
		command.setConstraint((Rectangle) constraint);
		return command;
	}
}
