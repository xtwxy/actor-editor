package com.wincom.actor.editor.test2.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class ProvidedPortModel extends FigureModel {

	private static final long serialVersionUID = 2969510135967969883L;
	public static final String INPUTS = "inputs";
	
	private static final IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new TextPropertyDescriptor(INPUTS, INPUTS)
	};

	private List<ConnectionModel> inputs = new ArrayList<>();
	
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
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
	
}
