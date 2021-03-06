package com.wincom.actor.editor.test2.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ColorPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ElementModel implements IAdaptable, IPropertySource, Cloneable, Serializable {

	private static final long serialVersionUID = -7289522210862727774L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	transient protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	protected String name;
	protected ElementModel parent;
	private Rectangle layout = new Rectangle(10, 10, 100, 50);
	private Color backgroundColor;
	private Color foregroundColor;

	public static final String NAME = "name";
	public static final String PARENT = "parent";
	public static final String BACKGROUND_COLOR = "backgroundColor";
	public static final String FOREGROUND_COLOR = "foregroundColor";
	
	public static final String PROPERTY_LAYOUT = "ElementLayout";
	public static final String PROPERTY_RENAME = "ElementRename";

	private static final IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new TextPropertyDescriptor(NAME, NAME),
			new TextPropertyDescriptor(PARENT, PARENT),
			new ColorPropertyDescriptor(BACKGROUND_COLOR, BACKGROUND_COLOR),
			new ColorPropertyDescriptor(FOREGROUND_COLOR, FOREGROUND_COLOR)
		};
	
	@Override
	public Object getPropertyValue(Object id) {
		if(NAME.equals(id)) {
			return name;
		} else if(PARENT.equals(id)) {
			return parent;
		} else if(BACKGROUND_COLOR.equals(id)) {
			return backgroundColor.getRGB();
		} else if(FOREGROUND_COLOR.equals(id)) {
			return foregroundColor.getRGB();
		}
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		if(NAME.equals(id)) {
			return name != null;
		} else if(PARENT.equals(id)) {
			return parent != null;
		} else if(BACKGROUND_COLOR.equals(id)) {
			return backgroundColor != null;
		} else if(FOREGROUND_COLOR.equals(id)) {
			return foregroundColor != null;
		}
		return false;
	}
	
	@Override
	public void setPropertyValue(Object id, Object value) {
		if(NAME.equals(id)) {
			setName((String) value);
		} else if(PARENT.equals(id)) {
			setParent((ElementModel) value);
		} else if(BACKGROUND_COLOR.equals(id)) {
			Color newColor = new Color(null, (RGB) value);
			setBackgroundColor(newColor);
		} else if(FOREGROUND_COLOR.equals(id)) {
			Color newColor = new Color(null, (RGB) value);
			setForegroundColor(newColor);
		}
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

	public ElementModel() { 
		log.info("new ElementModel()");
		setForegroundColor(new Color(null, (new Double(Math.random() * 128)).intValue(),
				(new Double(Math.random() * 128)).intValue(), (new Double(Math.random() * 128)).intValue()));
		setBackgroundColor(new Color(null, (new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128));
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		listeners.addPropertyChangeListener(l);
	}

	protected void firePropertyChange(String prop, Object old, Object newValue) {
		listeners.firePropertyChange(prop, old, newValue);
	}

	protected void fireStructureChange(String prop, Object child) {
		log.debug("prop = " + prop
				+ ", child = " + child);
		listeners.firePropertyChange(prop, null, child);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		listeners.removePropertyChangeListener(l);
	}
	
	public void setName(String newName) {
		String oldName = this.name;
		this.name = newName;
		listeners.firePropertyChange(PROPERTY_RENAME, oldName, newName);
	}

	public String getName() {
		return this.name;
	}

	public void setLayout(Rectangle newLayout) {
		log.info("check");
		Rectangle oldLayout = this.layout;
		this.layout = newLayout;
		listeners.firePropertyChange(PROPERTY_LAYOUT, oldLayout, newLayout);
	}

	public Rectangle getLayout() {
		return this.layout;
	}

	public ElementModel getParent() {
		return parent;
	}

	public void setParent(ElementModel newParent) {
		Rectangle oldParent = this.layout;
		this.parent = newParent;
		listeners.firePropertyChange(PARENT, oldParent, newParent);
	}
	
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color value) {
		Color old = this.backgroundColor;
		this.backgroundColor = value;
		firePropertyChange(BACKGROUND_COLOR, old, value);
	}

	public Color getForegroundColor() {
		return foregroundColor;
	}

	public void setForegroundColor(Color newForegroundColor) {
		Color old = this.foregroundColor;
		this.foregroundColor = newForegroundColor;
		firePropertyChange(FOREGROUND_COLOR, old, newForegroundColor);
	}

	@Override
	public Object getEditableValue() {
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementModel other = (ElementModel) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if (adapter == IPropertySource.class) {
			return this;
		}
		return null;
	}
	
	public abstract List<ElementModel> getChildren();
}
