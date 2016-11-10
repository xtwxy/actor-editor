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

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import com.wincom.actor.editor.flow.model.Activity;
import com.wincom.actor.editor.flow.model.StructuredActivity;
import com.wincom.actor.editor.flow.model.commands.OrphanChildCommand;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ActivityContainerEditPolicy
 * 
 * @author Daniel Lee
 */
public class ActivityContainerEditPolicy extends ContainerEditPolicy {
	Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * @see ContainerEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
	 */
	protected Command getCreateCommand(CreateRequest request) {
		log.info("check");
		return null;
	}

	/**
	 * @see org.eclipse.gef.editpolicies.ContainerEditPolicy#getOrphanChildrenCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	protected Command getOrphanChildrenCommand(GroupRequest request) {
		log.info("check");
		List parts = request.getEditParts();
		CompoundCommand result = new CompoundCommand();
		for (int i = 0; i < parts.size(); i++) {
			OrphanChildCommand orphan = new OrphanChildCommand();
			orphan.setChild((Activity) ((EditPart) parts.get(i)).getModel());
			orphan.setParent((StructuredActivity) getHost().getModel());
			result.add(orphan);
		}
		return result.unwrap();
	}

}
