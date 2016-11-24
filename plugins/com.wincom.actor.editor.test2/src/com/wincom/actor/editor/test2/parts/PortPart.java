package com.wincom.actor.editor.test2.parts;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;

public class PortPart extends ElementPart implements NodeEditPart{

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		
	}

}
