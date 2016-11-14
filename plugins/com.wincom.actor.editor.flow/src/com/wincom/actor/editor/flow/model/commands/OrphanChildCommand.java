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
package com.wincom.actor.editor.flow.model.commands;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.flow.model.Activity;
import com.wincom.actor.editor.flow.model.StructuredActivity;

/**
 * OrphanChildCommand
 * 
 * @author Daniel Lee
 */
public class OrphanChildCommand extends Command {
	Logger log = LoggerFactory.getLogger(this.getClass());

	private StructuredActivity parent;
	private Activity child;
	private int index;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		log.info("check");
		List children = parent.getChildren();
		index = children.indexOf(child);
		parent.removeChild(child);
	}

	/**
	 * Sets the child to the passed Activity
	 * 
	 * @param child
	 *            the child
	 */
	public void setChild(Activity child) {
		this.child = child;
	}

	/**
	 * Sets the parent to the passed StructuredActivity
	 * 
	 * @param parent
	 *            the parent
	 */
	public void setParent(StructuredActivity parent) {
		this.parent = parent;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		log.info("check");
		parent.addChild(child, index);
	}

}
