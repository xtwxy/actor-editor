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
package com.wincom.actor.editor.flow;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wincom.actor.editor.flow.model.Activity;
import com.wincom.actor.editor.flow.model.ParallelActivity;
import com.wincom.actor.editor.flow.model.SequentialActivity;

/**
 * Handles the creation of the palette for the Flow Editor.
 * 
 * @author Daniel Lee
 */
public class FlowEditorPaletteFactory {
	private static Logger log = LoggerFactory.getLogger(FlowEditorPaletteFactory.class);
	private static List createCategories(PaletteRoot root) {
		log.info("check");
		List categories = new ArrayList();
		categories.add(createControlGroup(root));
		categories.add(createComponentsDrawer());
		return categories;
	}

	private static PaletteContainer createComponentsDrawer() {
		log.info("check");

		PaletteDrawer drawer = new PaletteDrawer("Components", null);

		List entries = new ArrayList();

		CombinedTemplateCreationEntry combined = new CombinedTemplateCreationEntry(
				"Activity", "Create a new Activity Node", Activity.class,
				new SimpleFactory(Activity.class),
				ImageDescriptor.createFromFile(FlowPlugin.class,
						"images/gear16.gif"), ImageDescriptor.createFromFile(
						Activity.class, "images/gear16.gif"));
		entries.add(combined);

		combined = new CombinedTemplateCreationEntry("Sequential Activity",
				"Create a Sequential Activity", SequentialActivity.class,
				new SimpleFactory(SequentialActivity.class),
				ImageDescriptor.createFromFile(FlowPlugin.class,
						"images/sequence16.gif"),
				ImageDescriptor.createFromFile(FlowPlugin.class,
						"images/sequence16.gif"));
		entries.add(combined);

		combined = new CombinedTemplateCreationEntry("Parallel Activity",
				"Create a  Parallel Activity", ParallelActivity.class,
				new SimpleFactory(ParallelActivity.class),
				ImageDescriptor.createFromFile(FlowPlugin.class,
						"images/parallel16.gif"),
				ImageDescriptor.createFromFile(FlowPlugin.class,
						"images/parallel16.gif"));
		entries.add(combined);

		drawer.addAll(entries);
		return drawer;
	}

	private static PaletteContainer createControlGroup(PaletteRoot root) {
		log.info("check");
		PaletteGroup controlGroup = new PaletteGroup("Control Group");

		List entries = new ArrayList();

		ToolEntry tool = new SelectionToolEntry();
		entries.add(tool);
		root.setDefaultEntry(tool);

		tool = new MarqueeToolEntry();
		entries.add(tool);

		PaletteSeparator sep = new PaletteSeparator(
				"com.wincom.actor.editor.flow.flowplugin.sep2");
		sep.setUserModificationPermission(PaletteEntry.PERMISSION_NO_MODIFICATION);
		entries.add(sep);

		tool = new ConnectionCreationToolEntry("Connection Creation",
				"Creating connections", null, ImageDescriptor.createFromFile(
						FlowPlugin.class, "images/connection16.gif"),
				ImageDescriptor.createFromFile(Activity.class,
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
		log.info("check");
		PaletteRoot flowPalette = new PaletteRoot();
		flowPalette.addAll(createCategories(flowPalette));
		return flowPalette;
	}

}
