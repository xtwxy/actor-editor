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

import org.eclipse.draw2d.IFigure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.flow.figures.ParallelActivityFigure;
import com.wincom.actor.editor.flow.figures.SubgraphFigure;

/**
 * @author hudsonr
 */
public class ParallelActivityPart extends StructuredActivityPart {
	Logger log = LoggerFactory.getLogger(this.getClass());

	protected IFigure createFigure() {
		log.info("check");
		return new ParallelActivityFigure();
	}

	/**
	 * @see org.eclipse.gef.EditPart#setSelected(int)
	 */
	public void setSelected(int value) {
		log.info("check");
		super.setSelected(value);
		SubgraphFigure sf = (SubgraphFigure) getFigure();
		sf.setSelected(value != SELECTED_NONE);
	}

}
