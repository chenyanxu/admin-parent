package com.kalix.admin.core.web.internal;

import com.kalix.framework.core.api.web.IApplication;
import com.kalix.framework.core.util.SystemUtil;
import org.apache.camel.impl.osgi.tracker.BundleTracker;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by sunlf on 14-3-23.
 */
public class InitActivator implements BundleActivator {
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        SystemUtil.startBundlePrintln(bundleContext);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        SystemUtil.stopBundlePrintln(bundleContext);
        ServiceReference[] refs = bundleContext.getServiceReferences(IApplication.class.getName(), null);
    }
}
