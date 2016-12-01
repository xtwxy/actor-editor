package com.wincom.actor.editor.test2.commands;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.model.ConnectionModel;
import com.wincom.actor.editor.test2.model.PortModel;

public class ReconnectTargetCommand extends Command {
	Logger log = LoggerFactory.getLogger(this.getClass());

	protected PortModel source;
	protected PortModel target;
	protected ConnectionModel connection;
	protected PortModel oldTarget;

	public boolean canExecute() {
		log.info("check");
		if (connection.getSource().equals(target))
			return false;

		List<ConnectionModel> outgoingConnections = source.getOutgoingConnections();
		for (ConnectionModel conn : outgoingConnections) {
			if (conn.getTarget().equals(target) && !conn.getTarget().equals(oldTarget))
				return false;
		}
		return true;
	}

	public void execute() {
		log.info("check");
		if (target != null) {
			oldTarget.removeInput(connection);
			connection.setTarget(target);
			target.addInput(connection);
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

	public void setTransition(ConnectionModel trans) {
		connection = trans;
		source = trans.getSource();
		oldTarget = trans.getTarget();
	}

	public void undo() {
		log.info("check");
		target.removeInput(connection);
		connection.setTarget(oldTarget);
		oldTarget.addInput(connection);
	}

}
