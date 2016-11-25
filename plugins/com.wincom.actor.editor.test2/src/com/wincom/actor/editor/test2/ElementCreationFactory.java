package com.wincom.actor.editor.test2;

import org.eclipse.gef.requests.CreationFactory;

import com.wincom.actor.editor.test2.model.ActorModel;
import com.wincom.actor.editor.test2.model.PortModel;

public class ElementCreationFactory implements CreationFactory {
	private Class<?> template;

	public ElementCreationFactory(Class<?> t) {
		this.template = t;
	}

	@Override
	public Object getNewObject() {
		if (template == null)
			return null;
		if (template == ActorModel.class) {
			ActorModel actor = new ActorModel();
			return actor;
		} else if (template == PortModel.class) {
			PortModel port = new PortModel();
			return port;
		}
		return null;
	}

	@Override
	public Object getObjectType() {
		return template;
	}

}
