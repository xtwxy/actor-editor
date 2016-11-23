package com.wincom.actor.editor.test2.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActorModel extends ElementModel {
	private static final long serialVersionUID = -3468137955444207978L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private String id;
	private ProvidedPortModel input;
	private Map<String, RequiredPortModel> outputs = new HashMap<>();

	private static final String ID = "id";
	private static final String INPUT = "input";
	private static final String OUTPUTS = "outputs";

	private static final IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new PropertyDescriptor(ID, ID), 
			new TextPropertyDescriptor(NAME, NAME),
			new PropertyDescriptor(PARENT, PARENT),
			new PropertyDescriptor(INPUT, INPUT), 
			new PropertyDescriptor(OUTPUTS, OUTPUTS) 
		};

	public ActorModel() {
		log.info("new ActorModel()");
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProvidedPortModel getInput() {
		return input;
	}

	public void setInput(ProvidedPortModel port) {
		ProvidedPortModel old = this.input;
		this.input = port;
		firePropertyChange(INPUT, old, port);
	}

	public RequiredPortModel getOutputs(String name) {
		return outputs.get(name);
	}

	public Map<String, RequiredPortModel> getOutputs() {
		return outputs;
	}

	public void setOutputs(Map<String, RequiredPortModel> newOutputs) {
		Map<String, RequiredPortModel> old = outputs;
		outputs = newOutputs;
		firePropertyChange(OUTPUTS, old, newOutputs);
	}

	public void setOutputs(String name, RequiredPortModel port) {
		RequiredPortModel old = this.outputs.get(name);
		this.outputs.put(name, port);
		firePropertyChange(OUTPUTS, old, port);
	}

	@Override
	public List<ElementModel> getChildren() {
		List<ElementModel> children = new ArrayList<>();
		children.add(input);
		children.addAll(outputs.values());
		return children;
	}

	@Override
	public Object getPropertyValue(Object id) {
		if(ID.equals(id)) {
			return id;
		} else if(INPUT.equals(id)) {
			return input;
		} else if(OUTPUTS.equals(id)) {
			return outputs;
		}
		return super.getPropertyValue(id);
	}

	@Override
	public boolean isPropertySet(Object id) {
		return getPropertyValue(id) != null;
	}

	@Override
	public void resetPropertyValue(Object id) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void setPropertyValue(Object id, Object value) {
		if(ID.equals(id)) {
			setId((String) value);
		} else if(INPUT.equals(id)) {
			setInput((ProvidedPortModel) value);
		} else if(OUTPUTS.equals(id)) {
			setOutputs((Map<String, RequiredPortModel>) value);
		} else {
			super.setPropertyValue(id, value);
		}
	}
}
