package com.wincom.actor.editor.test2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.ColorPropertyDescriptor;
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

	private static final IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] { 
			new TextPropertyDescriptor(ID, ID),
			new TextPropertyDescriptor(NAME, NAME),
			new PropertyDescriptor(PARENT, PARENT),
			new PropertyDescriptor(INPUT, INPUT), 
			new PropertyDescriptor(OUTPUTS, OUTPUTS),
			new ColorPropertyDescriptor(BACKGROUND_COLOR, BACKGROUND_COLOR),
			new ColorPropertyDescriptor(FOREGROUND_COLOR, FOREGROUND_COLOR)
			};

	public ActorModel() {
		log.info("new ActorModel()");
		setId("actor-" + Double.valueOf(Math.random() * 1000).intValue());
		setName("actor-" + Double.valueOf(Math.random() * 1000).intValue());
		this.input = new PortModel();
		this.input.setName(INPUT);
		this.input.setParent(this);
		
		for(int i = 0; i < 3; ++i) {
			PortModel output = new PortModel();
			String outName = "output-" + (i + 1);
			output.setName(outName);
			output.setParent(this);
			outputs.add(output);
		}
	}
	
	@Override
	public void setLayout(Rectangle newLayout) {
		log.info("check");
		super.setLayout(newLayout);
		layoutChildren(newLayout);
	}
	
	public void layoutChildren() {
		layoutChildren(getLayout());
	}

	public void layoutChildren(Rectangle newLayout) {
		int P = 0;
		int W = newLayout.width;
		int H = newLayout.height;
		layoutInput(P, H);
	
		layoutOutputs(P, W, H);
	}

	private void layoutInput(int P, int H) {
		int h = input.getLayout().height;
		int w = input.getLayout().width;
		int x = 0;
		int y = (H - 2*P)/2 + P - h/2;
		input.setLayout(new Rectangle(x, y, w, h));
	}

	public void layoutOutputs() {
		int P = 0;
		int W = getLayout().width;
		int H = getLayout().height;
		layoutOutputs(P, W, H);
	}
	
	private void layoutOutputs(int P, int W, int H) {
		int h;
		int w;
		int x;
		int y;
		final int N = outputs.size();
		for(int i = 0; i < N; ++i) {
			PortModel out = outputs.get(i);
			h = out.getLayout().height;
			w = out.getLayout().width;
			x = W - w;
			y = (H - 2*P)/(N + 1) * (i + 1) + P - h/2;
			out.setLayout(new Rectangle(x, y, w, h));
		}
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

	public String getId() {
		log.info("id = " + this.id);
		return this.id;
	}

	public void setId(String newId) {
		String old = id;
		this.id = newId;
		firePropertyChange(ID, old, newId);
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
		layoutOutputs();
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
		layoutOutputs();
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
			return getId();
		} else if (INPUT.equals(id)) {
			return getInput();
		} else if (OUTPUTS.equals(id)) {
			return getOutputs();
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
