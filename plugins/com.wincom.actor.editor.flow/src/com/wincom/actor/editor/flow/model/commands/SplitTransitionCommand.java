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

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.flow.model.Activity;
import com.wincom.actor.editor.flow.model.StructuredActivity;
import com.wincom.actor.editor.flow.model.Transition;

/**
 * SplitTransitionCommand
 * 
 * @author Daniel Lee
 */
public class SplitTransitionCommand extends Command {
	Logger log = LoggerFactory.getLogger(this.getClass());

	private StructuredActivity parent;
	private Activity oldSource;
	private Activity oldTarget;
	private Transition transition;

	private Activity newActivity;
	private Transition newIncomingTransition;
	private Transition newOutgoingTransition;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		log.info("check");
		oldSource.removeOutput(transition);
		oldTarget.removeInput(transition);
		parent.addChild(newActivity);
		newIncomingTransition = new Transition(oldSource, newActivity);
		newOutgoingTransition = new Transition(newActivity, oldTarget);
	}

	/**
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		log.info("check");
		oldSource.addOutput(newIncomingTransition);
		oldTarget.addInput(newOutgoingTransition);
		newActivity.addInput(newIncomingTransition);
		newActivity.addOutput(newOutgoingTransition);
		parent.addChild(newActivity);
		oldSource.removeOutput(transition);
		oldTarget.removeInput(transition);
	}

	/**
	 * Sets the parent Activity. The new Activity will be added as a child to
	 * the parent.
	 * 
	 * @param activity
	 *            the parent
	 */
	public void setParent(StructuredActivity activity) {
		log.info("check");
		parent = activity;
	}

	/**
	 * Sets the transition to be "split".
	 * 
	 * @param transition
	 *            the transition to be "split".
	 */
	public void setTransition(Transition transition) {
		log.info("check");
		this.transition = transition;
		oldSource = transition.source;
		oldTarget = transition.target;
	}

	/**
	 * Sets the Activity to be added.
	 * 
	 * @param activity
	 *            the new activity
	 */
	public void setNewActivity(Activity activity) {
		log.info("check");
		newActivity = activity;
		newActivity.setName("a " + (parent.getChildren().size() + 1));
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		log.info("check");
		oldSource.removeOutput(newIncomingTransition);
		oldTarget.removeInput(newOutgoingTransition);
		newActivity.removeInput(newIncomingTransition);
		newActivity.removeOutput(newOutgoingTransition);
		parent.removeChild(newActivity);
		oldSource.addOutput(transition);
		oldTarget.addInput(transition);
	}

}
