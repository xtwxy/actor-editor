package com.wincom.actor.editor.test2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActorModel extends ElementModel {
	private static final long serialVersionUID = -3468137955444207978L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private String id;
	private PortModel input;
	private List<PortModel> outputs = new ArrayList<>();

	public static final String ID = "id";
	public static final String INPUT = "input";
	public static final String OUTPUTS = "outputs";

	private static final IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] { new PropertyDescriptor(ID, ID),
			new TextPropertyDescriptor(NAME, NAME), new PropertyDescriptor(PARENT, PARENT),
			new PropertyDescriptor(INPUT, INPUT), new PropertyDescriptor(OUTPUTS, OUTPUTS) };

	public ActorModel() {
		log.info("new ActorModel()");
		setName("actor");
		input = new PortModel();
		input.setName(INPUT);
		input.setParent(this);
		
		for(int i = 0; i < 3; ++i) {
			PortModel output = new PortModel();
			String outName = "output-" + (i + 1);
			output.setName(outName);
			output.setParent(this);
			outputs.add(output);
		}
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

	public PortModel getInput() {
		return input;
	}

	public void setInput(PortModel port) {
		PortModel old = this.input;
		this.input = port;
		port.setParent(this);
		firePropertyChange(INPUT, old, port);
	}

	private PortModel findOutput(String name) {
		List<PortModel> l = outputs.stream().filter(x -> x.name.equals(name)).collect(Collectors.toList());
		return l.isEmpty() ? null : l.get(0);
	}
	
	public void removeOutput(PortModel port) {
		PortModel old = findOutput(port.name);
		
		if(old != null) {
			old.setParent(null);
		}
		this.outputs.remove(port);
		firePropertyChange(OUTPUTS, old, null);
	}

	public List<PortModel> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<PortModel> newOutputs) {
		List<PortModel> old = outputs;
		outputs = newOutputs;
		firePropertyChange(OUTPUTS, old, newOutputs);
	}

	public void addOutputs(PortModel port) {
		port.setParent(this);
		outputs.add(port);
		firePropertyChange(OUTPUTS, null, port);
	}

	public boolean contains(PortModel port) {
		return this.outputs.contains(port);
	}

	@Override
	public List<ElementModel> getChildren() {
		List<ElementModel> children = new ArrayList<>();
		children.add(input);
		children.addAll(outputs);
		return children;
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (ID.equals(id)) {
			return id;
		} else if (INPUT.equals(id)) {
			return input;
		} else if (OUTPUTS.equals(id)) {
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
		if (ID.equals(id)) {
			setId((String) value);
		} else if (INPUT.equals(id)) {
			setInput((PortModel) value);
		} else if (OUTPUTS.equals(id)) {
			setOutputs((List<PortModel>) value);
		} else {
			super.setPropertyValue(id, value);
		}
	}

	@Override
	public Object clone() {
		ActorModel model = new ActorModel();
		model.setBackgroundColor(getBackgroundColor());
		model.setForegroundColor(getForegroundColor());
		model.setId(getId());
		model.setInput(getInput());
		model.setLayout(new Rectangle(getLayout().x + 10, getLayout().y + 10, getLayout().width, getLayout().height));
		model.setName(getName());

		List<PortModel> outputs = new ArrayList<>();
		for (PortModel e : this.outputs) {
			PortModel port = (PortModel) e.clone();
			port.setLayout(e.getLayout());
			outputs.add(port);
		}

		model.setOutputs(outputs);
		model.setParent(getParent());

		return model;

	}
}
