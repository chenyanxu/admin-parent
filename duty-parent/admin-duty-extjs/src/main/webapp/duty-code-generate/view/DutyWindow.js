/**
 * 职位添加和修改表单
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.app.duty.view.DutyWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.app.duty.viewModel.DutyViewModel',
        'kalix.controller.BaseWindowController',
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.dutyWindow',
    viewModel: 'dutyViewModel',
    controller: {
        type: 'baseWindowController',
        storeId: 'dutyStore'
    },
    xtype: "dutyWindow",
    width: 400,
    //todo 在此修改表单
    items: [{
        xtype: 'baseForm',
        items: [{
            fieldLabel: '职位名称',
            allowBlank: false,
            bind: {
                activeError: '{validation.name}',
                value: '{rec.name}'
            }
        },
        {
            fieldLabel: '所在部门',
            allowBlank: false,
            bind: {
                activeError: '{validation.title}',
                value: '{rec.department}'
            }
        }, {
            fieldLabel: '职位描述',
            allowBlank: false,
            xtype: 'textarea',
            bind: {
                activeError: '{validation.content}',
                value: '{rec.comment}'
            }
        }]
    }]
});