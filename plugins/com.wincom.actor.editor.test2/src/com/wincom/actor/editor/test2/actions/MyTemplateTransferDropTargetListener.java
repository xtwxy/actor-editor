package com.wincom.actor.editor.test2.actions;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.ElementCreationFactory;

public class MyTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener {
	Logger log = LoggerFactory.getLogger(this.getClass());

	public MyTemplateTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer);
	}

	@Override
	protected CreationFactory getFactory(Object template) {
		log.info(template.toString());
		if (template instanceof Class<?>) {
			return new ElementCreationFactory((Class<?>) template);
		} else if (template instanceof CreationFactory) {
			return (CreationFactory) template;
		} else {
			log.warn("unknow type: " + template.toString());
			return null;
		}
	}
}
