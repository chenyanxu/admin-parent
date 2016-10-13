/**
 * 审计模型
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.sys.auditconfig.viewModel.AuditConfigViewModel', {
    extend: 'kalix.viewmodel.BaseViewModel',
    alias: 'viewmodel.auditconfigViewModel',
    data: {
        // batchDeleteUrl为执行批量删除服务的地址
        batchDeleteUrl: CONFIG.restRoot + '/camel/rest/auditconfigs/remove',
    }
});