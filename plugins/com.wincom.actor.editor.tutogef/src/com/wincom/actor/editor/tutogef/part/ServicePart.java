package com.wincom.actor.editor.tutogef.part;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import com.wincom.actor.editor.tutogef.figure.ServiceFigure;
import com.wincom.actor.editor.tutogef.model.Node;
import com.wincom.actor.editor.tutogef.model.Service;
import com.wincom.actor.editor.tutogef.policy.AppDeletePolicy;
import com.wincom.actor.editor.tutogef.policy.AppEditLayoutPolicy;
import com.wincom.actor.editor.tutogef.policy.AppRenamePolicy;

public class ServicePart extends AppAbstractEditPart {

	@Override
	protected IFigure createFigure() {
		return new ServiceFigure();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
	}

	@Override
	protected void refreshVisuals() {
		ServiceFigure figure = (ServiceFigure) getFigure();
		Service model = (Service) getModel();
		figure.setName(model.getName());
		figure.setEtage(model.getEtage());
		figure.setLayout(model.getLayout());
	}

	@Override
	public List<Node> getModelChildren() {
		return ((Service) getModel()).getChildrenArray();
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
	}

}
