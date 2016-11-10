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
package com.wincom.actor.editor.flow.figures;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.flow.FlowImages;

/**
 * @author hudsonr Created on Jul 21, 2003
 */
public class EndTag extends Label {
	Logger log = LoggerFactory.getLogger(this.getClass());

	static final Border BORDER = new MarginBorder(2, 0, 2, 2);

	/**
	 * Creates a new StartTag
	 * 
	 * @param name
	 *            the text to display in this StartTag
	 */
	public EndTag(String name) {
		log.info("check");
		setIconTextGap(8);
		setText(name);
		setIcon(FlowImages.GEAR);
		setBorder(BORDER);
	}

	protected void paintFigure(Graphics g) {
		log.info("check");
		super.paintFigure(g);
		Rectangle r = getTextBounds();

		r.resize(0, -1).expand(1, 1);
		g.drawLine(r.x, r.y, r.right(), r.y); // Top line
		g.drawLine(r.x, r.bottom(), r.right(), r.bottom()); // Bottom line
		g.drawLine(r.right(), r.bottom(), r.right(), r.y); // Right line

		g.drawLine(r.x - 7, r.y + r.height / 2, r.x, r.y);
		g.drawLine(r.x - 7, r.y + r.height / 2, r.x, r.bottom());
	}

}
