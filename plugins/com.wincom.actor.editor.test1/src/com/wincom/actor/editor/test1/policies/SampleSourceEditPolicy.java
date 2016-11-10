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
package com.wincom.actor.editor.test1.policies;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

/**
 * @author Daniel Lee
 */
public class SampleSourceEditPolicy extends ContainerEditPolicy {
	private static final Log log = LogFactory.getLog(SampleSourceEditPolicy.class);

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		log.info("unimplemented");
		return null;
	}
}
