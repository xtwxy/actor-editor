package com.wincom.actor.editor.test1.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleModel implements IPropertySource, Cloneable, Serializable {

	private static final long serialVersionUID = -7289522210862727774L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private String sample = "sample";

	transient protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	protected static IPropertyDescriptor[] descriptors;

	public static final String NAME = "name"; //$NON-NLS-1$
	static {
		descriptors = new IPropertyDescriptor[] { new TextPropertyDescriptor(NAME, "Name") };
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		listeners.addPropertyChangeListener(l);
	}

	protected void firePropertyChange(String prop, Object old, Object newValue) {
		listeners.firePropertyChange(prop, old, newValue);
	}

	protected void fireStructureChange(String prop, Object child) {
		listeners.firePropertyChange(prop, null, child);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		listeners.removePropertyChangeListener(l);
	}

	@Override
	public Object getEditableValue() {
		// TODO Auto-generated method stub
		log.info("getEditableValue");
		return sample;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// TODO Auto-generated method stub
		log.info("getPropertyDescriptors");
		return null;
	}

	@Override
	public Object getPropertyValue(Object id) {
		log.info("getPropertyValue with id = " + id);
		return sample;
	}

	@Override
	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		log.info("isPropertySet with id = " + id);
		return true;
	}

	@Override
	public void resetPropertyValue(Object id) {
		// TODO Auto-generated method stub
		log.info("resetPropertyValue with id = " + id);

	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub
		log.info("setPropertyValue with id = " + id + ", value = " + value);

	}

}
