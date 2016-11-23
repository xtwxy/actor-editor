package com.wincom.actor.editor.test2.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiagramModel extends ElementModel {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = 2121730343288390039L;
	
	public DiagramModel() { 
		log.info("new DiagramModel()");
	}

	@Override
	public Object getEditableValue() {
		return null;
	}

	@Override
	public Object getPropertyValue(Object id) {
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
		
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		
	}

	@Override
	public List<ElementModel> getChildren() {
		return null;
	}

}
