/**
 * 应用添加和修改表单
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.application.view.ApplicationWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.controller.BaseWindowController',
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.applicationWindow',
    controller: {
        type: 'baseWindowController',
        storeId: 'applicationStore'
    },
    xtype: 'applicationWindow',
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
        },{
            fieldLabel: '应用图标',
            allowBlank: false,
            bind: {
                value: '{rec.iconCls}'
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