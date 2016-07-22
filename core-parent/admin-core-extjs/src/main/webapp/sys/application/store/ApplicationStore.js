/**
 * 应用数据仓库
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.application.store.ApplicationStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.admin.application.model.ApplicationModel',
    alias: 'store.applicationStore',
    xtype: 'applicationStore',
    storeId: "applicationStore",
    proxyUrl: CONFIG.restRoot + '/camel/rest/applications'
});