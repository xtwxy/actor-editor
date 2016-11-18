package com.wincom.actor.editor.tutogef.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyEditorInput implements IEditorInput {
	Logger log = LoggerFactory.getLogger(this.getClass());
	public String name = null;

	public MyEditorInput(String name) {
		this.name = name;
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
    	log.info("check");
		return null;
	}

	@Override
	public boolean exists() {
    	log.info("check");
		return (this.name != null);
	}

	@Override
	public boolean equals(Object o) {
    	log.info("check");
		if (!(o instanceof MyEditorInput)) {
			return false;
		}
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
    	log.info("check");
		return ImageDescriptor.getMissingImageDescriptor();
	}

	@Override
	public String getName() {
    	log.info("check");
		return this.name;
	}

	@Override
	public IPersistableElement getPersistable() {
    	log.info("check");
		return null;
	}

	@Override
	public String getToolTipText() {
    	log.info("check");
		return this.name;
	}

}
