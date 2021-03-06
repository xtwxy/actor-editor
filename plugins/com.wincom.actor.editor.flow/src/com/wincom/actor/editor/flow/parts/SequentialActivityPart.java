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

import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.graph.CompoundDirectedGraph;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.gef.EditPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.flow.figures.SequentialActivityFigure;

/**
 * @author hudsonr Created on Jul 18, 2003
 */
public class SequentialActivityPart extends StructuredActivityPart {
	Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * @see com.wincom.actor.editor.flow.parts.StructuredActivityPart#createFigure()
	 */
	protected IFigure createFigure() {
		log.info("check");
		return new SequentialActivityFigure();
	}

	/**
	 * @see ActivityPart#contributeEdgesToGraph(org.eclipse.graph.CompoundDirectedGraph,
	 *      java.util.Map)
	 */
	public void contributeEdgesToGraph(CompoundDirectedGraph graph, Map map) {
		log.info("check");
		super.contributeEdgesToGraph(graph, map);
		Node node, prev = null;
		EditPart a;
		List members = getChildren();
		for (int n = 0; n < members.size(); n++) {
			a = (EditPart) members.get(n);
			node = (Node) map.get(a);
			if (prev != null) {
				Edge e = new Edge(prev, node);
				e.weight = 50;
				graph.edges.add(e);
			}
			prev = node;
		}
	}

	/**
	 * @see com.wincom.actor.editor.flow.parts.StructuredActivityPart#getAnchorOffset()
	 */
	int getAnchorOffset() {
		log.info("check");
		return 15;
	}

}
