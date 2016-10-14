/**
 * 审计首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.auditconfig.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.sys.auditconfig.view.AuditConfigGrid',
        'kalix.sys.auditconfig.view.AuditConfigSearchForm',
        'kalix.sys.auditconfig.viewModel.AuditConfigViewModel'
    ],
    storeId: 'auditconfigStore',
    viewModel: 'auditconfigViewModel',
    items: [
        {
            title: '审计配置查询',
            xtype: 'auditconfigSearchForm'
        }, {
            xtype: 'auditconfigGridPanel',
            id: 'auditconfigGridPanel',
            title: '审计配置列表'
        }
    ]
});
