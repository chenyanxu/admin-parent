/**
 * 应用新增和修改表单
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.app.application.view.ApplicationWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.app.application.viewModel.ApplicationViewModel',
        'kalix.controller.BaseWindowController',
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.applicationWindow',
    viewModel: 'applicationViewModel',
    controller: {
        type: 'baseWindowController',
        storeId: 'applicationStore'
    },
    xtype: "applicationWindow",
    width: 400,
    items: [{
        xtype: 'baseForm',
        items: [{
            fieldLabel: '名称',
            allowBlank: false,
            bind: {
                value: '{rec.name}'
            }
        },
        {
            fieldLabel: '应用代码',
            allowBlank: false,
            bind: {
                value: '{rec.code}'
            }
        }, {
            fieldLabel: '备注',
            allowBlank: false,
            xtype: 'textarea',
            bind: {
                value: '{rec.remark}'
            }
        }]
    }]
});