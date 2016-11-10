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
package com.wincom.actor.editor.flow.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Structured activity is an activity whose execution is determined by some
 * internal structure.
 * 
 * @author hudsonr
 */
public class StructuredActivity extends Activity {
	Logger log = LoggerFactory.getLogger(this.getClass());

	static final long serialVersionUID = 1;

	private static int count;

	protected List children = new ArrayList();

	public StructuredActivity() {
		log.info("check");
	}

	public void addChild(Activity child) {
		log.info("check");
		addChild(child, -1);
	}

	public void addChild(Activity child, int index) {
		log.info("check");
		if (index >= 0)
			children.add(index, child);
		else
			children.add(child);
		fireStructureChange(CHILDREN, child);
	}

	public List getChildren() {
		log.info("check");
		return children;
	}

	public String getNewID() {
		log.info("check");
		return Integer.toString(count++);
	}

	public void removeChild(FlowElement child) {
		log.info("check");
		children.remove(child);
		fireStructureChange(CHILDREN, child);
	}

}
