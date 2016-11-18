package com.wincom.actor.editor.tutogef;

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

public class Activator extends AbstractUIPlugin {
	Logger log = LoggerFactory.getLogger(this.getClass());

	public static final String PLUGIN_ID = "com.wincom.actor.editor.tutogef"; //$NON-NLS-1$

	private static Activator plugin;

	public Activator() {
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		configureLogbackInBundle(context.getBundle());
		plugin = this;
	}

	private void configureLogbackInBundle(Bundle bundle) throws JoranException, IOException {
		log.info("check");
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator jc = new JoranConfigurator();
		jc.setContext(context);
		context.reset();

		String logDirProperty = "workspace";// ... get alternative log directory
											// location
		context.putProperty("LOG_DIR", logDirProperty);

		URL logbackConfigFileUrl = FileLocator.find(bundle, new Path("logback.xml"), null);
		jc.doConfigure(logbackConfigFileUrl.openStream());
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
}
