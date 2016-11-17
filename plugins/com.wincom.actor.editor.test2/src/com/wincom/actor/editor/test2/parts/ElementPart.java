package com.wincom.actor.editor.test2.parts;

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
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.tools.DirectEditManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.model.ElementModel;

public abstract class ElementPart extends AbstractGraphicalEditPart implements PropertyChangeListener, NodeEditPart {
	Logger log = LoggerFactory.getLogger(this.getClass());
	protected DirectEditManager manager;

	public void activate() {
		log.info("check");
		super.activate();
		getElementModel().addPropertyChangeListener(this);
	}	
	
	public void deactivate() {
		log.info("check");
		super.deactivate();
		getElementModel().removePropertyChangeListener(this);
	}


	protected void applyGraphResults(CompoundDirectedGraph graph, Map<GraphicalEditPart, Node> map) {
		log.info("check");
		Node n = (Node) map.get(this);
		getFigure().setBounds(new Rectangle(n.x, n.y, n.width, n.height));

		@SuppressWarnings("unchecked")
		List<ConnectionPart> conns = getSourceConnections();
		for (ConnectionPart conn : conns) {
			conn.applyGraphResults(graph, map);
		}
	}

	public void contributeEdgesToGraph(CompoundDirectedGraph graph, Map<GraphicalEditPart, Node> map) {
		log.info("check");
		@SuppressWarnings("unchecked")
		List<ConnectionPart> outgoing = getSourceConnections();
		for (ConnectionPart part : outgoing) {
			part.contributeToGraph(graph, map);
		}
		@SuppressWarnings("unchecked")
		List<ElementPart> children = getChildren();
		for (ElementPart child : children) {
			child.contributeEdgesToGraph(graph, map);
		}
	}

	public abstract void contributeNodesToGraph(CompoundDirectedGraph graph, Subgraph s,
			Map<GraphicalEditPart, Node> map);

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return null;
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return null;
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return null;
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return null;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

	}

	@Override
	protected IFigure createFigure() {
		return null;
	}

	@Override
	protected void createEditPolicies() {

	}

	protected ElementModel getElementModel() {
		return (ElementModel) super.getModel();
	}

}
