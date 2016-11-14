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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.graph.CompoundDirectedGraph;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test1.model.SampleModel;

/**
 * @author hudsonr Created on Jun 30, 2003
 */
public abstract class SamplePart extends AbstractGraphicalEditPart implements
		PropertyChangeListener, NodeEditPart {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	private SampleModel getSampleModel() {
		return (SampleModel) getModel();
	}
	public void activate() {
		super.activate();
		getSampleModel().addPropertyChangeListener(this);
	}
	
	public void deactivate() {
		super.deactivate();
		getSampleModel().removePropertyChangeListener(this);
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		log.info("check");
		return new BottomAnchor(getFigure(), getAnchorOffset());
	}

	abstract int getAnchorOffset();
	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		log.info("check");
		return new BottomAnchor(getFigure(), getAnchorOffset());
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		log.info("check");
		return new TopAnchor(getFigure(), getAnchorOffset());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		log.info("check");
		return new TopAnchor(getFigure(), getAnchorOffset());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		log.info("check");
		
	}

	public void contributeNodesToGraph(CompoundDirectedGraph graph, Object object, Map partsToNodes) {
		log.info("check");
	}

	public void contributeEdgesToGraph(CompoundDirectedGraph graph, Map partsToNodes) {
		log.info("check");
	}

	public void applyGraphResults(CompoundDirectedGraph graph, Map partsToNodes) {
		log.info("check");
	}

}
