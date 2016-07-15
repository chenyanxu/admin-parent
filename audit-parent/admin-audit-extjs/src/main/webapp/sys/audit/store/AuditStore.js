/**
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.audit.store.AuditStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.sys.audit.model.AuditModel',
    alias: 'store.auditStore',
    xtype: 'auditStore',
    storeId: "auditStore",
    proxyUrl: CONFIG.restRoot + '/camel/rest/audits'
});