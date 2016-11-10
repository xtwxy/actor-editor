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
package com.wincom.actor.editor.test1.parts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;

import com.wincom.actor.editor.test1.policies.SampleDirectEditPolicy;
import com.wincom.actor.editor.test1.policies.SampleEditPolicy;
import com.wincom.actor.editor.test1.policies.SampleNodeEditPolicy;
import com.wincom.actor.editor.test1.policies.SampleSourceEditPolicy;

/**
 * @author hudsonr Created on Jun 30, 2003
 */
public class DiagramPart extends SamplePart {
	private static final Log log = LogFactory.getLog(DiagramPart.class);

	@Override
	int getAnchorOffset() {
		log.info("unimplemented.");
		return 0;
	}

	@Override
	protected void createEditPolicies() {
		log.info("check");
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new SampleNodeEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new SampleSourceEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new SampleEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new SampleDirectEditPolicy());
	}

	@Override
	protected IFigure createFigure() {
		log.info("check");
		Figure f = new Figure() {
			public void setBounds(Rectangle rect) {
				int x = bounds.x, y = bounds.y;

				boolean resize = (rect.width != bounds.width)
						|| (rect.height != bounds.height), translate = (rect.x != x)
						|| (rect.y != y);

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
		log.info(f);
		return f;
	}



}
