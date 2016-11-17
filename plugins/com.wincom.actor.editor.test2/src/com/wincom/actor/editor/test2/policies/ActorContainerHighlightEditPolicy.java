package com.wincom.actor.editor.test2.policies;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.swt.graphics.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActorContainerHighlightEditPolicy extends GraphicalEditPolicy {
	Logger log = LoggerFactory.getLogger(this.getClass());

	private Color revertColor;
	private static Color highLightColor = new Color(null, 200, 200, 240);

	/**
	 * @see org.eclipse.gef.EditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
	 */
	public void eraseTargetFeedback(Request request) {
		log.info("check");
		if (revertColor != null) {
			setContainerBackground(revertColor);
			revertColor = null;
		}
	}

	private Color getContainerBackground() {
		log.info("check");
		return getContainerFigure().getBackgroundColor();
	}

	private IFigure getContainerFigure() {
		log.info("check");
		return ((GraphicalEditPart) getHost()).getFigure();
	}

	/**
	 * @see org.eclipse.gef.EditPolicy#getTargetEditPart(org.eclipse.gef.Request)
	 */
	public EditPart getTargetEditPart(Request request) {
		log.info("check");
		return request.getType().equals(RequestConstants.REQ_SELECTION_HOVER) ? getHost()
				: null;
	}

	private void setContainerBackground(Color c) {
		log.info("check");
		getContainerFigure().setBackgroundColor(c);
	}

	/**
	 * Changes the background color of the container to the highlight color
	 */
	protected void showHighlight() {
		log.info("check");
		if (revertColor == null) {
			revertColor = getContainerBackground();
			setContainerBackground(highLightColor);
		}
	}

	/**
	 * @see org.eclipse.gef.EditPolicy#showTargetFeedback(org.eclipse.gef.Request)
	 */
	public void showTargetFeedback(Request request) {
		log.info("check");
		if (request.getType().equals(RequestConstants.REQ_CREATE)
				|| request.getType().equals(RequestConstants.REQ_ADD))
			showHighlight();
	}


}
