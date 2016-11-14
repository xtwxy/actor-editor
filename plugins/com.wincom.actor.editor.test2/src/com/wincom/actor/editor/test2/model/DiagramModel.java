package com.wincom.actor.editor.test2.model;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiagramModel extends Test2Model {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = 2121730343288390039L;

	@Override
	public Object getEditableValue() {
		log.debug("unimplemented");
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
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

}
