package com.kalix.admin.state.core.internal;

import com.kalix.framework.core.api.osgi.BaseBundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Created by sunlf on 14-3-23.
 */
public class InitActivator extends BaseBundleActivator {

    private static BundleContext context;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        super.start(bundleContext);
        context = bundleContext;
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        super.stop(bundleContext);
        context = null;
    }

    public static BundleContext getBundleContext() {
        return context;
    }
}
