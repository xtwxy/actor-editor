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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.CompoundDirectedGraph;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.Subgraph;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import com.wincom.actor.editor.flow.model.Activity;
import com.wincom.actor.editor.flow.model.FlowElement;
import com.wincom.actor.editor.flow.policies.ActivityDirectEditPolicy;
import com.wincom.actor.editor.flow.policies.ActivityEditPolicy;
import com.wincom.actor.editor.flow.policies.ActivityNodeEditPolicy;
import com.wincom.actor.editor.flow.policies.ActivitySourceEditPolicy;
import org.eclipse.gef.tools.DirectEditManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hudsonr Created on Jun 30, 2003
 */
public abstract class ActivityPart extends AbstractGraphicalEditPart implements
		PropertyChangeListener, NodeEditPart {
	Logger log = LoggerFactory.getLogger(this.getClass());

	protected DirectEditManager manager;

	/**
	 * @see org.eclipse.gef.EditPart#activate()
	 */
	public void activate() {
		log.info("check");
		super.activate();
		getActivity().addPropertyChangeListener(this);
	}

	protected void applyGraphResults(CompoundDirectedGraph graph, Map map) {
		log.info("check");
		Node n = (Node) map.get(this);
		getFigure().setBounds(new Rectangle(n.x, n.y, n.width, n.height));

		for (int i = 0; i < getSourceConnections().size(); i++) {
			TransitionPart trans = (TransitionPart) getSourceConnections().get(
					i);
			trans.applyGraphResults(graph, map);
		}
	}

	public void contributeEdgesToGraph(CompoundDirectedGraph graph, Map map) {
		log.info("check");
		List outgoing = getSourceConnections();
		for (int i = 0; i < outgoing.size(); i++) {
			TransitionPart part = (TransitionPart) getSourceConnections()
					.get(i);
			part.contributeToGraph(graph, map);
		}
		for (int i = 0; i < getChildren().size(); i++) {
			ActivityPart child = (ActivityPart) children.get(i);
			child.contributeEdgesToGraph(graph, map);
		}
	}

	public abstract void contributeNodesToGraph(CompoundDirectedGraph graph,
			Subgraph s, Map map);

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		log.info("check");
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new ActivityNodeEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new ActivitySourceEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ActivityEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new ActivityDirectEditPolicy());
	}

	/**
	 * @see org.eclipse.gef.EditPart#deactivate()
	 */
	public void deactivate() {
		log.info("check");
		super.deactivate();
		getActivity().removePropertyChangeListener(this);
	}

	/**
	 * Returns the Activity model associated with this EditPart
	 * 
	 * @return the Activity model
	 */
	protected Activity getActivity() {
		log.info("check");
		return (Activity) getModel();
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelSourceConnections()
	 */
	protected List getModelSourceConnections() {
		log.info("check");
		return getActivity().getOutgoingTransitions();
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelTargetConnections()
	 */
	protected List getModelTargetConnections() {
		log.info("check");
		return getActivity().getIncomingTransitions();
	}

	abstract int getAnchorOffset();

	/**
	 * @see NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		log.info("check");
		return new BottomAnchor(getFigure(), getAnchorOffset());
	}

	/**
	 * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		log.info("check");
		return new BottomAnchor(getFigure(), getAnchorOffset());
	}

	/**
	 * @see NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		log.info("check");
		return new TopAnchor(getFigure(), getAnchorOffset());
	}

	/**
	 * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		log.info("check");
		return new TopAnchor(getFigure(), getAnchorOffset());
	}

	protected void performDirectEdit() {
		log.info("check");
	}

	/**
	 * @see org.eclipse.gef.EditPart#performRequest(org.eclipse.gef.Request)
	 */
	public void performRequest(Request request) {
		log.info("check");
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT)
			performDirectEdit();
	}

	/**
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		log.info("check");
		String prop = evt.getPropertyName();
		if (FlowElement.CHILDREN.equals(prop))
			refreshChildren();
		else if (FlowElement.INPUTS.equals(prop))
			refreshTargetConnections();
		else if (FlowElement.OUTPUTS.equals(prop))
			refreshSourceConnections();
		else if (Activity.NAME.equals(prop))
			refreshVisuals();

		// Causes Graph to re-layout
		((GraphicalEditPart) (getViewer().getContents())).getFigure()
				.revalidate();
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#setFigure(org.eclipse.draw2d.IFigure)
	 */
	protected void setFigure(IFigure figure) {
		log.info("check");
		// FIXME: why set size to zero?
		figure.getBounds().setSize(0, 0);
		super.setFigure(figure);
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#toString()
	 */
	public String toString() {
		log.info("check");
		return getModel().toString();
	}

}
