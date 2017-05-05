/**
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.templateconfig.store.TemplateConfigStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.sys.templateconfig.model.TemplateConfigModel',
    alias: 'store.templateconfigStore',
    xtype: 'templateconfigStore',
    storeId: "templateconfigStore",
    proxyUrl: CONFIG.restRoot + '/camel/rest/templateconfigs'
});