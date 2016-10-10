/**
 * 插件数据仓库
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.plugin.store.PluginStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.admin.plugin.model.PluginModel',
    alias: 'store.pluginStore',
    xtype: 'pluginStore',
    storeId: "pluginStore",
    proxyUrl: CONFIG.restRoot + '/camel/rest/plugins'
});