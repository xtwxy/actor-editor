package com.wincom.actor.editor.tutogef.policy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.tutogef.command.AbstractLayoutCommand;
import com.wincom.actor.editor.tutogef.command.EmployeChangeLayoutCommand;
import com.wincom.actor.editor.tutogef.command.EmployeCreateCommand;
import com.wincom.actor.editor.tutogef.command.ServiceChangeLayoutCommand;
import com.wincom.actor.editor.tutogef.command.ServiceCreateCommand;
import com.wincom.actor.editor.tutogef.figure.EmployeFigure;
import com.wincom.actor.editor.tutogef.figure.ServiceFigure;
import com.wincom.actor.editor.tutogef.part.EmployePart;
import com.wincom.actor.editor.tutogef.part.EnterprisePart;
import com.wincom.actor.editor.tutogef.part.ServicePart;

public class AppEditLayoutPolicy extends XYLayoutEditPolicy {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		if (request.getType() == REQ_CREATE && getHost() instanceof EnterprisePart) {
			ServiceCreateCommand cmd = new ServiceCreateCommand();
			cmd.setEnterprise(getHost().getModel());
			cmd.setService(request.getNewObject());
			Rectangle constraint = (Rectangle) getConstraintFor(request);
			constraint.x = (constraint.x < 0) ? 0 : constraint.x;
			constraint.y = (constraint.y < 0) ? 0 : constraint.y;
			constraint.width = (constraint.width <= 0) ? ServiceFigure.SERVICE_FIGURE_DEFWIDTH : constraint.width;
			constraint.height = (constraint.height <= 0) ? ServiceFigure.SERVICE_FIGURE_DEFHEIGHT : constraint.height;
			cmd.setLayout(constraint);
			return cmd;
		} else if (request.getType() == REQ_CREATE && getHost() instanceof
				ServicePart) {
			EmployeCreateCommand cmd = new EmployeCreateCommand();
			cmd.setService(getHost().getModel());
			cmd.setEmploye(request.getNewObject());
			Rectangle constraint = (Rectangle)getConstraintFor(request);
			constraint.x = (constraint.x < 0) ? 0 : constraint.x;
			constraint.y = (constraint.y < 0) ? 0 : constraint.y;
			constraint.width = (constraint.width <= 0) ?
			EmployeFigure.EMPLOYE_FIGURE_DEFWIDTH : constraint.width;
			constraint.height = (constraint.height <= 0) ?
			EmployeFigure.EMPLOYE_FIGURE_DEFHEIGHT : constraint.height;
			cmd.setLayout(constraint);
			return cmd;
			}
		return null;
	}

	@Override
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		AbstractLayoutCommand command = null;
		if (child instanceof EmployePart) {
			command = new EmployeChangeLayoutCommand();
		} else if (child instanceof ServicePart) {
			command = new ServiceChangeLayoutCommand();
		}
		command.setModel(child.getModel());
		command.setConstraint((Rectangle) constraint);
		return command;
	}
}
