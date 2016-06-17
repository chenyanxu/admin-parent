/**
 * 审计查看表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.admin.audit.view.AuditViewWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
        requires: [
            'kalix.admin.audit.viewModel.AuditViewModel',
            'kalix.admin.user.store.UserStore'
        ],
        alias: 'widget.auditViewWindow',
        viewModel: 'auditViewModel',
        xtype: "auditViewWindow",
        width: 400,
    items: [{
            defaults: {readOnly: true},
            xtype: 'baseForm',
            items: [{
                fieldLabel: '应用名称',
                bind: {
                    value: '{rec.appName}'
                }
            },
            {
                fieldLabel: '功能名称',
                bind: {
                    value: '{rec.funName}'
                }
            }, {
                    fieldLabel: '操作人',
                    bind: {
                        value: '{rec.actor}'
                    }
                },
                {
                    fieldLabel: '操作',
                    bind: {
                        value: '{rec.action}'
                    }
                },{
                fieldLabel: '操作内容',
                xtype: 'textarea',
                bind: {
                    value: '{rec.content}'
                }
            }]
        }]
});