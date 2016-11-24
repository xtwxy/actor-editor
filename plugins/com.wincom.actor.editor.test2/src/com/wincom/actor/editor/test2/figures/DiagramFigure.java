package com.wincom.actor.editor.test2.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiagramFigure extends Figure {
	Logger log = LoggerFactory.getLogger(this.getClass());
	private Label nameLabel = new Label();
	private Label aggregateIdLabel = new Label();
	private XYLayout layout;

	public DiagramFigure() {
		log.info("check");
		layout = new XYLayout();
		setLayoutManager(layout);
		
		nameLabel.setForegroundColor(ColorConstants.blue);
		setConstraint(nameLabel, new Rectangle(5, 5, -1, -1));
		add(nameLabel);
		
		aggregateIdLabel.setForegroundColor(ColorConstants.blue);
		setConstraint(aggregateIdLabel, new Rectangle(5, 18, -1, -1));
		add(aggregateIdLabel);
	}

	public void setLayout(Rectangle rect) {
		log.info("check");
		setBounds(rect);
	}

	public void setName(String text) {
		log.info("check");
		nameLabel.setText(text);
	}

	public void setAggregateId(String aggregateId) {
		this.aggregateIdLabel.setText(aggregateId);
	}

}
