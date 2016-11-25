package com.wincom.actor.editor.test2.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PortFigure extends Figure {
	public static final int PORT_FIGURE_DEFWIDTH = 32;
	public static final int PORT_FIGURE_DEFHEIGHT = 24;

	Logger log = LoggerFactory.getLogger(this.getClass());

	Label nameLabel = new Label();

	public PortFigure() {
		log.info("check");
		XYLayout layout = new XYLayout();
		setLayoutManager(layout);
		add(nameLabel);
	}

	public void setName(String s) {
		nameLabel.setText(s);
	}

	public void setLayout(Rectangle rect) {
		log.info(rect.toString());
		getParent().setConstraint(this, rect);
	}
}
