package com.wincom.actor.editor.test2.commands;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.model.ConnectionModel;
import com.wincom.actor.editor.test2.model.PortModel;

public class ReconnectSourceCommand extends Command {
	Logger log = LoggerFactory.getLogger(this.getClass());

	protected PortModel source;
	protected PortModel target;
	protected ConnectionModel connection;
	protected PortModel oldSource;

	public boolean canExecute() {
		log.info("check");
		if (connection.getTarget().equals(source))
			return false;

		List<ConnectionModel> connections = source.getOutgoingConnections();
		for (ConnectionModel trans : connections) {
			if (trans.getTarget().equals(target) && !trans.getSource().equals(oldSource))
				return false;
		}
		return true;
	}

	public void execute() {
		log.info("check");
		if (source != null) {
			oldSource.removeOutput(connection);
			connection.setSource(source);
			source.addOutput(connection);
		}
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

	public void setSource(PortModel port) {
		source = port;
	}

	public void setTarget(PortModel port) {
		target = port;
	}

	public void setConnection(ConnectionModel conn) {
		connection = conn;
		target = conn.getTarget();
		oldSource = conn.getSource();
	}

	public void undo() {
		log.info("check");
		source.removeOutput(connection);
		connection.setSource(oldSource);
		oldSource.addOutput(connection);
	}

}
