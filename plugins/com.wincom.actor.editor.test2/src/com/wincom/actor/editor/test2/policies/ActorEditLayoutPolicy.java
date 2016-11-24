package com.wincom.actor.editor.test2.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.commands.AbstractLayoutCommand;
import com.wincom.actor.editor.test2.commands.PortChangeLayoutCommand;
import com.wincom.actor.editor.test2.commands.PortCreateCommand;
import com.wincom.actor.editor.test2.figures.ActorFigure;
import com.wincom.actor.editor.test2.model.ActorModel;
import com.wincom.actor.editor.test2.model.PortModel;
import com.wincom.actor.editor.test2.parts.ActorPart;
import com.wincom.actor.editor.test2.parts.PortPart;

public class ActorEditLayoutPolicy extends XYLayoutEditPolicy {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		if (request.getType() == REQ_CREATE && getHost() instanceof ActorPart) {
			PortCreateCommand cmd = new PortCreateCommand();
			cmd.setActor((ActorModel) getHost().getModel());
			cmd.setPort((PortModel) request.getNewObject());
			Rectangle constraint = (Rectangle) getConstraintFor(request);
			constraint.x = (constraint.x < 0) ? 0 : constraint.x;
			constraint.y = (constraint.y < 0) ? 0 : constraint.y;
			constraint.width = (constraint.width <= 0) ? ActorFigure.ACTOR_FIGURE_DEFWIDTH : constraint.width;
			constraint.height = (constraint.height <= 0) ? ActorFigure.ACTOR_FIGURE_DEFHEIGHT : constraint.height;
			cmd.setLayout(constraint);
			return cmd;
		}
		return null;
	}

	@Override
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		AbstractLayoutCommand command = null;
		if (child instanceof PortPart) {
			command = new PortChangeLayoutCommand();
		}
		command.setModel(child.getModel());
		command.setConstraint((Rectangle) constraint);
		return command;
	}
}
