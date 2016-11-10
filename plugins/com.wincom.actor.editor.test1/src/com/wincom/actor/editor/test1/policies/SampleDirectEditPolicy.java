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
package com.wincom.actor.editor.test1.policies;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

/**
 * EditPolicy for the direct editing of Activity names.
 * 
 * @author Daniel Lee
 */
public class SampleDirectEditPolicy extends DirectEditPolicy {
	private static final Log log = LogFactory.getLog(SampleDirectEditPolicy.class);

	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		log.info("unimplemented.");
		return null;
	}

	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		log.info("unimplemented.");
		
	}

}
