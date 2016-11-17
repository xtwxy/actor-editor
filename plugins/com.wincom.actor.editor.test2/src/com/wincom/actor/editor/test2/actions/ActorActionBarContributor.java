package com.wincom.actor.editor.test2.actions;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.actions.ActionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActorActionBarContributor extends ActionBarContributor {
	Logger log = LoggerFactory.getLogger(this.getClass());

	protected void buildActions() {
		log.info("check");
		addRetargetAction(new UndoRetargetAction());
		addRetargetAction(new RedoRetargetAction());
		addRetargetAction(new DeleteRetargetAction());

	}

	public void contributeToToolBar(IToolBarManager toolBarManager) {
		log.info("check");
		toolBarManager.add(getAction(ActionFactory.UNDO.getId()));
		toolBarManager.add(getAction(ActionFactory.REDO.getId()));
	}

	protected void declareGlobalActionKeys() {
		log.info("check");
	}

}
