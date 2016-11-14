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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.flow.model.Activity;
import com.wincom.actor.editor.flow.model.StructuredActivity;
import com.wincom.actor.editor.flow.model.Transition;

/**
 * Handles the deletion of Activities.
 * 
 * @author Daniel Lee
 */
public class DeleteCommand extends Command {
	Logger log = LoggerFactory.getLogger(this.getClass());

	private Activity child;
	private StructuredActivity parent;
	private int index = -1;
	private List sourceConnections = new ArrayList();
	private List targetConnections = new ArrayList();

	private void deleteConnections(Activity a) {
		if (a instanceof StructuredActivity) {
			List children = ((StructuredActivity) a).getChildren();
			for (int i = 0; i < children.size(); i++)
				deleteConnections((Activity) children.get(i));
		}
		sourceConnections.addAll(a.getIncomingTransitions());
		for (int i = 0; i < sourceConnections.size(); i++) {
			Transition t = (Transition) sourceConnections.get(i);
			t.source.removeOutput(t);
			a.removeInput(t);
		}
		targetConnections.addAll(a.getOutgoingTransitions());
		for (int i = 0; i < targetConnections.size(); i++) {
			Transition t = (Transition) targetConnections.get(i);
			t.target.removeInput(t);
			a.removeOutput(t);
		}
	}

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		log.info("check");
		log.info("check");
		primExecute();
	}

	/**
	 * Invokes the execution of this command.
	 */
	protected void primExecute() {
		deleteConnections(child);
		index = parent.getChildren().indexOf(child);
		parent.removeChild(child);
	}

	/**
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		log.info("check");
		primExecute();
	}

	private void restoreConnections() {
		for (int i = 0; i < sourceConnections.size(); i++) {
			Transition t = (Transition) sourceConnections.get(i);
			t.target.addInput(t);
			t.source.addOutput(t);
		}
		sourceConnections.clear();
		for (int i = 0; i < targetConnections.size(); i++) {
			Transition t = (Transition) targetConnections.get(i);
			t.source.addOutput(t);
			t.target.addInput(t);
		}
		targetConnections.clear();
	}

	/**
	 * Sets the child to the passed Activity
	 * 
	 * @param a
	 *            the child
	 */
	public void setChild(Activity a) {
		child = a;
	}

	/**
	 * Sets the parent to the passed StructuredActivity
	 * 
	 * @param sa
	 *            the parent
	 */
	public void setParent(StructuredActivity sa) {
		parent = sa;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		log.info("check");
		parent.addChild(child, index);
		restoreConnections();
	}

}
