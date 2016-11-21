package com.wincom.actor.editor.tutogef.part;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;

import com.wincom.actor.editor.tutogef.figure.EmployeFigure;
import com.wincom.actor.editor.tutogef.model.Employe;
import com.wincom.actor.editor.tutogef.model.Node;

public class EmployePart extends AppAbstractEditPart {

	@Override
	protected IFigure createFigure() {
		return new EmployeFigure();
	}

	@Override
	protected void createEditPolicies() {

	}

	protected void refreshVisuals() {
		EmployeFigure figure = (EmployeFigure) getFigure();
		Employe model = (Employe) getModel();
		figure.setName(model.getName());
		figure.setFirstName(model.getPrenom());
		figure.setLayout(model.getLayout());
	}

	public List<Node> getModelChildren() {
		return new ArrayList<Node>();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT))
			refreshVisuals();
	}
}
