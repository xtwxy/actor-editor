package com.wincom.actor.editor.test2.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class QueueModel extends ElementModel {
	private static final long serialVersionUID = -5998400381137891493L;

	private static final IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new TextPropertyDescriptor(NAME, NAME), 
			new TextPropertyDescriptor(PARENT, PARENT) 
		};

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

	@Override
	public void resetPropertyValue(Object id) {

	}

	@Override
	public List<ElementModel> getChildren() {
		return new ArrayList<>();
	}
}
