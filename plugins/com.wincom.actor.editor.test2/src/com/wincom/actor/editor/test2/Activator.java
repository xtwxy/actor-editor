package com.wincom.actor.editor.test2;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {
	Logger log = LoggerFactory.getLogger(this.getClass());

	// The plug-in ID
	public static final String PLUGIN_ID = "com.wincom.actor.editor.test2"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		configureLogbackInBundle(context.getBundle());
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static Activator getDefault() {
		return plugin;
	}

	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	private void configureLogbackInBundle(Bundle bundle) throws JoranException, IOException {
		log.info("check");
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator jc = new JoranConfigurator();
		jc.setContext(context);
		context.reset();

		// overriding the log directory property programmatically
		String logDirProperty = "workspace";// ... get alternative log directory
											// location
		context.putProperty("LOG_DIR", logDirProperty);

		// this assumes that the logback.xml file is in the root of the bundle.
		URL logbackConfigFileUrl = FileLocator.find(bundle, new Path("logback.xml"), null);
		jc.doConfigure(logbackConfigFileUrl.openStream());
	}
}
