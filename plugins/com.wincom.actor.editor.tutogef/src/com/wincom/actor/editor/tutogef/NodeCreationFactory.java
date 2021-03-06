package com.wincom.actor.editor.tutogef;

import org.eclipse.gef.requests.CreationFactory;

import com.wincom.actor.editor.tutogef.model.Employe;
import com.wincom.actor.editor.tutogef.model.Service;

public class NodeCreationFactory implements CreationFactory {
	private Class<?> template;

	public NodeCreationFactory(Class<?> t) {
		this.template = t;
	}

	@Override
	public Object getNewObject() {
		if (template == null)
			return null;
		if (template == Service.class) {
			Service srv = new Service();
			srv.setStockNo(32768);
			srv.setName("Negative");
			return srv;
		} else if (template == Employe.class) {
			Employe emp = new Employe();
			emp.setPrenom("David");
			emp.setName("Johns");
			return emp;
		}
		return null;
	}

	@Override
	public Object getObjectType() {
		return template;
	}

}
