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

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.flow.parts.DummyLayout;

/**
 * 
 * @author hudsonr Created on Jul 23, 2003
 */
public class SubgraphFigure extends Figure {
	Logger log = LoggerFactory.getLogger(this.getClass());

	IFigure contents;
	IFigure footer;
	IFigure header;

	public SubgraphFigure(IFigure header, IFigure footer) {
		log.info("check");
		contents = new Figure();
		contents.setLayoutManager(new DummyLayout());
		add(contents);
		add(this.header = header);
		add(this.footer = footer);
	}

	public IFigure getContents() {
		log.info("check");
		return contents;
	}

	public IFigure getFooter() {
		log.info("check");
		return footer;
	}

	public IFigure getHeader() {
		log.info("check");
		return header;
	}

	/**
	 * @see org.eclipse.draw2d.Figure#getPreferredSize(int, int)
	 */
	public Dimension getPreferredSize(int wHint, int hHint) {
		log.info("check");
		Dimension dim = new Dimension();
		dim.width = getFooter().getPreferredSize().width;
		dim.width += getInsets().getWidth();
		dim.height = 50;
		return dim;
	}

	public void setBounds(Rectangle rect) {
		log.info("check");
		super.setBounds(rect);
		rect = Rectangle.SINGLETON;
		getClientArea(rect);
		contents.setBounds(rect);
		Dimension size = footer.getPreferredSize();
		footer.setLocation(rect.getBottomLeft().translate(0, -size.height));
		footer.setSize(size);

		size = header.getPreferredSize();
		header.setSize(size);
		header.setLocation(rect.getLocation());
	}

	public void setSelected(boolean value) {
		log.info("check");
	}

}
