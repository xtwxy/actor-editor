package com.wincom.actor.editor.test2.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class RequiredPortModel extends FigureModel {
	private static final long serialVersionUID = 5306947633961230432L;
	
	private static final String OUTPUTS = "outputs";
	private List<ConnectionModel> outputs = new ArrayList<>();
	
	private static final IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new TextPropertyDescriptor(OUTPUTS, OUTPUTS)
	};

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
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
	
}
