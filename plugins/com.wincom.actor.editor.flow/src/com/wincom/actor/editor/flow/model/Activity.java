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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activity extends FlowElement {
	Logger log = LoggerFactory.getLogger(this.getClass());

	protected static IPropertyDescriptor[] descriptors;

	public static final String NAME = "name"; //$NON-NLS-1$
	static {
		descriptors = new IPropertyDescriptor[] { new TextPropertyDescriptor(
				NAME, "Name") };
	}

	static final long serialVersionUID = 1;
	private List inputs = new ArrayList();
	private String name = "Activity";
	private List outputs = new ArrayList();
	private int sortIndex;

	public Activity() {
		log.info("check");
	}

	public Activity(String s) {
		log.info("check");
		setName(s);
	}

	public void addInput(Transition transition) {
		log.info("check");
		inputs.add(transition);
		fireStructureChange(INPUTS, transition);
	}

	public void addOutput(Transition transtition) {
		log.info("check");
		outputs.add(transtition);
		fireStructureChange(OUTPUTS, transtition);
	}

	public List getIncomingTransitions() {
		log.info("check");
		return inputs;
	}

	public String getName() {
		log.info("check");
		return name;
	}

	public List getOutgoingTransitions() {
		log.info("check");
		return outputs;
	}

	// public List getConnections() {
	// Vector v = (Vector)outputs.clone();
	// Enumeration ins = inputs.elements();
	// while (ins.hasMoreElements())
	// v.addElement(ins.nextElement());
	// return v;
	// }

	/**
	 * Returns useful property descriptors for the use in property sheets. this
	 * supports location and size.
	 * 
	 * @return Array of property descriptors.
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		log.info("check");
		return descriptors;
	}

	/**
	 * Returns an Object which represents the appropriate value for the property
	 * name supplied.
	 * 
	 * @param propName
	 *            Name of the property for which the the values are needed.
	 * @return Object which is the value of the property.
	 */
	public Object getPropertyValue(Object propName) {
		log.info("check");
		if (NAME.equals(propName))
			return getName();
		return super.getPropertyValue(propName);
	}

	public int getSortIndex() {
		log.info("check");
		return sortIndex;
	}

	public void removeInput(Transition transition) {
		log.info("check");
		inputs.remove(transition);
		fireStructureChange(INPUTS, transition);
	}

	public void removeOutput(Transition transition) {
		log.info("check");
		outputs.remove(transition);
		fireStructureChange(OUTPUTS, transition);
	}

	public void setName(String s) {
		log.info("check");
		name = s;
		firePropertyChange(NAME, null, s);
	}

	/**
	 * Sets the value of a given property with the value supplied.
	 * 
	 * @param id
	 *            Name of the parameter to be changed.
	 * @param value
	 *            Value to be set to the given parameter.
	 */
	public void setPropertyValue(Object id, Object value) {
		log.info("check");
		if (id == NAME)
			setName((String) value);
	}

	public void setSortIndex(int i) {
		log.info("check");
		sortIndex = i;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		log.info("check");
		String className = getClass().getName();
		className = className.substring(className.lastIndexOf('.') + 1);
		return className + "(" + name + ")";
	}

}
