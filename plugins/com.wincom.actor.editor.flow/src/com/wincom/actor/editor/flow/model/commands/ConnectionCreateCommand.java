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

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.flow.model.Activity;
import com.wincom.actor.editor.flow.model.Transition;

/**
 * @author Daniel Lee
 */
public class ConnectionCreateCommand extends Command {
	Logger log = LoggerFactory.getLogger(this.getClass());

	/** The Transistion between source and target Activities **/
	protected Transition transition;
	/** The source Activity **/
	protected Activity source;
	/** The target Activity **/
	protected Activity target;

	/**
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		log.info("check");
		if (source.equals(target))
			return false;

		// Check for existence of connection already
		List transistions = source.getOutgoingTransitions();
		for (int i = 0; i < transistions.size(); i++) {
			if (((Transition) transistions.get(i)).target.equals(target))
				return false;
		}
		return true;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		log.info("check");
		transition = new Transition(source, target);
	}

	/**
	 * Returns the source Activity
	 * 
	 * @return the source
	 */
	public Activity getSource() {
		return source;
	}

	/**
	 * Returns the target Activity
	 * 
	 * @return the target
	 */
	public Activity getTarget() {
		return target;
	}

	/**
	 * Returns the Transistion between the source and target Activities
	 * 
	 * @return the transistion
	 */
	public Transition getTransition() {
		return transition;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		log.info("check");
		source.addOutput(transition);
		target.addInput(transition);
	}

	/**
	 * Sets the source Activity
	 * 
	 * @param activity
	 *            the source Activity
	 */
	public void setSource(Activity activity) {
		source = activity;
	}

	/**
	 * Sets the Transistion between the source and target Activities
	 * 
	 * @param transition
	 *            the transistion
	 */
	public void setTransition(Transition transition) {
		this.transition = transition;
	}

	/**
	 * Sets the target Activity
	 * 
	 * @param activity
	 *            the target
	 */
	public void setTarget(Activity activity) {
		target = activity;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		log.info("check");
		source.removeOutput(transition);
		target.removeInput(transition);
	}

}
