package com.wincom.actor.editor.test2;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	private static final String ID_TABS_FOLDER = "ID_TABS_FOLDER";

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		layout.addStandaloneView(IPageLayout.ID_OUTLINE, true, IPageLayout.LEFT, 0.3f, editorArea);
		
		IFolderLayout tabs = layout.createFolder(
				ID_TABS_FOLDER, IPageLayout.LEFT, 0.3f, editorArea);
				tabs.addView(IPageLayout.ID_OUTLINE);
				tabs.addPlaceholder(IPageLayout.ID_PROP_SHEET);
	}
}
