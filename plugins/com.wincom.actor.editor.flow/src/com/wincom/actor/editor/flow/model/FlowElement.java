/*******************************************************************************
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.wincom.actor.editor.flow.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract public class FlowElement implements IPropertySource, Cloneable,
		Serializable {
	static private Logger log = LoggerFactory.getLogger(FlowElement.class);

	public static final String CHILDREN = "Children", //$NON-NLS-1$
			INPUTS = "inputs", //$NON-NLS-1$
			OUTPUTS = "outputs"; //$NON-NLS-1$

	transient protected PropertyChangeSupport listeners = new PropertyChangeSupport(
			this);
	static final long serialVersionUID = 1;

	public void addPropertyChangeListener(PropertyChangeListener l) {
		log.info("check");
		listeners.addPropertyChangeListener(l);
	}

	protected void firePropertyChange(String prop, Object old, Object newValue) {
		log.info("check");
		listeners.firePropertyChange(prop, old, newValue);
	}

	protected void fireStructureChange(String prop, Object child) {
		log.info("check");
		listeners.firePropertyChange(prop, null, child);
	}

	public Object getEditableValue() {
		log.info("check");
		return this;
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		log.info("check");
		return new IPropertyDescriptor[0];
	}

	public Object getPropertyValue(Object propName) {
		log.info("check");
		return null;
	}

	public boolean isPropertySet(Object propName) {
		log.info("check");
		return true;
	}

	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		log.info("check");
		in.defaultReadObject();
		listeners = new PropertyChangeSupport(this);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		log.info("check");
		listeners.removePropertyChangeListener(l);
	}

	public void resetPropertyValue(Object propName) {
		log.info("check");
	}

	public void setPropertyValue(Object propName, Object val) {
		log.info("check");
	}

}
