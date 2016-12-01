package com.wincom.actor.editor.test2.commands;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.model.ConnectionModel;
import com.wincom.actor.editor.test2.model.PortModel;

public class ConnectionCreateCommand extends Command {
	Logger log = LoggerFactory.getLogger(this.getClass());

	protected ConnectionModel connection;
	protected PortModel source;
	protected PortModel target;

	public boolean canExecute() {
		log.info("check");
		if (source.equals(target))
			return false;

		List<ConnectionModel> outgoingConnections = source.getOutgoingConnections();
		for (ConnectionModel conn : outgoingConnections) {
			if(conn.getTarget().equals(target))
				return false;
		}
		log.info("canExecute() returns true.");
		return true;
	}

	public void execute() {
		log.info("check");
		connection = new ConnectionModel(source, target);
	}

	public PortModel getSource() {
		return source;
	}

	public PortModel getTarget() {
		return target;
	}

	public ConnectionModel getTransition() {
		return connection;
	}

	public void redo() {
		log.info("check");
		source.addOutput(connection);
		target.addInput(connection);
	}

	public void setSource(PortModel activity) {
		source = activity;
	}

	public void setTransition(ConnectionModel transition) {
		this.connection = transition;
	}

	public void setTarget(PortModel activity) {
		target = activity;
	}

	public void undo() {
		log.info("check");
		source.removeOutput(connection);
		target.removeInput(connection);
	}

}
