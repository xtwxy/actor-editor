package com.wincom.actor.editor.test2.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Test2Model implements IPropertySource, Cloneable, Serializable {

	private static final long serialVersionUID = -7289522210862727774L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	transient protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	private Test2Model parent;
	private List<Test2Model> children = new ArrayList<>();

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

	public Test2Model getParent() {
		return parent;
	}

	public void setParent(Test2Model parent) {
		this.parent = parent;
	}

	public List<Test2Model> getChildren() {
		return children;
	}

	public void setChildren(List<Test2Model> children) {
		this.children = children;
	}
	
	public void addChild(Test2Model child) {
		this.children.add(child);
	}
	
	public void addChild(Test2Model child, int index) {
		this.children.add(index, child);
	}
}
