package com.wincom.actor.editor.test2.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ElementModel implements IPropertySource, Cloneable, Serializable {

	private static final long serialVersionUID = -7289522210862727774L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	transient protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	protected String name;
	protected ElementModel parent;
	private Rectangle layout;

	public static final String NAME = "name";
	public static final String PARENT = "parent";
	public static final String PROPERTY_LAYOUT = "ElementLayout";
	public static final String PROPERTY_RENAME = "ElementRename";
	public static final String PROPERTY_PARENT = "ElementParent";

	private static final IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new TextPropertyDescriptor(NAME, NAME),
			new TextPropertyDescriptor(PARENT, PARENT)
		};
	
	@Override
	public Object getPropertyValue(Object id) {
		if(NAME.equals(id)) {
			return name;
		} else if(PARENT.equals(id)) {
			return parent;
		}
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		if(NAME.equals(id)) {
			return name != null;
		} else if(PARENT.equals(id)) {
			return parent != null;
		}
		return false;
	}
	
	@Override
	public void setPropertyValue(Object id, Object value) {
		if(NAME.equals(id)) {
			setName((String) value);
		} else if(PARENT.equals(id)) {
			setParent((ElementModel) value);
		}
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

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
	
	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		listeners.firePropertyChange(PROPERTY_RENAME, oldName, this.name);
	}

	public String getName() {
		return this.name;
	}

	public void setLayout(Rectangle newLayout) {
		Rectangle oldLayout = this.layout;
		this.layout = newLayout;
		listeners.firePropertyChange(PROPERTY_LAYOUT, oldLayout, newLayout);
	}

	public Rectangle getLayout() {
		return this.layout;
	}

	public ElementModel getParent() {
		return parent;
	}

	public void setParent(ElementModel newParent) {
		Rectangle oldParent = this.layout;
		this.parent = newParent;
		listeners.firePropertyChange(PROPERTY_PARENT, oldParent, newParent);
	}
	
	@Override
	public Object getEditableValue() {
		return this;
	}

	public abstract List<ElementModel> getChildren();
}
