package com.wincom.actor.editor.test2.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.wincom.actor.editor.test2.model.ActorModel;
import com.wincom.actor.editor.test2.model.PortModel;

public class PortCreateCommand extends Command {
	private ActorModel actor;
	private PortModel port;

	public PortCreateCommand() {
		super();
	}

	public void setActor(ActorModel actor) {
		this.actor = actor;
	}

	public void setPort(PortModel port) {
		this.port = port;
	}


	public void setLayout(Rectangle r) {
		if (port == null)
			return;
		port.setLayout(r);
	}

	@Override
	public boolean canExecute() {
		if (port == null || actor == null)
			return false;
		return true;
	}

	@Override
	public void execute() {
		actor.addOutputs(port);
	}

	@Override
	public boolean canUndo() {
		if (actor == null || port == null)
			return false;
		return actor.contains(port);
	}

	@Override
	public void undo() {
		actor.removeOutput(port);
	}
}
