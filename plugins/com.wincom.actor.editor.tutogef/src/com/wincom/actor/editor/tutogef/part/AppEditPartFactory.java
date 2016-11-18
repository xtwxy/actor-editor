package com.wincom.actor.editor.tutogef.part;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.tutogef.model.Employe;
import com.wincom.actor.editor.tutogef.model.Enterprise;
import com.wincom.actor.editor.tutogef.model.Service;

public class AppEditPartFactory implements EditPartFactory {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
    	log.info("context = " + context + ", model = " + model);
		AbstractGraphicalEditPart part = null;
		if (model instanceof Enterprise) {
			part = new EnterprisePart();
		} else if (model instanceof Service) {
			part = new ServicePart();
		} else if (model instanceof Employe) {
			part = new EmployePart();
		}
		part.setModel(model);
		return part;
	}
}
