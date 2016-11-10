/*******************************************************************************
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.wincom.actor.editor.flow;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * Plugin class used by the flow editor.
 */
public class FlowPlugin extends AbstractUIPlugin {
	Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Creates a new FlowPlugin with the given descriptor
	 * 
	 * @param descriptor
	 *            the descriptor
	 */
	public FlowPlugin() {
		super();
		log.info("check");
	}
	
	@Override
    public void start(BundleContext bundleContext) throws Exception {
		log.info("check");
            configureLogbackInBundle(bundleContext.getBundle());
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
		log.info("check");
    }

    private void configureLogbackInBundle(Bundle bundle) throws JoranException, IOException {
		log.info("check");
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            JoranConfigurator jc = new JoranConfigurator();
            jc.setContext(context);
            context.reset();

            // overriding the log directory property programmatically
            String logDirProperty = "workspace";// ... get alternative log directory location
            context.putProperty("LOG_DIR", logDirProperty);

            // this assumes that the logback.xml file is in the root of the bundle.
            URL logbackConfigFileUrl = FileLocator.find(bundle, new Path("logback.xml"),null);
            jc.doConfigure(logbackConfigFileUrl.openStream());
    }
}
