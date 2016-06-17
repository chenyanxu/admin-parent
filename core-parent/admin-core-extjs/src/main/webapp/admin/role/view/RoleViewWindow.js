/**
 * 角色查看表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.admin.role.view.RoleViewWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.admin.role.viewModel.RoleViewModel',
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.roleViewWindow',
    viewModel: 'roleViewModel',
    xtype: "roleViewWindow",
    width: 400,
    items: [{
        defaults: {readOnly: true},
        xtype: 'baseForm',
        items: [{
            fieldLabel: '名称',
            allowBlank: false,
            bind: {
                activeError: '{validation.name}',
                value: '{rec.name}'
            }
        },
            {
                fieldLabel: '所属应用',
                allowBlank: false,
                bind: {
                    activeError: '{validation.app}',
                    value: '{rec.app}'
                }
            }, {
                fieldLabel: '备注',
                allowBlank: false,
                xtype: 'textarea',
                bind: {
                    activeError: '{validation.remark}',
                    value: '{rec.remark}'
                }
            }]
    }]
});