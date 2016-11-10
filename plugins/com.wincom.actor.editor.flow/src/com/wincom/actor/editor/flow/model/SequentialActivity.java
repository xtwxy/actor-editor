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
package com.wincom.actor.editor.flow.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hudsonr Created on Jun 30, 2003
 */
public class SequentialActivity extends StructuredActivity {
	Logger log = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = -9080401150957481095L;

	public SequentialActivity() {
		super();
		log.info("check");
	}

}
