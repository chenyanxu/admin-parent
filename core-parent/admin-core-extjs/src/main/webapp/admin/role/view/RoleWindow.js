/**
 * 角色新增和修改表单
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.role.view.RoleWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.admin.role.viewModel.RoleViewModel',
        'kalix.controller.BaseWindowController',
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.roleWindow',
    viewModel: 'roleViewModel',
    controller: {
        type: 'baseWindowController',
        storeId: 'roleStore'
    },
    xtype: "roleWindow",
    width: 400,

    items: [{
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