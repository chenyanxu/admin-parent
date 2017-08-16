/**
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.template.store.TemplateStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.sys.template.model.TemplateModel',
    alias: 'store.templateStore',
    xtype: 'templateStore',
    storeId: "templateStore",
    proxyUrl: CONFIG.restRoot + '/camel/rest/admin/templates'
});