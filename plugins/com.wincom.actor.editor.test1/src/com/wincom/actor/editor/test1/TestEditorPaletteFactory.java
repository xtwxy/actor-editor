/*******************************************************************************
 * Copyright (c) 2003, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.wincom.actor.editor.test1;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.jface.resource.ImageDescriptor;

import com.wincom.actor.editor.test1.model.Figure1Model;
import com.wincom.actor.editor.test1.model.Figure2Model;
import com.wincom.actor.editor.test1.model.Figure3Model;

/**
 * Handles the creation of the palette for the Test Editor.
 * 
 * @author Daniel Lee
 */
public class TestEditorPaletteFactory {

	private static List createCategories(PaletteRoot root) {
		List categories = new ArrayList();
		categories.add(createControlGroup(root));
		categories.add(createComponentsDrawer());
		return categories;
	}

	private static PaletteContainer createComponentsDrawer() {

		PaletteDrawer drawer = new PaletteDrawer("Components", null);

		List entries = new ArrayList();

		CombinedTemplateCreationEntry combined = new CombinedTemplateCreationEntry(
				"Figure1", "Create a new Figure1 Node", Figure1Model.class,
				new SimpleFactory(Figure1Model.class),
				ImageDescriptor.createFromFile(TestPlugin.class,
						"images/gear16.gif"), ImageDescriptor.createFromFile(
						Figure1Model.class, "images/gear16.gif"));
		entries.add(combined);

		combined = new CombinedTemplateCreationEntry("Figure2",
				"Create a Figure2", Figure2Model.class,
				new SimpleFactory(Figure2Model.class),
				ImageDescriptor.createFromFile(TestPlugin.class,
						"images/sequence16.gif"),
				ImageDescriptor.createFromFile(TestPlugin.class,
						"images/sequence16.gif"));
		entries.add(combined);

		combined = new CombinedTemplateCreationEntry("Figure3",
				"Create a  Figure3", Figure3Model.class,
				new SimpleFactory(Figure3Model.class),
				ImageDescriptor.createFromFile(TestPlugin.class,
						"images/parallel16.gif"),
				ImageDescriptor.createFromFile(TestPlugin.class,
						"images/parallel16.gif"));
		entries.add(combined);

		drawer.addAll(entries);
		return drawer;
	}

	private static PaletteContainer createControlGroup(PaletteRoot root) {
		PaletteGroup controlGroup = new PaletteGroup("Control Group");

		List entries = new ArrayList();

		ToolEntry tool = new SelectionToolEntry();
		entries.add(tool);
		root.setDefaultEntry(tool);

		PaletteSeparator sep = new PaletteSeparator(
				"com.wincom.actor.editor.flow.flowplugin.sep2");
		sep.setUserModificationPermission(PaletteEntry.PERMISSION_NO_MODIFICATION);
		entries.add(sep);

		tool = new ConnectionCreationToolEntry("Connection",
				"Creating connections", null, ImageDescriptor.createFromFile(
						TestPlugin.class, "images/connection16.gif"),
				ImageDescriptor.createFromFile(Figure1Model.class,
						"images/connection16.gif"));
		entries.add(tool);
		controlGroup.addAll(entries);
		return controlGroup;
	}

	/**
	 * Creates the PaletteRoot and adds all Palette elements.
	 * 
	 * @return the root
	 */
	public static PaletteRoot createPalette() {
		PaletteRoot flowPalette = new PaletteRoot();
		flowPalette.addAll(createCategories(flowPalette));
		return flowPalette;
	}

}
