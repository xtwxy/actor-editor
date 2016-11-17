package com.wincom.actor.editor.test2.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.parts.DummyLayout;

public class ProvidedPortFigure extends Figure {
	Logger log = LoggerFactory.getLogger(this.getClass());

	Label contents;

	public ProvidedPortFigure(IFigure header, IFigure footer) {
		log.info("check");
		contents = new Label();
		contents.setLayoutManager(new DummyLayout());
		add(contents);
	}

	public IFigure getContents() {
		log.info("check");
		return contents;
	}

	/**
	 * @see org.eclipse.draw2d.Figure#getPreferredSize(int, int)
	 */
	public Dimension getPreferredSize(int wHint, int hHint) {
		log.info("check");
		Dimension dim = new Dimension();
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
	}

	public void setSelected(boolean value) {
		log.info("check");
	}

}
