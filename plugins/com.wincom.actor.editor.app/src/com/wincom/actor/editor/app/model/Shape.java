package com.wincom.actor.editor.app.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

public abstract class Shape extends ModelElement {

	private static final long serialVersionUID = 1L;

	private static final String SOURCE_CONNECTIONS_PROP = "SOURCE_CONNECTIONS_PROP";

	private static final String TARGET_CONNECTIONS_PROP = "TARGET_CONNECTIONS_PROP";

	private static final String LOCATION_PROP = "LOCATION_PROP";

	private Point location = new Point(0, 0);
	private Dimension size = new Dimension(50, 50);
	private List<Connection> sourceConnections = new ArrayList<>();
	private List<Connection> targetConnections = new ArrayList<>();

	public Point getLocation() {
		return location.getCopy();
	}

	public Dimension getDimension() {
		return size.getCopy();
	}
	public void setLocation(Point newLocation) {
		if(newLocation == null) {
			throw new IllegalArgumentException();
		}
		location.setLocation(newLocation);
		firePropertyChange(LOCATION_PROP, null, location);
	}
	
	public void addConnection(Connection conn) {
		if(conn == null || conn.getSourceAnchor() == conn.getTargetAnchor()) {
			throw new IllegalArgumentException();
		}
		if(conn.getSourceAnchor() == this) {
			sourceConnections.add(conn);
			firePropertyChange(SOURCE_CONNECTIONS_PROP, null, sourceConnections);
		} else if(conn.getTargetAnchor() == this) {
			targetConnections.add(conn);
			firePropertyChange(TARGET_CONNECTIONS_PROP, null, targetConnections);
		}
	}
	
	public void removeConnection(Connection conn) {
		if(conn == null || conn.getSourceAnchor() == conn.getTargetAnchor()) {
			throw new IllegalArgumentException();
		}
		if(conn.getSourceAnchor() == this) {
			sourceConnections.remove(conn);
			firePropertyChange(SOURCE_CONNECTIONS_PROP, null, sourceConnections);
		} else if(conn.getTargetAnchor() == this) {
			targetConnections.remove(conn);
			firePropertyChange(TARGET_CONNECTIONS_PROP, null, targetConnections);
		}
	}
	
	public List<Connection> getSourceConnections() {
		return new ArrayList<>(sourceConnections);
	}
	
	public List<Connection> getTargetConnections() {
		return new ArrayList<>(targetConnections);
	}
}
