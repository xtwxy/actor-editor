package com.wincom.actor.editor.test2.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.test2.figures.PortFigure;

public class PortModel extends ElementModel {
	private static final long serialVersionUID = 2969510135967969883L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private ConnectionModel assocInterface;
	private List<ConnectionModel> incomingConnections;
	private List<ConnectionModel> outgoingConnections;
	
	public static final String INTERFACE = "interface";
	public static final String INPUTS = "inputs";
	public static final String OUTPUTS = "outputs";

	
	public PortModel() {
		setName("new port");
		setLayout(new Rectangle(10, 10, PortFigure.PORT_FIGURE_DEFWIDTH, PortFigure.PORT_FIGURE_DEFHEIGHT));
		incomingConnections = new ArrayList<>();
		outgoingConnections = new ArrayList<>();
	}
	public ConnectionModel getAssocInterface() {
		return assocInterface;
	}
	
	public void setLayout(Rectangle newLayout) {
		log.info("check");
		super.setLayout(newLayout);
	}

	public void setAssocInterface(ConnectionModel newInterface) {
		ConnectionModel oldInterface = newInterface;
		this.assocInterface = newInterface;
		firePropertyChange(INTERFACE, oldInterface, newInterface);
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (INTERFACE.equals(id)) {
			return assocInterface;
		}
		return super.getPropertyValue(id);
	}

	@Override
	public boolean isPropertySet(Object id) {
		if (INTERFACE.equals(id)) {
			return assocInterface != null;
		}
		return super.isPropertySet(id);
	}

	@Override
	public void resetPropertyValue(Object id) {
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (INTERFACE.equals(id)) {
			setAssocInterface((ConnectionModel) value);
		} else {
			super.setPropertyValue(id, value);
		}
	}

	@Override
	public List<ElementModel> getChildren() {
		return new ArrayList<>();
	}
	
	public List<ConnectionModel> getIncomingConnections() {
		return incomingConnections;
	}

	public void setIncomingConnections(List<ConnectionModel> incomingConnections) {
		this.incomingConnections = incomingConnections;
	}

	public List<ConnectionModel> getOutgoingConnections() {
		return outgoingConnections;
	}

	public void setOutgoingConnections(List<ConnectionModel> outgoingConnections) {
		this.outgoingConnections = outgoingConnections;
	}

	public void addOutput(ConnectionModel connection) {
		this.outgoingConnections.add(connection);
		fireStructureChange(OUTPUTS, connection);
	}

	public void addInput(ConnectionModel connection) {
		this.incomingConnections.add(connection);
		fireStructureChange(INPUTS, connection);
	}

	public void removeOutput(ConnectionModel connection) {
		this.outgoingConnections.remove(connection);
		fireStructureChange(OUTPUTS, connection);
	}

	public void removeInput(ConnectionModel connection) {
		this.incomingConnections.remove(connection);
		fireStructureChange(INPUTS, connection);
	}

	@Override
	public Object clone() {
		PortModel model = new PortModel();
		model.setAssocInterface(getAssocInterface());
		model.setBackgroundColor(getBackgroundColor());
		model.setForegroundColor(getForegroundColor());
		model.setLayout(getLayout());
		model.setName(getName());
		model.setParent(getParent());
		return model;
	}
}
