package com.wincom.actor.editor.test2.parts.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.model.DiagramModel;
import com.wincom.actor.editor.test2.model.ElementModel;

public class DiagramTreePart extends ElementTreeEditPart {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected List<ElementModel> getModelChildren() {
		return ((DiagramModel) getModel()).getChildren();
	}
	
	@Override
	public void refreshVisuals() {
		log.info("check");
		ElementModel model = (ElementModel) getModel();
		setWidgetText(model.getName());
		setWidgetImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT));
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(DiagramModel.AGGREGATE_ID)) {
			refreshVisuals();
		} else if(evt.getPropertyName().equals(DiagramModel.CHILDREN)) {
			refreshVisuals();
		} else {
			super.propertyChange(evt);
		}
	}

}
