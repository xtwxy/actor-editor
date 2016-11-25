package com.wincom.actor.editor.test2.commands;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import com.wincom.actor.editor.test2.model.ActorModel;
import com.wincom.actor.editor.test2.model.ElementModel;
import com.wincom.actor.editor.test2.model.PortModel;

public class CopyElementCommand extends Command {
	private ArrayList<ElementModel> list = new ArrayList<ElementModel>();

	public boolean addElement(ElementModel node) {
		if (!list.contains(node)) {
			return list.add(node);
		}
		return false;
	}

	@Override
	public boolean canExecute() {
		if (list == null || list.isEmpty())
			return false;
		Iterator<ElementModel> it = list.iterator();
		while (it.hasNext()) {
			if (!isCopyableElementModel(it.next()))
				return false;
		}
		return true;
	}

	@Override
	public void execute() {
		if (canExecute())
			Clipboard.getDefault().setContents(list);
	}

	@Override
	public boolean canUndo() {
		return false;
	}

	public boolean isCopyableElementModel(ElementModel element) {
		if (element instanceof ActorModel || element instanceof PortModel)
			return true;
		return false;
	}
}
