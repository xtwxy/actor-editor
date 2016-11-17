package com.wincom.actor.editor.test2.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActorModel extends FigureModel {
	private static final long serialVersionUID = -3468137955444207978L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private String id;
	private String name;
	private ProvidedPortModel input;
	private Map<String, RequiredPortModel> outputs = new HashMap<>();

	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String INPUT = "input";
	private static final String OUTPUTS = "outputs";

	private static final IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new TextPropertyDescriptor(ID, ID), new TextPropertyDescriptor(NAME, NAME),
			new TextPropertyDescriptor(INPUT, INPUT), new TextPropertyDescriptor(OUTPUTS, OUTPUTS) };
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setOutputs(String name, RequiredPortModel port) {
		RequiredPortModel old = this.outputs.get(name);
		this.outputs.put(name, port);
		firePropertyChange(INPUT, old, port);
	}

	@Override
	public List<ElementModel> getChildren() {
		List<ElementModel> children = new ArrayList<>();
		children.add(input);
		children.addAll(outputs.values());
		return children;
	}
}
