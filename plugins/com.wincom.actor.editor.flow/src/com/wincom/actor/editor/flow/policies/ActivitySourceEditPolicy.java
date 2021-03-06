/*******************************************************************************
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.wincom.actor.editor.flow.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import com.wincom.actor.editor.flow.model.Activity;
import com.wincom.actor.editor.flow.model.StructuredActivity;
import com.wincom.actor.editor.flow.model.commands.AddAndAssignSourceCommand;
import com.wincom.actor.editor.flow.model.commands.CreateAndAssignSourceCommand;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Lee
 */
public class ActivitySourceEditPolicy extends ContainerEditPolicy {
	Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * @see org.eclipse.gef.editpolicies.ContainerEditPolicy#getAddCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command getAddCommand(GroupRequest request) {
		log.info("check");
		CompoundCommand cmd = new CompoundCommand();
		for (int i = 0; i < request.getEditParts().size(); i++) {
			AddAndAssignSourceCommand add = new AddAndAssignSourceCommand();
			add.setParent((StructuredActivity) getHost().getParent().getModel());
			add.setSource((Activity) getHost().getModel());
			add.setChild(((Activity) ((EditPart) request.getEditParts().get(i))
					.getModel()));
			cmd.add(add);
		}
		return cmd;
	}

	/**
	 * @see ContainerEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
	 */
	protected Command getCreateCommand(CreateRequest request) {
		log.info("check");
		CreateAndAssignSourceCommand cmd = new CreateAndAssignSourceCommand();
		cmd.setParent((StructuredActivity) getHost().getParent().getModel());
		cmd.setChild((Activity) request.getNewObject());
		cmd.setSource((Activity) getHost().getModel());
		return cmd;
	}

	/**
	 * @see AbstractEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
	 */
	public EditPart getTargetEditPart(Request request) {
		log.info("check");
		if (REQ_CREATE.equals(request.getType()))
			return getHost();
		if (REQ_ADD.equals(request.getType()))
			return getHost();
		if (REQ_MOVE.equals(request.getType()))
			return getHost();
		return super.getTargetEditPart(request);
	}

}
