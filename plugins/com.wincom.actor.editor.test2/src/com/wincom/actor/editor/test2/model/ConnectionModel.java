package com.wincom.actor.editor.test2.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionModel extends ElementModel {
	private static final long serialVersionUID = -3254462748778391632L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static final String SOURCE = "source";
	public static final String TARGET = "target";
	
	private static final IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new TextPropertyDescriptor(NAME, NAME),
			new PropertyDescriptor(PARENT, PARENT),
			new TextPropertyDescriptor(SOURCE, SOURCE),
			new TextPropertyDescriptor(SOURCE, SOURCE)
	};

	private ElementModel source;
	private ElementModel target;
	
	public ConnectionModel() { 
		log.info("new ConnectionModel()");
	}
	
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

	public ElementModel getSource() {
		return source;
	}

	public void setSource(ElementModel newSource) {
		ElementModel old = source;
		this.source = newSource;
		firePropertyChange(SOURCE, old, newSource);
	}

	public ElementModel getTarget() {
		return target;
	}

	public void setTarget(ElementModel newTarget) {
		ElementModel old = target;
		this.target = newTarget;
		firePropertyChange(TARGET, old, newTarget);
	}

	@Override
	public Object getPropertyValue(Object id) {
		if(SOURCE.equals(id)) {
			return source;
		} else if(TARGET.equals(id)) {
			return target;
		} else {
			return super.getPropertyValue(id);
		}
	}

	@Override
	public boolean isPropertySet(Object id) {
		return getPropertyValue(id) != null;
	}

	@Override
	public void resetPropertyValue(Object id) {
		
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if(SOURCE.equals(id)) {
			setSource((ElementModel) value);
		} else if(TARGET.equals(id)) {
			setTarget((ElementModel) value);
		} else {
			super.setPropertyValue(id, value);
		}
	}

	@Override
	public List<ElementModel> getChildren() {
		return new ArrayList<>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConnectionModel other = (ConnectionModel) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}
}
