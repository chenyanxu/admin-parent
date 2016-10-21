package com.kalix.admin.core.extjs.internal;

import com.kalix.framework.core.api.osgi.KalixBundleActivator;
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
        super.start(bundleContext);

        reference = bundleContext.getServiceReference(HttpService.class.getName());
        httpService = (HttpService) bundleContext.getService(reference);

        if(deploy){
            httpService.registerResources(contextPath + "/app/admin", "/min/admin", null);
            httpService.registerResources(contextPath + "/app/admin/application", "/min/sys/application", null);
            httpService.registerResources(contextPath + "/app/admin/function", "/min/sys/function", null);
            httpService.registerResources(contextPath + "/admin/resources/images", "/min/resources/images", null);
        }
        else
        {
            httpService.registerResources(contextPath + "/app/admin", "/admin", null);
            httpService.registerResources(contextPath + "/app/admin/application", "/sys/application", null);
            httpService.registerResources(contextPath + "/app/admin/function", "/sys/function", null);
            httpService.registerResources(contextPath + "/admin/resources/images", "/resources/images", null);
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        super.stop(bundleContext);

        if (reference != null)
            bundleContext.ungetService(reference);
        if (httpService != null) {
            httpService.unregister(contextPath + "/app/admin");
            httpService.unregister(contextPath + "/app/admin/application");
            httpService.unregister(contextPath + "/app/admin/function");
        }
    }
}
