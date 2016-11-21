package com.wincom.actor.editor.tutogef.part.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import com.wincom.actor.editor.tutogef.model.Enterprise;
import com.wincom.actor.editor.tutogef.model.Node;

public class EnterpriseTreeEditPart extends AppAbstractTreeEditPart {

	@Override
	protected List<Node> getModelChildren() {
		return ((Enterprise) getModel()).getChildrenArray();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD))
			refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE))
			refreshChildren();
	}
}
