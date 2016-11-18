package com.wincom.actor.editor.test2.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.model.ActorModel;
import com.wincom.actor.editor.test2.model.ConnectionModel;
import com.wincom.actor.editor.test2.model.DiagramModel;
import com.wincom.actor.editor.test2.model.PortModel;
import com.wincom.actor.editor.test2.model.ProvidedPortModel;
import com.wincom.actor.editor.test2.model.RequiredPortModel;

public class ActorEditPartFactory implements EditPartFactory {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		log.info("check, " + context + ", " + model);
		EditPart part = null;
		if(model instanceof ActorModel) {
			part = new ActorPart();
		} else if(model instanceof ConnectionModel) {
			part = new ConnectionPart();
		} else if(model instanceof DiagramModel) {
			part = new DiagramPart();
		} else if(model instanceof PortModel) {
			part = new PortPart();
		} else if(model instanceof ProvidedPortModel) {
			part = new ProvidedPortPart();
		} else if(model instanceof RequiredPortModel) {
			part = new RequiredPortPart();
		} else {
		}
		part.setModel(model);
		return part;
	}

}
