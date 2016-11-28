package com.wincom.actor.editor.test2.parts.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.model.ActorModel;
import com.wincom.actor.editor.test2.model.ElementModel;
import com.wincom.actor.editor.test2.policies.AppDeletePolicy;
import com.wincom.actor.editor.test2.policies.AppRenamePolicy;

public class ActorTreePart extends ElementTreeEditPart {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
	}

	public void refreshVisuals() {
		log.info("check");
		ActorModel model = (ActorModel) getModel();
		setWidgetText(model.getName());
		setWidgetImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT));
	}
	
	@Override
	public List<ElementModel> getModelChildren() {
		return ((ActorModel) getModel()).getChildren();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		log.info(evt.toString());
		if (evt.getPropertyName().equals(ActorModel.ID)) {
			refreshVisuals();
		} else if(evt.getPropertyName().equals(ActorModel.INPUT)) {
			refreshVisuals();
		} else if(evt.getPropertyName().equals(ActorModel.OUTPUTS)) {
			refreshChildren();
		} else {
			super.propertyChange(evt);
		}
	}

}
