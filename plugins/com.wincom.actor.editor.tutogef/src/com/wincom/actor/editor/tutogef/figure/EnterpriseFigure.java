package com.wincom.actor.editor.tutogef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnterpriseFigure extends Figure {
	Logger log = LoggerFactory.getLogger(this.getClass());
	private Label labelName = new Label();
	private Label labelAddress = new Label();
	private Label labelCapital = new Label();
	private XYLayout layout;

	public EnterpriseFigure() {
    	log.info("check");
		layout = new XYLayout();
		setLayoutManager(layout);
		labelName.setForegroundColor(ColorConstants.blue);
		add(labelName);
		setConstraint(labelName, new Rectangle(5, 5, -1, -1));
		labelAddress.setForegroundColor(ColorConstants.lightBlue);
		add(labelAddress);
		setConstraint(labelAddress, new Rectangle(5, 17, -1, -1));
		labelCapital.setForegroundColor(ColorConstants.lightBlue);
		add(labelCapital);
		setConstraint(labelCapital, new Rectangle(5, 30, -1, -1));
		//setForegroundColor(ColorConstants.black);
		//setBorder(new LineBorder(5));
	}

	public void setLayout(Rectangle rect) {
    	log.info("check");
		setBounds(rect);
	}

	public void setName(String text) {
    	log.info("check");
		labelName.setText(text);
	}

	public void setAddress(String text) {
    	log.info("check");
		labelAddress.setText(text);
	}

	public void setCapital(int capital) {
    	log.info("check");
		labelCapital.setText("Capital : " + capital);
	}
}
