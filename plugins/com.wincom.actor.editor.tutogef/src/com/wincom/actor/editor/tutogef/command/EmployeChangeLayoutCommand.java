package com.wincom.actor.editor.tutogef.command;

import org.eclipse.draw2d.geometry.Rectangle;

import com.wincom.actor.editor.tutogef.model.Employe;

public class EmployeChangeLayoutCommand extends AbstractLayoutCommand {
	private Employe model;
	private Rectangle layout;

	@Override
	public void execute() {
		model.setLayout(layout);
	}

	@Override
	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	@Override
	public void setModel(Object model) {
		this.model = (Employe) model;
	}
}
