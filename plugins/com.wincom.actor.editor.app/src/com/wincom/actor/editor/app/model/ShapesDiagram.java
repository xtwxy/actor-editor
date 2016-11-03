package com.wincom.actor.editor.app.model;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

public class ShapesDiagram extends ModelElement {

	private static final long serialVersionUID = 1L;

	private static final String CHILD_ADDED_PROP = "CHILD_ADDED_PROP";

	private static final String CHILD_REMOVED_PROP = "CHILD_REMOVED_PROP";
	
	private Collection<Shape> shapes = new Vector<>();
	
	public boolean addChild(Shape s) {
		if(s != null && shapes.add(s)) {
			firePropertyChange(CHILD_ADDED_PROP, null, s);
		}
		return false;
	}
	
	public List<Shape> getChildren() {
		return new Vector<>(shapes);
	}
	
	public boolean removeChild(Shape s) {
		if(s != null && shapes.remove(s)) {
			firePropertyChange(CHILD_REMOVED_PROP, null, s);
			return true;
		}
		return false;
	}
	
	@Override
	public Object getEditableValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPropertyValue(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub

	}

}
