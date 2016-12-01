package com.wincom.actor.editor.test2.commands;

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.model.ConnectionModel;
import com.wincom.actor.editor.test2.model.PortModel;

public class DeleteConnectionCommand extends Command {
	Logger log = LoggerFactory.getLogger(this.getClass());

	private PortModel source;
	private PortModel target;
	private ConnectionModel connection;

	public void execute() {
		log.info("check");
		source.removeOutput(connection);
		target.removeInput(connection);
		connection.setSource(null);
		connection.setTarget(null);
	}

	public void setSource(PortModel port) {
		source = port;
	}

	public void setTarget(PortModel port) {
		target = port;
	}

	public void setConnection(ConnectionModel connection) {
		this.connection = connection;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		log.info("check");
		connection.setSource(source);
		connection.setTarget(target);
		source.addOutput(connection);
		target.addInput(connection);
	}

}
