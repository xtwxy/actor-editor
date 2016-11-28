package com.wincom.actor.editor.test2.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiagramModel extends ElementModel {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private static final long serialVersionUID = 2121730343288390039L;

	private String aggregateId;
	private List<ElementModel> children = new ArrayList<>();
	public static final String AGGREGATE_ID = "aggregateId";
	public static final String CHILDREN = "children";
	private static final IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new TextPropertyDescriptor(AGGREGATE_ID, AGGREGATE_ID),
			new TextPropertyDescriptor(NAME, NAME),
			new PropertyDescriptor(PARENT, PARENT),
			new TextPropertyDescriptor(CHILDREN, CHILDREN)
		};
	
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

	public DiagramModel() {
		this.aggregateId = "to be changed";
		this.name = "to be changed";
		log.info("new DiagramModel()");
	}

	@Override
	public Object getPropertyValue(Object id) {
		if(AGGREGATE_ID.equals(id)) {
			return aggregateId;
		} else if(CHILDREN.equals(id)) {
			return children;
		} else {
			return super.getPropertyValue(id);
		}
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
		if(AGGREGATE_ID.equals(id)) {
			setAggregateId((String) value);
		} else if(CHILDREN.equals(id)) {
			setChildren((List<ElementModel>) value);
		} else {
			super.setPropertyValue(id, value);
		}
	}

	@Override
	public List<ElementModel> getChildren() {
		return children;
	}

	public String getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(String newAggregateId) {
		String old = this.aggregateId;
		this.aggregateId = newAggregateId;
		firePropertyChange(AGGREGATE_ID, old, newAggregateId);
	}

	public void setChildren(List<ElementModel> newChildren) {
		List<ElementModel> old = this.children;
		this.children = newChildren;
		firePropertyChange(CHILDREN, old, newChildren);
	}

	public void removeChild(ElementModel model) {
		this.children.remove(model);
		firePropertyChange(CHILDREN, model, null);
	}

	public void addChild(ElementModel model) {
		this.children.add(model);
		model.setParent(this);
		firePropertyChange(CHILDREN, null, model);
	}

	public boolean contains(ActorModel actor) {
		return this.children.contains(actor);
	}

}
