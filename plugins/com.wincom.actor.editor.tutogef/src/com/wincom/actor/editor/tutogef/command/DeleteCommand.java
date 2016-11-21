package com.wincom.actor.editor.tutogef.command;

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.tutogef.model.Node;

public class DeleteCommand extends Command {
	Logger log = LoggerFactory.getLogger(this.getClass());

	private Node model;
	private Node parentModel;

	@Override
	public void execute() {
		this.parentModel.removeChild(model);
	}

	public void setModel(Object model) {
		this.model = (Node) model;
	}

	public void setParentModel(Object model) {
		parentModel = (Node) model;
	}

	@Override
	public void undo() {
		this.parentModel.addChild(model);
	}
}
