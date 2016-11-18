package com.wincom.actor.editor.tutogef;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.tutogef.editor.MyEditorInput;
import com.wincom.actor.editor.tutogef.editor.MyGraphicalEditor;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {
	Logger log = LoggerFactory.getLogger(this.getClass());

	private static final String PERSPECTIVE_ID = "com.wincom.actor.editor.tutogef.perspective"; //$NON-NLS-1$

    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
    	log.info("check");
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

	public String getInitialWindowPerspectiveId() {
    	log.info("check");
		return PERSPECTIVE_ID;
	}
	
	@Override
	public void postStartup() {
    	log.info("check");
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			page.openEditor(new MyEditorInput("TutoGEF"), MyGraphicalEditor.ID, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
