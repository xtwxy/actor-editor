package com.wincom.actor.editor.test2.model;

import java.util.ArrayList;
import java.util.List;

public class PortModel extends ElementModel {
	private static final long serialVersionUID = 2969510135967969883L;
	private ConnectionModel assocInterface;
	public static final String INTERFACE = "interface";

	public ConnectionModel getAssocInterface() {
		return assocInterface;
	}

	public void setAssocInterface(ConnectionModel newInterface) {
		ConnectionModel oldInterface = newInterface;
		this.assocInterface = newInterface;
		firePropertyChange(INTERFACE, oldInterface, newInterface);
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (INTERFACE.equals(id)) {
			return assocInterface;
		}
		return super.getPropertyValue(id);
	}

	@Override
	public boolean isPropertySet(Object id) {
		if (INTERFACE.equals(id)) {
			return assocInterface != null;
		}
		return super.isPropertySet(id);
	}

	@Override
	public void resetPropertyValue(Object id) {
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (INTERFACE.equals(id)) {
			setAssocInterface((ConnectionModel) value);
		} else {
			super.setPropertyValue(id, value);
		}
	}

	@Override
	public List<ElementModel> getChildren() {
		return new ArrayList<>();
	}
}
