package com.wincom.actor.editor.tutogef.wizard;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RenameWizard extends Wizard {
	Logger log = LoggerFactory.getLogger(this.getClass());
	private class RenamePage extends WizardPage {

		public Text nameText;

		public RenamePage(String pageName) {
			super(pageName);
			setTitle("Rename");
			setDescription("Rename a component");
		}

		@Override
		public void createControl(Composite parent) {
			Composite composite = new Composite(parent, SWT.NONE);
			Label lab = new Label(composite, SWT.NONE);
			lab.setText("Rename to: ");
			nameText = new Text(composite, SWT.NONE);
			nameText.setText(oldName);
			RowLayout l = new RowLayout();
			l.pack = true;
			composite.setLayout(l);
			setControl(composite);
		}
	}

	private String oldName;
	private String newName;

	public RenameWizard(String oldName) {
		log.info("oldName = " + oldName);
		this.oldName = oldName;
		this.newName = null;
		addPage(new RenamePage("MyRenamePage"));
	}

	@Override
	public boolean performFinish() {
		RenamePage page = (RenamePage) getPage("MyRenamePage");
		if (page.nameText.getText().isEmpty()) {
			page.setErrorMessage("Le champ nom est vide!");
			return false;
		}
		newName = page.nameText.getText();
		return true;
	}

	public String getRenameValue() {
		return newName;
	}
}
