package com.wincom.actor.editor.tutogef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class ServiceFigure extends Figure {
	private Label labelName = new Label();
	private Label labelStockNo = new Label();
	
	public static final int SERVICE_FIGURE_DEFWIDTH = 250;
	public static final int SERVICE_FIGURE_DEFHEIGHT = 150;
	
	public ServiceFigure() {
		XYLayout layout = new XYLayout();
		setLayoutManager(layout);
		labelName.setForegroundColor(ColorConstants.darkGray);
		add(labelName, ToolbarLayout.ALIGN_CENTER);
		setConstraint(labelName, new Rectangle(5, 17, -1, -1));
		labelStockNo.setForegroundColor(ColorConstants.black);
		add(labelStockNo, ToolbarLayout.ALIGN_CENTER);
		setConstraint(labelStockNo, new Rectangle(5, 5, -1, -1));
		setForegroundColor(new Color(null, (new Double(Math.random() * 128)).intValue(),
				(new Double(Math.random() * 128)).intValue(), (new Double(Math.random() * 128)).intValue()));
		setBackgroundColor(new Color(null, (new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128));

		setBorder(new LineBorder(1));
		setOpaque(true);
	}

	public void setName(String text) {
		labelName.setText(text);
	}

	public void setStockNo(int stockNo) {
		labelStockNo.setText("StockNo:" + stockNo);
	}

	public void setLayout(Rectangle rect) {
		getParent().setConstraint(this, rect);
	}
}
