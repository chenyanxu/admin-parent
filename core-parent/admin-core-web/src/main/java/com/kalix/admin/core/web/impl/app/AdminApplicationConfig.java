package com.kalix.admin.core.web.impl.app;

import com.kalix.framework.core.api.web.IApplication;
import org.osgi.framework.BundleContext;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;

import java.util.Dictionary;

/**
 * Created by Administrator on 2016-08-04.
 */
public class AdminApplicationConfig implements ManagedService{
    private BundleContext bundleContext;
    private IApplication application;
    @Override
    public void updated(Dictionary<String, ?> dictionary) throws ConfigurationException {
        if(application==null){
            bundleContext.registerService(IApplication.class.getName(),new AdminApplicationImpl(),null);
        }
// try {
//            ServiceReference[] refs = bundleContext.getServiceReferences(IApplication.class.getName(), null);
//            if (refs != null) {
//                for (ServiceReference ref : refs) {
//                    IApplication service= (IApplication) bundleContext.getService(ref);
//                    //service.updateConfig();
//                }
//            }
//
//        } catch (InvalidSyntaxException e) {
//            e.printStackTrace();
//        }
    }

    public void setBundleContext(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }




}
