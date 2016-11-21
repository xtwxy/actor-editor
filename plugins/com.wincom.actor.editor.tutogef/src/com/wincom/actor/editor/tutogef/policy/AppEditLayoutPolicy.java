package com.wincom.actor.editor.tutogef.policy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.wincom.actor.editor.tutogef.command.AbstractLayoutCommand;
import com.wincom.actor.editor.tutogef.command.EmployeChangeLayoutCommand;
import com.wincom.actor.editor.tutogef.command.ServiceChangeLayoutCommand;
import com.wincom.actor.editor.tutogef.part.EmployePart;
import com.wincom.actor.editor.tutogef.part.ServicePart;

public class AppEditLayoutPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		// TODO Auto-generated method stub
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
