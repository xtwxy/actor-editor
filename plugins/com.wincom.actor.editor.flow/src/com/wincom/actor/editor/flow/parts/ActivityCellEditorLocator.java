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
package com.wincom.actor.editor.flow.parts;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CellEditorLocator for Activities.
 * 
 * @author Daniel Lee
 */
public class ActivityCellEditorLocator implements CellEditorLocator {
	Logger log = LoggerFactory.getLogger(this.getClass());
	private Label label;

	/**
	 * Creates a new ActivityCellEditorLocator for the given Label
	 * 
	 * @param label
	 *            the Label
	 */
	public ActivityCellEditorLocator(Label label) {
		log.info("check");
		setLabel(label);
	}

	/**
	 * @see CellEditorLocator#relocate(org.eclipse.jface.viewers.CellEditor)
	 */
	public void relocate(CellEditor celleditor) {
		log.info("check");
		Text text = (Text) celleditor.getControl();
		Point pref = text.computeSize(-1, -1);
		Rectangle rect = label.getTextBounds().getCopy();
		label.translateToAbsolute(rect);
		text.setBounds(rect.x - 1, rect.y - 1, pref.x + 1, pref.y + 1);
	}

	/**
	 * Returns the Label figure.
	 * 
	 * @return the Label
	 */
	protected Label getLabel() {
		log.info("check");
		return label;
	}

	/**
	 * Sets the label.
	 * 
	 * @param label
	 *            The label to set
	 */
	protected void setLabel(Label label) {
		log.info("check");
		this.label = label;
	}

}
