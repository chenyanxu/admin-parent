package com.kalix.admin.template.extjs.internal;

import com.kalix.framework.core.api.osgi.KalixBundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;

/**
 * Created by sunlf on 14-3-23.
 */
public class InitActivator extends KalixBundleActivator {
    private ServiceReference reference;
    private HttpService httpService;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        super.start(bundleContext);

        reference = bundleContext.getServiceReference(HttpService.class.getName());
        httpService = (HttpService) bundleContext.getService(reference);

        if(deploy){
            httpService.registerResources(contextPath + "/app/sys/template", "/min/sys/template", null);
            httpService.registerResources(contextPath + "/app/sys/templateconfig", "/min/sys/templateconfig", null);
        }
        else{
            httpService.registerResources(contextPath + "/app/sys/template", "sys/template", null);
            httpService.registerResources(contextPath + "/app/sys/templateconfig", "sys/templateconfig", null);
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        super.stop(bundleContext);

        if (httpService != null) {
            httpService.unregister(contextPath + "/app/sys/template");
            httpService.unregister(contextPath + "/app/sys/templateconfig");
        }

        if (reference != null)
            bundleContext.ungetService(reference);
    }
}
