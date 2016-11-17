package com.wincom.actor.editor.test2.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ElementModel implements IPropertySource, Cloneable, Serializable {

	private static final long serialVersionUID = -7289522210862727774L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	transient protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	protected final Properties properties = new Properties();

	private ElementModel parent;
	
	public ElementModel() { 
		log.info("new ElementModel()");
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		listeners.addPropertyChangeListener(l);
	}

	protected void firePropertyChange(String prop, Object old, Object newValue) {
		listeners.firePropertyChange(prop, old, newValue);
	}

	protected void fireStructureChange(String prop, Object child) {
		log.debug("prop = " + prop
				+ ", child = " + child);
		listeners.firePropertyChange(prop, null, child);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		listeners.removePropertyChangeListener(l);
	}
	
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[0];
	}

	public ElementModel getParent() {
		return parent;
	}

	public void setParent(ElementModel parent) {
		this.parent = parent;
	}

	public List<ElementModel> getChildren() {
		throw new RuntimeException("unimplemented.");
	}
	
	@Override
	public Object getEditableValue() {
		log.debug("unimplemented");
		return this;
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
