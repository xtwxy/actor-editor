package com.wincom.actor.editor.test2.model;

public class PortModel extends FigureModel {
	public static final String PROVIDED = "provided";
	public static final String REQUIRED = "required";
	private static final long serialVersionUID = 2969510135967969883L;
	private ProvidedPortModel provided;
	private RequiredPortModel required;

	public ProvidedPortModel getProvided() {
		return provided;
	}

	public void setProvided(ProvidedPortModel provided) {
		ProvidedPortModel old = this.provided;
		this.provided = provided;
		firePropertyChange(PROVIDED, old, provided);
	}

	public RequiredPortModel getRequired() {
		return required;
	}

	public void setRequired(RequiredPortModel required) {
		RequiredPortModel old = this.required;
		this.required = required;
		firePropertyChange(REQUIRED, old, provided);
	}
}
