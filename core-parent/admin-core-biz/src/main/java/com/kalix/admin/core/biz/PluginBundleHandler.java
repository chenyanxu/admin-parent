package com.kalix.admin.core.biz;

import org.osgi.framework.Bundle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2015/12/30.
 * 插件bundle处理类
 */
public class PluginBundleHandler {
    private static Map<Bundle, PluginBundleHandler> bundleHandlers = new HashMap<Bundle, PluginBundleHandler>();
    private static Map<String, Bundle> bundleMap = new HashMap<String, Bundle>();
    private static final String PLUGIN_HEADER = "Kalix-Plugin";
    private Bundle bundle;

    private PluginBundleHandler(Bundle bundle) {
        this.bundle = bundle;
    }

    public static PluginBundleHandler getInstance(Bundle bundle) {
        if (!bundleHandlers.containsKey(bundle)) {
            bundleHandlers.put(bundle, new PluginBundleHandler(bundle));
        }
        return bundleHandlers.get(bundle);
    }

    public static void processBundle(Bundle bundle) {
        PluginBundleHandler bundleHandler = getInstance(bundle);
        bundleHandler.scanBundle(bundle);
    }

    private void scanBundle(Bundle bundle) {
        String header = (String) bundle.getHeaders().get(PLUGIN_HEADER);
        if (header == null || header.isEmpty()) {
            return;
        }
        if (!bundleMap.containsKey(header)) {
            bundleMap.put(header, bundle);
        }
    }

    static public Bundle getBundle(String key) {
        return bundleMap.get(key);
    }
}
