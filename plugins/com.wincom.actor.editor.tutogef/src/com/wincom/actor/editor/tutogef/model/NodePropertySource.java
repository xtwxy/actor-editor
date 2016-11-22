package com.wincom.actor.editor.tutogef.model;

import java.util.ArrayList;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ColorPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class NodePropertySource implements IPropertySource {
	private Node node;

	public NodePropertySource(Node node) {
		this.node = node;
	}

	@Override
	public Object getEditableValue() {
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		ArrayList<IPropertyDescriptor> properties = new ArrayList<IPropertyDescriptor>();
		if (node instanceof Employe)
			properties.add(new TextPropertyDescriptor(Node.PROPERTY_RENAME, "Name"));
		else
			properties.add(new TextPropertyDescriptor(Node.PROPERTY_RENAME, "Name"));
		if (node instanceof Service) {
			properties.add(new ColorPropertyDescriptor(Service.PROPERTY_COLOR, "Color"));
			properties.add(new TextPropertyDescriptor(Service.PROPERTY_FLOOR, "Etage"));
		} else if (node instanceof Enterprise) {
			properties.add(new TextPropertyDescriptor(Enterprise.PROPERTY_CAPITAL, "Capital"));
		} else if (node instanceof Employe) {
			properties.add(new TextPropertyDescriptor(Employe.PROPERTY_FIRSTNAME, "Prenom"));
		}
		return properties.toArray(new IPropertyDescriptor[0]);
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(Node.PROPERTY_RENAME))
			return node.getName();
		if (id.equals(Service.PROPERTY_COLOR))
			return ((Service) node).getColor().getRGB();
		if (id.equals(Service.PROPERTY_FLOOR))
			return Integer.toString(((Service) node).getEtage());
		if (id.equals(Enterprise.PROPERTY_CAPITAL))
			return Integer.toString(((Enterprise) node).getCapital());
		if (id.equals(Employe.PROPERTY_FIRSTNAME))
			return (((Employe) node).getPrenom());
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (id.equals(Node.PROPERTY_RENAME)) {
			node.setName((String) value);
		} else if (id.equals(Employe.PROPERTY_FIRSTNAME)) {
			((Employe) node).setPrenom((String)value);
		} else if (id.equals(Service.PROPERTY_COLOR)) {
			Color newColor = new Color(null, (RGB) value);
			((Service) node).setColor(newColor);
		} else if (id.equals(Service.PROPERTY_FLOOR)) {
			try {
				Integer floor = Integer.parseInt((String) value);
				((Service) node).setEtage(floor);
			} catch (NumberFormatException e) {
			}
		} else if (id.equals(Enterprise.PROPERTY_CAPITAL)) {
			try {
				Integer capital = Integer.parseInt((String) value);
				((Enterprise) node).setCapital(capital);
			} catch (NumberFormatException e) {
			}
		}
	}

}
