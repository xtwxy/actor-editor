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
import org.eclipse.draw2d.IFigure;

/**
 * @author hudsonr Created on Jun 30, 2003
 */
public class Figure2Part extends SamplePart {
	private static final Log log = LogFactory.getLog(Figure2Part.class);

	@Override
	int getAnchorOffset() {
		log.info("unimplemented.");
		return 0;
	}

	@Override
	protected void createEditPolicies() {
		log.info("unimplemented.");
	}

	@Override
	protected IFigure createFigure() {
		log.info("unimplemented.");
		return null;
	}



}
