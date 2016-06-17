/**
 * 审计数据仓库
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.audit.store.AuditStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.admin.audit.model.AuditModel',
    alias: 'store.auditStore',
    xtype: 'auditStore',
    storeId: "auditStore",
    proxyUrl: CONFIG.restRoot + '/camel/rest/audits'
});