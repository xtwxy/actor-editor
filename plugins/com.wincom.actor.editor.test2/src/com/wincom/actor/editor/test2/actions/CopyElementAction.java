package com.wincom.actor.editor.test2.actions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import com.wincom.actor.editor.test2.commands.CopyElementCommand;
import com.wincom.actor.editor.test2.model.ElementModel;

public class CopyElementAction extends SelectionAction {

	public CopyElementAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}

	@Override
	protected void init() {
		super.init();
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setText("Copy");
		setId(ActionFactory.COPY.getId());
		setHoverImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
		setEnabled(false);
	}

	private Command createCopyCommand(List<Object> selectedObjects) {
		if (selectedObjects == null || selectedObjects.isEmpty()) {
			return null;
		}
		CopyElementCommand cmd = new CopyElementCommand();
		Iterator<Object> it = selectedObjects.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			if (o instanceof EditPart) {
				EditPart ep = (EditPart) o;
				ElementModel node = (ElementModel) ep.getModel();
				if (!cmd.isCopyableElementModel(node))
					return null;
				cmd.addElement(node);
			}
		}
		return cmd;
	}

	@Override
	protected boolean calculateEnabled() {
		@SuppressWarnings("unchecked")
		Command cmd = createCopyCommand(getSelectedObjects());
		if (cmd == null)
			return false;
		return cmd.canExecute();
	}

	@Override
	public void run() {
		@SuppressWarnings("unchecked")
		Command cmd = createCopyCommand(getSelectedObjects());
		if (cmd != null && cmd.canExecute()) {
			cmd.execute();
		}
	}
}
