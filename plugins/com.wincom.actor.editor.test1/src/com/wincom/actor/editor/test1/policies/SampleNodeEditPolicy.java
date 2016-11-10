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
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

/**
 * 
 * Created on Jul 17, 2003
 */
public class SampleNodeEditPolicy extends GraphicalNodeEditPolicy {
	private static final Log log = LogFactory.getLog(SampleNodeEditPolicy.class);

	@Override
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		log.info("unimplemented");
		return null;
	}

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		log.info("unimplemented");
		return null;
	}

	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		log.info("unimplemented");
		return null;
	}

	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		log.info("unimplemented");
		return null;
	}

}
