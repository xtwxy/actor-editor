package com.wincom.actor.editor.test1.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test1.model.DiagramModel;
import com.wincom.actor.editor.test1.model.Figure1Model;
import com.wincom.actor.editor.test1.model.Figure2Model;

public class SampleEditPartFactory implements EditPartFactory {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		log.info("createEditPart: " + model.getClass().getName());
		EditPart part = null;
		if (model instanceof DiagramModel) {
			part = new DiagramPart();
		} else if (model instanceof Figure1Model) {
			part = new Figure1Part();
		} else if (model instanceof Figure2Model) {
			part = new Figure2Part();
		} else if (model instanceof Figure2Model) {
			part = new Figure3Part();
		} else {
			log.warn("no parts found, return null");
			part = null;
		}
		part.setModel(model);
		return part;
	}
}
