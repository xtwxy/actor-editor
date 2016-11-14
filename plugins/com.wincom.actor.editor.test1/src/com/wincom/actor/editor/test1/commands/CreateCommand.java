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
package com.wincom.actor.editor.test1.commands;

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Daniel Lee
 */
public class CreateCommand extends Command {
	Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		log.info("check");
	}

	public void undo() {
		log.info("check");
	}

}
