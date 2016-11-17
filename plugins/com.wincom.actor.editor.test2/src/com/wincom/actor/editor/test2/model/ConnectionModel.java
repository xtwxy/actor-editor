package com.wincom.actor.editor.test2.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionModel extends ElementModel {
	private static final long serialVersionUID = -3254462748778391632L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private ActorModel source;
	private ActorModel target;
	
	
	public ConnectionModel() { 
		log.info("new ConnectionModel()");
	}

	public ActorModel getSource() {
		return source;
	}

	public void setSource(ActorModel source) {
		this.source = source;
	}

	public ActorModel getTarget() {
		return target;
	}

	public void setTarget(ActorModel target) {
		this.target = target;
	}
}
