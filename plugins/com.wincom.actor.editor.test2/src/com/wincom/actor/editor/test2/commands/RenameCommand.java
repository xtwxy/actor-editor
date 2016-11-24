package com.wincom.actor.editor.test2.commands;

import org.eclipse.gef.commands.Command;

import com.wincom.actor.editor.test2.model.ElementModel;

public class RenameCommand extends Command {
	private ElementModel model;
	private String oldName;
	private String newName;

	public void execute() {
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
