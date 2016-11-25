package com.wincom.actor.editor.test2.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.model.ActorModel;
import com.wincom.actor.editor.test2.model.DiagramModel;

public class ActorCreateCommand extends Command {
	Logger log = LoggerFactory.getLogger(this.getClass());
	private DiagramModel diagram;
	private ActorModel actor;

	public ActorCreateCommand() {
		super();
	}

	public void setDiagram(DiagramModel diagram) {
		this.diagram = diagram;
	}

	public void setActor(ActorModel actor) {
		this.actor = actor;
	}


	public void setLayout(Rectangle r) {
		if (actor == null)
			return;
		actor.setLayout(r);
	}

	@Override
	public boolean canExecute() {
		if (actor == null || diagram == null)
			return false;
		return true;
	}

	@Override
	public void execute() {
		log.info("check");
		diagram.addChild(actor);
	}

	@Override
	public boolean canUndo() {
		if (actor == null || diagram == null)
			return false;
		return diagram.contains(actor);
	}

	@Override
	public void undo() {
		diagram.removeChild(actor);
	}
}
