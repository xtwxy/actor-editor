package com.wincom.actor.editor.test2.commands;

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.model.ElementModel;

public class RenameCommand extends Command {
	Logger log = LoggerFactory.getLogger(this.getClass());
	private ElementModel model;
	private String oldName;
	private String newName;

	public void execute() {
		log.info("check");
		this.oldName = model.getName();
		this.model.setName(newName);
	}

	public void setModel(ElementModel model) {
		this.model = model;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public void undo() {
		this.model.setName(oldName);
	}
}
