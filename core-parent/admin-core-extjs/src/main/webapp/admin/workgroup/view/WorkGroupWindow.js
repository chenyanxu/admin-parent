/**
 * 工作组新增和修改表单
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.workgroup.view.WorkGroupWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.admin.workgroup.viewModel.WorkGroupViewModel',
        'kalix.controller.BaseWindowController',
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.workgroupWindow',
    viewModel: 'workgroupViewModel',
    controller: {
        type: 'baseWindowController',
        storeId: 'workgroupStore'
    },
    xtype: "workgroupWindow",
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