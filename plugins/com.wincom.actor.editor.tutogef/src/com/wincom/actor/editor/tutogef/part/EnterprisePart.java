package com.wincom.actor.editor.tutogef.part;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.tutogef.figure.EnterpriseFigure;
import com.wincom.actor.editor.tutogef.model.Enterprise;
import com.wincom.actor.editor.tutogef.model.Node;
import com.wincom.actor.editor.tutogef.policy.AppDeletePolicy;
import com.wincom.actor.editor.tutogef.policy.AppEditLayoutPolicy;

public class EnterprisePart extends AppAbstractEditPart {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected IFigure createFigure() {
		log.info("check");
		return new EnterpriseFigure();
	}

	@Override
	protected void createEditPolicies() {
		log.info("check");
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
	}

	protected void refreshVisuals() {
		log.info("check");
		EnterpriseFigure figure = (EnterpriseFigure) getFigure();
		Enterprise model = (Enterprise) getModel();
		figure.setName(model.getName());
		figure.setAddress(model.getAddress());
		figure.setCapital(model.getCapital());
	}

	public List<Node> getModelChildren() {
		log.info("check");
		return ((Enterprise) getModel()).getChildrenArray();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT))
			refreshVisuals();
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD))
			refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE))
			refreshChildren();

		if (evt.getPropertyName().equals(Node.PROPERTY_RENAME))
			refreshVisuals();
		if (evt.getPropertyName().equals(Enterprise.PROPERTY_CAPITAL))
			refreshVisuals();
	}

}
