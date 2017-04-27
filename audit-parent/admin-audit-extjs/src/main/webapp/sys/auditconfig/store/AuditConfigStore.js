/**
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.auditconfig.store.AuditConfigStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.sys.auditconfig.model.AuditConfigModel',
    alias: 'store.auditconfigStore',
    xtype: 'auditconfigStore',
    storeId: 'auditconfigStore',
    proxyUrl: CONFIG.restRoot + '/camel/rest/auditconfigs'
});