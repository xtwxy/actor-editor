package com.wincom.actor.editor.tutogef.model;

public class Employe extends Node {
	public static final String PROPERTY_FIRSTNAME = "EmployePrenom";
	
	private String prenom;

	public void setPrenom(String prenom) {
		String oldPrenom = this.prenom;
		this.prenom = prenom;
		getListeners().firePropertyChange(PROPERTY_FIRSTNAME, oldPrenom, this.prenom);
	}

	public String getPrenom() {
		return this.prenom;
	}
}
