/**
 * 状态机添加表单
 *
 * @author hqj <br/>
 *         date:2017-4-26
 * @version 1.0.0
 */

Ext.define('kalix.admin.statemachine.view.StateMachineWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.view.components.common.BaseComboBox',
        'kalix.admin.statemachine.controller.StateMachineWindowController',
    ],
    alias: 'widget.stateMachineWindow',
    controller: {
        type: 'stateMachineWindowController'
    },
    xtype: "stateMachineWindow",
    width: 400,
    items: [{
        xtype: 'baseForm',
        items: [
            {
                xtype: 'baseComboBox',
                fieldLabel: '所属应用',
                editable: false,
                valueField: 'text',
                displayField: 'text',
                store: Ext.create('kalix.store.BaseStore', {
                    autoLoad: true,
                    proxyUrl: CONFIG.restRoot + '/camel/rest/system/applications'
                }),
                bind: {
                    value: '{rec.app}'
                }
            }, {
                fieldLabel: '状态机关键字',
                bind: {
                    value: '{rec.key}'
                }
            }, {
                fieldLabel: '状态机描述',
                bind: {
                    value: '{rec.description}'
                }
            }, {
                xtype: 'textarea',
                fieldLabel: '配置文件',
                height: 600,
                bind: {
                    value: '{rec.config}'
                }
            }, {
                xtype: 'textarea',
                fieldLabel: '备注',
                //allowBlank: false,
                bind: {
                    value: '{rec.remark}'
                }
            }
        ]
    }]
});