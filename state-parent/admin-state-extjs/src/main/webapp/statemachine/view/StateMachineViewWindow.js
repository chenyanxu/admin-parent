/**
 * 状态机查看表单
 *
 * @author hqj <br/>
 *         date:2017-4-26
 * @version 1.0.0
 */

Ext.define('kalix.admin.statemachine.view.StateMachineViewWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    alias: 'widget.stateMachineViewWindow',
    xtype: "stateMachineViewWindow",
    width: 400,
    items: [{
        defaults: {readOnly: true},
        xtype: 'baseForm',
        items: [
            {
                fieldLabel: '所属应用',
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
                bind: {
                    value: '{rec.config}'
                }
            }, {
                xtype: 'textarea',
                fieldLabel: '备注',
                bind: {
                    value: '{rec.remark}'
                }
            }
        ]
    }]
});