package com.wincom.actor.editor.test2.commands;

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.model.ActorModel;
import com.wincom.actor.editor.test2.model.PortModel;

public class DeletePortCommand extends Command {
	Logger log = LoggerFactory.getLogger(this.getClass());

	private PortModel model;
	private ActorModel parentModel;

	@Override
	public void execute() {
		this.parentModel.removeOutput(model);
	}

	public void setModel(PortModel model) {
		this.model = model;
	}

	public void setParentModel(ActorModel model) {
		parentModel = model;
	}

	@Override
	public void undo() {
		this.parentModel.addOutputs(model);
	}

}
