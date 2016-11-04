package com.wincom.actor.editor.app.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.util.PropertyChangeEvent;

public class SpecificPart extends AbstractGraphicalEditPart {

	@Override
	protected IFigure createFigure() {
		return null;
	}

	@Override
	protected void createEditPolicies() {

	}
	public void activate() {
        if (!isActive()) {
            super.activate();
            //((PropertyAwareModel) this.getModel()).addPropertyChangeListener(this);
        }
    }

	public void deactivate() {

        if (isActive()) {
            //((PropertyAwareModel) this.getModel()).removePropertyChangeListener(this);
            super.deactivate();
        }
    }	
	
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getProperty();
	}
}
