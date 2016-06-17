/**
 * 审计首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.audit.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.admin.audit.view.AuditGrid',
        'kalix.admin.audit.view.AuditSearchForm',
        'kalix.admin.audit.viewModel.AuditViewModel'
    ],
    storeId: 'auditStore',
    viewModel: 'auditViewModel',
    items: [
        {
            title: '审计查询',
            xtype: 'auditSearchForm'
        }, {
            xtype: 'auditGridPanel',
            id: 'auditGridPanel',
            title: '审计列表',
            margin: 10
        }
    ]
});
