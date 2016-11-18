package com.wincom.actor.editor.tutogef.part;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.tutogef.figure.EnterpriseFigure;
import com.wincom.actor.editor.tutogef.model.Enterprise;
import com.wincom.actor.editor.tutogef.model.Node;

public class EnterprisePart extends AbstractGraphicalEditPart {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected IFigure createFigure() {
    	log.info("check");
		return new EnterpriseFigure();
	}

	@Override
	protected void createEditPolicies() {
    	log.info("check");
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
}
