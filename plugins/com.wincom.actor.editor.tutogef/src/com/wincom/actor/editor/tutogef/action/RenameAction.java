package com.wincom.actor.editor.tutogef.action;

import java.util.HashMap;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.tutogef.model.Node;
import com.wincom.actor.editor.tutogef.wizard.RenameWizard;

public class RenameAction extends SelectionAction {
	Logger log = LoggerFactory.getLogger(this.getClass());

	public RenameAction(IWorkbenchPart part) {
		super(part);
		log.info("check");
		setLazyEnablementCalculation(false);
	}

	protected void init() {
		setText("Rename...");
		setToolTipText("Rename");
		setId(ActionFactory.RENAME.getId());
		ImageDescriptor icon = AbstractUIPlugin.imageDescriptorFromPlugin("TutoGEF", "icons/rename-icon.png");
		if (icon != null)
			setImageDescriptor(icon);
		setEnabled(false);
	}

	@Override
	protected boolean calculateEnabled() {
		Command cmd = createRenameCommand("");
		if (cmd == null)
			return false;
		return true;
	}

	public Command createRenameCommand(String name) {
		log.info(name);
		if(getSelectedObjects().isEmpty()) {
			return null;
		}
		Request renameReq = new Request("rename");
		HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newName", name);
		renameReq.setExtendedData(reqData);
		for (Object o : getSelectedObjects()) {
			log.info(o.toString());
		}
		
		Object o = getSelectedObjects().get(0);
		if (o instanceof EditPart) {
			EditPart object = (EditPart) getSelectedObjects().get(0);
			Command cmd = object.getCommand(renameReq);
			return cmd;
		} else {
			return null;
		}
	}

	public void run() {
		Node node = getSelectedNode();
		RenameWizard wizard = new RenameWizard(node.getName());
		WizardDialog dialog = new WizardDialog(getWorkbenchPart().getSite().getShell(), wizard);
		dialog.create();
		dialog.getShell().setSize(400, 180);
		dialog.getShell().pack();
		dialog.setTitle("Rename wizard");
		dialog.setMessage("Rename");
		if (dialog.open() == WizardDialog.OK) {
			String name = wizard.getRenameValue();
			execute(createRenameCommand(name));
		}
	}

	// Helper
	private Node getSelectedNode() {
		@SuppressWarnings("rawtypes")
		List objects = getSelectedObjects();
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;
		EditPart part = (EditPart) objects.get(0);
		return (Node) part.getModel();
	}
}
