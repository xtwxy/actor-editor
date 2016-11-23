package com.wincom.actor.editor.test2.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class RequiredPortModel extends FigureModel {
	private static final long serialVersionUID = 5306947633961230432L;
	
	public static final String INPUT = "input";
	private static final String OUTPUTS = "outputs";

	private ConnectionModel input;
	private List<ConnectionModel> outputs = new ArrayList<>();
	
	private static final IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new TextPropertyDescriptor(NAME, NAME),
			new TextPropertyDescriptor(PARENT, PARENT),
			new TextPropertyDescriptor(OUTPUTS, OUTPUTS),
			new PropertyDescriptor(INPUT, INPUT)
	};

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

	@Override
	public Object getPropertyValue(Object id) {
		if(INPUT.equals(id)) {
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
	
	@SuppressWarnings("unchecked")
	@Override
	public void setPropertyValue(Object id, Object value) {
		if(INPUT.equals(id)) {
			setInput((ConnectionModel) value);
		} else if(OUTPUTS.equals(id)) {
			setOutputs((List<ConnectionModel>) value);
		} else {
			super.setPropertyValue(id, value);
		}
	}
	
	
	public void addOutputs(ConnectionModel conn) {
		this.outputs.add(conn);
		firePropertyChange(OUTPUTS, null, conn);
	}
	
	public List<ConnectionModel> getOutputs() {
		return outputs;
	}
	
	public void setOutputs(List<ConnectionModel> outputs) {
		List<ConnectionModel> old = this.outputs;
		this.outputs = outputs;
		firePropertyChange(OUTPUTS, old, outputs);
	}

	@Override
	public void resetPropertyValue(Object id) {
	}

	public ConnectionModel getInput() {
		return input;
	}
	
	public void setInput(ConnectionModel newInput) {
		ConnectionModel old = input;
		this.input = newInput;
		firePropertyChange(INPUT, old, newInput);
	}

	@Override
	public List<ElementModel> getChildren() {
		return new ArrayList<>();
	}

}
