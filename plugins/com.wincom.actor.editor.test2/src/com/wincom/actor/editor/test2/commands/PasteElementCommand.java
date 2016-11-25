package com.wincom.actor.editor.test2.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import com.wincom.actor.editor.test2.model.ActorModel;
import com.wincom.actor.editor.test2.model.DiagramModel;
import com.wincom.actor.editor.test2.model.ElementModel;
import com.wincom.actor.editor.test2.model.PortModel;

public class PasteElementCommand extends Command {
	private HashMap<ElementModel, ElementModel> list = new HashMap<ElementModel, ElementModel>();

	@Override
	public boolean canExecute() {
		@SuppressWarnings("unchecked")
		ArrayList<ElementModel> bList = (ArrayList<ElementModel>) Clipboard.getDefault().getContents();
		if (bList == null || bList.isEmpty())
			return false;
		Iterator<ElementModel> it = bList.iterator();
		while (it.hasNext()) {
			ElementModel node = (ElementModel) it.next();
			if (isPastableElementModel(node)) {
				list.put(node, null);
			}
		}
		return true;
	}

	@Override
	public void execute() {
		if (!canExecute())
			return;
		Iterator<ElementModel> it = list.keySet().iterator();
		while (it.hasNext()) {
			ElementModel element = (ElementModel) it.next();
			if (element instanceof ActorModel) {
				ActorModel actor = (ActorModel) element;
				ActorModel clone = (ActorModel) actor.clone();
				list.put(element, clone);
			} else if (element instanceof PortModel) {
				PortModel port = (PortModel) element;
				PortModel clone = (PortModel) port.clone();
				list.put(element, clone);
			}
		}
		redo();
	}

	@Override
	public void redo() {
		Iterator<ElementModel> it = list.values().iterator();
		while (it.hasNext()) {
			ElementModel element = it.next();
			if (isPastableElementModel(element)) {
				if (element instanceof ActorModel) {
					((DiagramModel) element.getParent()).addChild(element);
				}
			}
		}
	}

	@Override
	public boolean canUndo() {
		return !(list.isEmpty());
	}

	@Override
	public void undo() {
		Iterator<ElementModel> it = list.values().iterator();
		while (it.hasNext()) {
			ElementModel element = it.next();
			if (isPastableElementModel(element)) {
				if (element instanceof ActorModel) {
					((DiagramModel) element.getParent()).addChild(element);
				}
			}
		}
	}

	public boolean isPastableElementModel(ElementModel element) {
		if (element instanceof ActorModel || element instanceof PortModel)
			return true;
		return false;
	}
}
