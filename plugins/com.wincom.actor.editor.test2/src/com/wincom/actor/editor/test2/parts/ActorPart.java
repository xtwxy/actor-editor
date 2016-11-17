package com.wincom.actor.editor.test2.parts;

import java.util.Map;

import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.graph.CompoundDirectedGraph;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.draw2d.graph.Subgraph;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.policies.ActorContainerEditPolicy;
import com.wincom.actor.editor.test2.policies.ActorContainerHighlightEditPolicy;
import com.wincom.actor.editor.test2.policies.ActorDirectEditPolicy;
import com.wincom.actor.editor.test2.policies.ActorEditPolicy;
import com.wincom.actor.editor.test2.policies.ActorLayoutEditPolicy;
import com.wincom.actor.editor.test2.policies.ActorNodeEditPolicy;

public class ActorPart extends ElementPart {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public ActorPart() { 
		log.info("new ActorPart()");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void contributeNodesToGraph(CompoundDirectedGraph graph, Subgraph s, Map<GraphicalEditPart, Node> map) {
		log.info("check");
		Node n = new Node(this, s);
		n.width = getFigure().getPreferredSize().width;
		n.height = getFigure().getPreferredSize().height;
		n.setPadding(new Insets(10, 8, 10, 12));
		map.put(this, n);
		graph.nodes.add(n);
	}

	protected void createEditPolicies() {
		log.info("check");
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new ActorNodeEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ActorEditPolicy());
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE,
				new ActorContainerHighlightEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new ActorContainerEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE,
				new ActorLayoutEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new ActorDirectEditPolicy());
	}

}
