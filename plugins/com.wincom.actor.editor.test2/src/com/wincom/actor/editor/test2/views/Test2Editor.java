package com.wincom.actor.editor.test2.views;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test2Editor extends GraphicalEditorWithPalette {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected PaletteRoot getPaletteRoot() {
		return null;
	}

	@Override
	protected void initializeGraphicalViewer() {
		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		
	}

}
