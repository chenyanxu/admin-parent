package com.kalix.admin.duty.entities.internal;

import com.kalix.admin.duty.entities.DutyBean;
import com.kalix.admin.duty.entities.DutyUserBean;
import com.kalix.framework.core.api.osgi.CascadeBundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Created by sunlf on 14-3-23.
 */
public class InitActivator extends CascadeBundleActivator {
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        super.start(bundleContext);
        unRegisterCascade(DutyBean.class);
        unRegisterCascade(DutyUserBean.class);
        registerCascade(DutyBean.class);
        registerCascade(DutyUserBean.class);
    }
}
