package com.wincom.actor.editor.test2.model;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class FigureModel extends Test2Model {
	private static final long serialVersionUID = -3254462748778391632L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	protected final Properties properties = new Properties();
	@Override
	public Object getEditableValue() {
		log.debug("unimplemented");
		return null;
	}

	@Override
	public Object getPropertyValue(Object id) {
		return properties.get(id);
	}

	@Override
	public boolean isPropertySet(Object id) {
		return properties.containsKey(id);
	}

	@Override
	public void resetPropertyValue(Object id) {
		properties.remove(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		Object old = properties.get(id);
		properties.put(id, value);
		firePropertyChange(id.toString(), old, value);
	}
}
