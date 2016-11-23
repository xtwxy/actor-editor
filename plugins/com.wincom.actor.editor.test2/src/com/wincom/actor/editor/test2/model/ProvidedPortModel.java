package com.wincom.actor.editor.test2.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class ProvidedPortModel extends ElementModel {

	private static final long serialVersionUID = 2969510135967969883L;
	public static final String INPUTS = "inputs";
	public static final String OUTPUT = "output";

	private ConnectionModel output;
	private List<ConnectionModel> inputs = new ArrayList<>();
	
	private static final IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new TextPropertyDescriptor(NAME, NAME),
			new TextPropertyDescriptor(PARENT, PARENT),
			new TextPropertyDescriptor(OUTPUT, OUTPUT),
			new PropertyDescriptor(INPUTS, INPUTS)
		};
	
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

	@Override
	public Object getPropertyValue(Object id) {
		if(INPUTS.equals(id)) {
			return inputs;
		} else if(OUTPUT.equals(id)) {
			return output;
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
		if(INPUTS.equals(id)) {
			setInputs((List<ConnectionModel>) value);
		} else if(OUTPUT.equals(id)) {
			setOutput((ConnectionModel) value);
		} else {
			super.setPropertyValue(id, value);
		}
	}

	public ConnectionModel getOutput() {
		return output;
	}

	public void setOutput(ConnectionModel newOutput) {
		ConnectionModel old = output;
		this.output = newOutput;
		firePropertyChange(OUTPUT, old, newOutput);
	}

	public void addInput(ConnectionModel conn) {
		this.inputs.add(conn);
		firePropertyChange(INPUTS, null, conn);
	}

	public List<ConnectionModel> getInputs() {
		return inputs;
	}

	public void setInputs(List<ConnectionModel> inConn) {
		List<ConnectionModel> old = this.inputs;
		this.inputs = inConn;
		firePropertyChange(INPUTS, old, inConn);
	}

	@Override
	public void resetPropertyValue(Object id) {
		
	}

	@Override
	public List<ElementModel> getChildren() {
		return new ArrayList<>();
	}
	
}
