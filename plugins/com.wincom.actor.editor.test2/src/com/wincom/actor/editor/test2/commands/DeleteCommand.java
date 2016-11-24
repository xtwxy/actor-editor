package com.wincom.actor.editor.test2.commands;

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.model.DiagramModel;
import com.wincom.actor.editor.test2.model.ElementModel;

public class DeleteCommand extends Command {
	Logger log = LoggerFactory.getLogger(this.getClass());

	private ElementModel model;
	private DiagramModel parentModel;

	@Override
	public void execute() {
		this.parentModel.removeChild(model);
	}

	public void setModel(ElementModel model) {
		this.model = model;
	}

	public void setParentModel(DiagramModel model) {
		parentModel = model;
	}

	@Override
	public void undo() {
		this.parentModel.addChild(model);
	}

}
