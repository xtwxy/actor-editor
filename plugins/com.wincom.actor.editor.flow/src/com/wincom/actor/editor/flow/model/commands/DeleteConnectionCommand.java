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
package com.wincom.actor.editor.flow.model.commands;

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.flow.model.Activity;
import com.wincom.actor.editor.flow.model.Transition;

/**
 * Handles the deletion of connections between Activities.
 * 
 * @author Daniel Lee
 */
public class DeleteConnectionCommand extends Command {
	Logger log = LoggerFactory.getLogger(this.getClass());

	private Activity source;
	private Activity target;
	private Transition transition;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		log.info("check");
		source.removeOutput(transition);
		target.removeInput(transition);
		transition.source = null;
		transition.target = null;
	}

	/**
	 * Sets the source activity
	 * 
	 * @param activity
	 *            the source
	 */
	public void setSource(Activity activity) {
		source = activity;
	}

	/**
	 * Sets the target activity
	 * 
	 * @param activity
	 *            the target
	 */
	public void setTarget(Activity activity) {
		target = activity;
	}

	/**
	 * Sets the transition
	 * 
	 * @param transition
	 *            the transition
	 */
	public void setTransition(Transition transition) {
		this.transition = transition;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		log.info("check");
		transition.source = source;
		transition.target = target;
		source.addOutput(transition);
		target.addInput(transition);
	}

}
