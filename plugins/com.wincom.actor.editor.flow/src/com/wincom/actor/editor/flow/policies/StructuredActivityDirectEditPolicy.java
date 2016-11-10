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
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import com.wincom.actor.editor.flow.figures.SubgraphFigure;
import org.eclipse.gef.requests.DirectEditRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * StructuredActivityDirectEditPolicy
 * 
 * @author Daniel Lee
 */
public class StructuredActivityDirectEditPolicy extends
		ActivityDirectEditPolicy {
	Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * @see org.eclipse.gef.EditPolicy#getCommand(Request)
	 */
	public Command getCommand(Request request) {
		log.info("check");
		if (RequestConstants.REQ_DIRECT_EDIT == request.getType()) {
			((DirectEditRequest) request).getLocation();
			return getDirectEditCommand((DirectEditRequest) request);
		}
		return null;
	}

	/**
	 * @see DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
	 */
	protected void showCurrentEditValue(DirectEditRequest request) {
		log.info("check");
		String value = (String) request.getCellEditor().getValue();
		((Label) ((SubgraphFigure) getHostFigure()).getHeader()).setText(value);
		((Label) ((SubgraphFigure) getHostFigure()).getFooter())
				.setText("/" + value);//$NON-NLS-1$

		// hack to prevent async layout from placing the cell editor twice.
		getHostFigure().getUpdateManager().performUpdate();
	}

}
