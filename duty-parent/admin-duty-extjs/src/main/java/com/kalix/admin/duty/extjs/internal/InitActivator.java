package com.kalix.admin.duty.extjs.internal;

import com.kalix.framework.core.api.osgi.KalixBundleActivator;
import com.kalix.framework.core.util.SystemUtil;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
/**
 * Created by sunlf on 14-3-23.
 */
public class InitActivator extends KalixBundleActivator {

    public static final String KALIX_APP_ROFFICE_PATH = "/kalix/app/duty";
    public static final String KALIX_ROFFICE_RESOURCES_IMAGES = "/kalix/duty/resources/images";
    private ServiceReference reference;
    private HttpService httpService;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        SystemUtil.startBundlePrintln(bundleContext);

        reference = bundleContext.getServiceReference(HttpService.class.getName());
        httpService = (HttpService) bundleContext.getService(reference);
        httpService.registerResources(contextPath + "/app/app/duty", "/duty", null);
        httpService.registerResources(contextPath +"/app/duty/resources/images", "/resources/images", null);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        SystemUtil.stopBundlePrintln(bundleContext);

        if(httpService!=null){
            httpService.unregister(contextPath +"/app/app/duty");
            httpService.unregister(contextPath +"/app/duty/resources/images");
        }
        if (reference != null)
                    bundleContext.ungetService(reference);
    }
}
