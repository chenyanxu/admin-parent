/**
 * 插件数据仓库
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.app.plugin.store.PluginStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.app.plugin.model.PluginModel',
    alias: 'store.pluginStore',
    xtype: 'pluginStore',
    storeId: "pluginStore",
    proxyUrl: '/kalix/camel/rest/plugins'
});