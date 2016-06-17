package com.kalix.admin.core.extjs.internal;

import com.kalix.framework.core.api.osgi.KalixBundleActivator;
import com.kalix.framework.core.util.SystemUtil;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;

/**
 * Created by sunlf on 14-3-23.
 */
//public class InitActivator implements BundleActivator {
public class InitActivator extends KalixBundleActivator {
    private ServiceReference reference;
    private HttpService httpService;


    @Override
    public void start(BundleContext bundleContext) throws Exception {
        SystemUtil.startBundlePrintln(bundleContext);

        reference = bundleContext.getServiceReference(HttpService.class.getName());
        httpService = (HttpService) bundleContext.getService(reference);
        httpService.registerResources(contextPath + "/app/admin", "/admin", null);
        httpService.registerResources(contextPath + "/app/app", "/app", null);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        SystemUtil.stopBundlePrintln(bundleContext);

        if (reference != null)
            bundleContext.ungetService(reference);
        if (httpService != null) {
            httpService.unregister(contextPath + "/app/admin");
            httpService.unregister(contextPath + "/app/app");
        }
    }
}
