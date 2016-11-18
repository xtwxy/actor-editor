package com.wincom.actor.editor.tutogef.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.tutogef.model.Employe;
import com.wincom.actor.editor.tutogef.model.Enterprise;
import com.wincom.actor.editor.tutogef.model.Service;
import com.wincom.actor.editor.tutogef.part.AppEditPartFactory;

public class MyGraphicalEditor extends GraphicalEditor {
	Logger log = LoggerFactory.getLogger(this.getClass());

	public static final String ID = "tutogef.mygraphicaleditor";

	public MyGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	public Enterprise createEnterprise() {
    	log.info("check");
		Enterprise e = new Enterprise();
		e.setAddress("No Such an Address");
		e.setCapital(1000000);
		e.setName("Wincom");

		Service s1 = new Service();
		s1.setEtage(2);
		s1.setLayout(new Rectangle(30, 50, 250, 150));
		s1.setName("XOXO Service");
	
		Employe e1 = new Employe();
		e1.setName("Nameless1");
		e1.setPrenom("P1");
		e1.setLayout(new Rectangle(25, 40, 60, 40));
		s1.addChild(e1);

		Employe e2 = new Employe();
		e2.setName("Nameless2");
		e2.setPrenom("P2");
		e2.setLayout(new Rectangle(100, 60, 60, 40));
		s1.addChild(e2);

		Employe e3 = new Employe();
		e3.setName("Nameless3");
		e3.setPrenom("P3");
		e3.setLayout(new Rectangle(180, 90, 60, 40));
		s1.addChild(e3);

		e.addChild(s1);
		
		Service s2 = new Service();
		s2.setEtage(1);
		s2.setName("F**K Service");
		s2.setLayout(new Rectangle(220, 230, 250, 150));

		Employe e4 = new Employe();
		e4.setName("Nameless4");
		e4.setPrenom("P4");
		e4.setLayout(new Rectangle(40, 70, 60, 40));
		s2.addChild(e4);

		Employe e5 = new Employe();
		e5.setName("Nameless5");
		e5.setPrenom("P5");
		e5.setLayout(new Rectangle(170, 100, 60, 40));
		s2.addChild(e5);

		e.addChild(s2);
		return e;
	}

	@Override
	protected void configureGraphicalViewer() {
    	log.info("check");
		super.configureGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new AppEditPartFactory());
	}

	@Override
	protected void initializeGraphicalViewer() {
    	log.info("check");
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(createEnterprise());
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
    	log.info("check");

	}

}
