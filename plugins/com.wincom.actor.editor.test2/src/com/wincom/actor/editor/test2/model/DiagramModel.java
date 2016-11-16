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

}
