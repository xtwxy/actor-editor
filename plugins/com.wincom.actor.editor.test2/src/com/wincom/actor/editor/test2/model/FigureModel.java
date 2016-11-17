package com.wincom.actor.editor.test2.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class FigureModel extends ElementModel {
	private static final long serialVersionUID = -3254462748778391632L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public FigureModel() { 
		log.info("new FigureModel()");
	}
}
