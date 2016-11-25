package com.wincom.actor.editor.test2.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.commands.AbstractLayoutCommand;
import com.wincom.actor.editor.test2.commands.ActorChangeLayoutCommand;
import com.wincom.actor.editor.test2.commands.ActorCreateCommand;
import com.wincom.actor.editor.test2.commands.ProvidedPortChangeLayoutCommand;
import com.wincom.actor.editor.test2.figures.ActorFigure;
import com.wincom.actor.editor.test2.model.ActorModel;
import com.wincom.actor.editor.test2.model.DiagramModel;
import com.wincom.actor.editor.test2.parts.ActorPart;
import com.wincom.actor.editor.test2.parts.ConnectionPart;
import com.wincom.actor.editor.test2.parts.DiagramPart;
import com.wincom.actor.editor.test2.parts.PortPart;
import com.wincom.actor.editor.test2.parts.ProvidedPortPart;
import com.wincom.actor.editor.test2.parts.RequiredPortPart;

public class AppEditLayoutPolicy extends XYLayoutEditPolicy {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		log.info(getHost().toString());
		if (request.getType() == REQ_CREATE && getHost() instanceof DiagramPart) {
			ActorCreateCommand cmd = new ActorCreateCommand();
			cmd.setDiagram((DiagramModel) getHost().getModel());
			cmd.setActor((ActorModel) request.getNewObject());
			
			Rectangle constraint = (Rectangle) getConstraintFor(request);
			constraint.x = (constraint.x < 0) ? 0 : constraint.x;
			constraint.y = (constraint.y < 0) ? 0 : constraint.y;
			constraint.width = (constraint.width <= 0) ? ActorFigure.ACTOR_FIGURE_DEFWIDTH : constraint.width;
			constraint.height = (constraint.height <= 0) ? ActorFigure.ACTOR_FIGURE_DEFHEIGHT : constraint.height;
			cmd.setLayout(constraint);
			return cmd;
		} else if (request.getType() == REQ_CREATE && getHost() instanceof ConnectionPart) {
		} else if (request.getType() == REQ_CREATE && getHost() instanceof PortPart) {
		} else if (request.getType() == REQ_CREATE && getHost() instanceof ProvidedPortPart) {
		} else if (request.getType() == REQ_CREATE && getHost() instanceof RequiredPortPart) {
		}
		return null;
	}

	@Override
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		log.info("child = " + child + ", constraint = " + constraint);
		AbstractLayoutCommand command = null;
		if (child instanceof ActorPart) {
			command = new ActorChangeLayoutCommand();
		} else if (child instanceof ConnectionPart) {
		} else if (child instanceof PortPart) {
		} else if (child instanceof ProvidedPortPart) {
			command = new ProvidedPortChangeLayoutCommand();
		} else if (child instanceof RequiredPortPart) {
		} else {
			return command;
		}
		command.setModel(child.getModel());
		command.setConstraint((Rectangle) constraint);
		return command;
	}
}
