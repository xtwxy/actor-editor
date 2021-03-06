package com.wincom.actor.editor.tutogef.model;

import java.util.Iterator;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class Service extends Node {
	public static final String PROPERTY_COLOR = "ServiceColor";
	public static final String PROPERTY_FLOOR = "ServiceFloor";
	public static final String PROPERTY_ETAGE = "ServiceStockNo";

	private int stockNo;
	private Color color;

	public Service() {
		this.color = createRandomColor();
	}

	public void setStockNo(int stockNo) {
		int oldStockNo = this.stockNo;
		this.stockNo = stockNo;
		getListeners().firePropertyChange(PROPERTY_ETAGE, oldStockNo, stockNo);
	}

	public int getStockNo() {
		return this.stockNo;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		Color oldColor = this.color;
		this.color = color;
		getListeners().firePropertyChange(PROPERTY_COLOR, oldColor, color);
	}

	private Color createRandomColor() { /** Just for Fun :) **/
		return new Color(null, (new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128, (new Double(Math.random() * 128)).intValue() + 128);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Service srv = new Service();
		srv.setColor(this.color);
		srv.setStockNo(this.stockNo);
		srv.setName(this.getName());
		srv.setParent(this.getParent());
		srv.setLayout(new Rectangle(getLayout().x + 10, getLayout().y + 10, getLayout().width, getLayout().height));
		Iterator<Node> it = this.getChildrenArray().iterator();
		while (it.hasNext()) {
			Node node = it.next();
			if (node instanceof Employe) {
				Employe child = (Employe) node;
				Node clone = (Node) child.clone();
				srv.addChild(clone);
				clone.setLayout(child.getLayout());
			}
		}
		return srv;
	}
}
