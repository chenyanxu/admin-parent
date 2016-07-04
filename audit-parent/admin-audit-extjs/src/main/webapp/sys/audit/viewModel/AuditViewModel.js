/**
 * 审计模型
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.sys.audit.viewModel.AuditViewModel', {
    extend: 'kalix.viewmodel.BaseViewModel',
    alias: 'viewmodel.auditViewModel',
    data: {
        // batchDeleteUrl为执行批量删除服务的地址
        batchDeleteUrl:'/kalix/camel/rest/audits/remove',
    }
});