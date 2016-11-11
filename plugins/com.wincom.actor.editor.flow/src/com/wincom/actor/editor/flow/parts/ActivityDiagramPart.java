/*******************************************************************************
 * Copyright (c) 2003, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.wincom.actor.editor.flow.parts;

import java.util.EventObject;
import java.util.Map;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.CompoundDirectedGraph;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.flow.policies.ActivityContainerEditPolicy;
import com.wincom.actor.editor.flow.policies.StructuredActivityLayoutEditPolicy;

/**
 * @author hudsonr Created on Jul 16, 2003
 */
public class ActivityDiagramPart extends StructuredActivityPart {
	Logger log = LoggerFactory.getLogger(this.getClass());

	CommandStackListener stackListener = null;
	
	public ActivityDiagramPart() {
		log.info("check");
		stackListener = new CommandStackListener() {
			public void commandStackChanged(EventObject event) {
				log.info("check");
				if (!GraphAnimation.captureLayout(getFigure()))
					return;
				while (GraphAnimation.step())
					getFigure().getUpdateManager().performUpdate();
				GraphAnimation.end();
			}
		};
	}

	protected void applyOwnResults(CompoundDirectedGraph graph, Map map) {
		log.info("check");
	}

	/**
	 * @see com.wincom.actor.editor.flow.parts.ActivityPart#activate()
	 */
	public void activate() {
		log.info("check");
		super.activate();
		getViewer().getEditDomain().getCommandStack()
				.addCommandStackListener(stackListener);
	}

	/**
	 * @see com.wincom.actor.editor.flow.parts.ActivityPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		log.info("check");
		installEditPolicy(EditPolicy.NODE_ROLE, null);
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new RootComponentEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE,
				new StructuredActivityLayoutEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new ActivityContainerEditPolicy());
	}

	protected IFigure createFigure() {
		log.info("check");
		Figure f = new Figure() {
			public void setBounds(Rectangle rect) {
				int x = bounds.x, y = bounds.y;

				boolean resize = (rect.width != bounds.width)
						|| (rect.height != bounds.height), translate = (rect.x != x)
						|| (rect.y != y);
// FIXME: why erase() ?
				if (isVisible() && (resize || translate))
					erase();
				if (translate) {
					int dx = rect.x - x;
					int dy = rect.y - y;
					primTranslate(dx, dy);
				}
				bounds.width = rect.width;
				bounds.height = rect.height;
				if (resize || translate) {
					fireFigureMoved();
					repaint();
				}
			}
		};
		f.setLayoutManager(new GraphLayoutManager(this));
		return f;
	}

	/**
	 * @see com.wincom.actor.editor.flow.parts.ActivityPart#deactivate()
	 */
	public void deactivate() {
		log.info("check");
		getViewer().getEditDomain().getCommandStack()
				.removeCommandStackListener(stackListener);
		super.deactivate();
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#isSelectable()
	 */
	public boolean isSelectable() {
		log.info("check");
		return false;
	}

	/**
	 * @see com.wincom.actor.editor.flow.parts.StructuredActivityPart#refreshVisuals()
	 */
	protected void refreshVisuals() {
		log.info("check");
	}

}
