package com.wincom.actor.editor.test2.commands;

import org.eclipse.draw2d.geometry.Rectangle;

import com.wincom.actor.editor.test2.model.PortModel;

public class PortChangeLayoutCommand extends AbstractLayoutCommand {
	private PortModel model;
	private Rectangle layout;
	private Rectangle oldLayout;

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
		this.model = (PortModel) model;
		this.oldLayout = ((PortModel) model).getLayout();
	}

	@Override
	public void undo() {
		this.model.setLayout(this.oldLayout);
	}
}
