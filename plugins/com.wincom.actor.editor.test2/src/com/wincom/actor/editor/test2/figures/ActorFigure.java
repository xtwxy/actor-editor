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
package com.wincom.actor.editor.test2.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author hudsonr Created on Jul 23, 2003
 */
public class ActorFigure extends Figure {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	public static final int ACTOR_FIGURE_DEFWIDTH = 0;
	public static final int ACTOR_FIGURE_DEFHEIGHT = 0;

	private Label idLabel = new Label();
	private Label nameLabel = new Label();

	public ActorFigure() {
		XYLayout layout = new XYLayout();
		setLayoutManager(layout);
		
		idLabel.setForegroundColor(ColorConstants.black);
		add(idLabel, ToolbarLayout.ALIGN_TOPLEFT);
		//setConstraint(id, new Rectangle(5, 17, -1, -1));	
		
		nameLabel.setForegroundColor(ColorConstants.darkGray);
		add(nameLabel, ToolbarLayout.ALIGN_CENTER);
		
		setForegroundColor(new Color(null, (new Double(Math.random() * 128)).intValue(),
				(new Double(Math.random() * 128)).intValue(), (new Double(Math.random() * 128)).intValue()));
		setBackgroundColor(new Color(null, (new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128));
		
		setBorder(new LineBorder(1));
		setOpaque(true);
	}

	public void setId(String id) {
		this.idLabel.setText(id);
	}
	
	public void setName(String name) {
		this.nameLabel.setText(name);
	}
	
	public void setLayout(Rectangle rect) {
		log.info(rect.toString());
		getParent().setConstraint(this, rect);
	}

}
