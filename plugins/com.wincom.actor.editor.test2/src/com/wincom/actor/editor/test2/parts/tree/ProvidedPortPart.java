package com.wincom.actor.editor.test2.parts.tree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.model.ElementModel;
import com.wincom.actor.editor.test2.policies.AppDeletePolicy;
import com.wincom.actor.editor.test2.policies.AppRenamePolicy;

public class ProvidedPortPart extends ElementTreeEditPart {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
	}

	@Override
	public void refreshVisuals() {
		log.info("check");
		ElementModel model = (ElementModel) getModel();
		setWidgetText(model.getName());
		setWidgetImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT));
	}
	
	@Override
	public List<ElementModel> getModelChildren() {
		return new ArrayList<>();
	}
}
