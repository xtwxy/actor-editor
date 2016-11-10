/*******************************************************************************
 * Copyright (c) 2003, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.wincom.actor.editor.flow.policies;

import org.eclipse.draw2d.Label;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import com.wincom.actor.editor.flow.model.Activity;
import com.wincom.actor.editor.flow.model.commands.RenameActivityCommand;
import org.eclipse.gef.requests.DirectEditRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EditPolicy for the direct editing of Activity names.
 * 
 * @author Daniel Lee
 */
public class ActivityDirectEditPolicy extends DirectEditPolicy {
	Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * @see DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
	 */
	protected Command getDirectEditCommand(DirectEditRequest request) {
		log.info("check");
		RenameActivityCommand cmd = new RenameActivityCommand();
		cmd.setSource((Activity) getHost().getModel());
		cmd.setOldName(((Activity) getHost().getModel()).getName());
		cmd.setName((String) request.getCellEditor().getValue());
		return cmd;
	}

	/**
	 * @see DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
	 */
	protected void showCurrentEditValue(DirectEditRequest request) {
		log.info("check");
		String value = (String) request.getCellEditor().getValue();
		((Label) getHostFigure()).setText(value);
		// hack to prevent async layout from placing the cell editor twice.
		// getHostFigure().getUpdateManager().performUpdate();
	}

}
