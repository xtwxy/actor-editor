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

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.parts.DummyLayout;

/**
 * 
 * @author hudsonr Created on Jul 23, 2003
 */
public class ActorFigure extends Figure {
	Logger log = LoggerFactory.getLogger(this.getClass());

	List<PortFigure> provides;
	List<PortFigure> requires;
	IFigure contents;

	public ActorFigure(IFigure header, IFigure footer) {
	}

	public IFigure getContents() {
		return contents;
	}

	/**
	 * @see org.eclipse.draw2d.Figure#getPreferredSize(int, int)
	 */
	public Dimension getPreferredSize(int wHint, int hHint) {
		log.info("check");
		Dimension dim = new Dimension();
		dim.height = 50;
		return dim;
	}

	public void setBounds(Rectangle rect) {
		log.info("check");
		super.setBounds(rect);
		rect = Rectangle.SINGLETON;
		getClientArea(rect);
		contents.setBounds(rect);

	}

	public void setSelected(boolean value) {
		log.info("check");
	}

}
