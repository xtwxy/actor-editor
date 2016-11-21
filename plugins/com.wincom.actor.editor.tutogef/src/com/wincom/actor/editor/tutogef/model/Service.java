package com.wincom.actor.editor.tutogef.model;

import org.eclipse.swt.graphics.Color;

public class Service extends Node {
	public static final String PROPERTY_COLOR = "ServiceColor";
	public static final String PROPERTY_FLOOR = "ServiceFloor";

	private int etage;
	private Color color;

	public Service() {
		this.color = createRandomColor();
	}

	public void setEtage(int etage) {
		this.etage = etage;
	}

	public int getEtage() {
		return this.etage;
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
}
