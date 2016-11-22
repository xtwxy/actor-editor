package com.wincom.actor.editor.tutogef.part.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.tutogef.model.Enterprise;
import com.wincom.actor.editor.tutogef.model.Node;

public class EnterpriseTreeEditPart extends AppAbstractTreeEditPart {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected List<Node> getModelChildren() {
		return ((Enterprise) getModel()).getChildrenArray();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		log.info(evt.toString());
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD))
			refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE))
			refreshChildren();
		if (evt.getPropertyName().equals(Enterprise.PROPERTY_CAPITAL))
			refreshVisuals();
	}
}
