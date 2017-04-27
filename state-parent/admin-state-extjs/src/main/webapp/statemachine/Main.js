/**
 * 状态机管理首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.statemachine.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.admin.statemachine.view.StateMachineSearchForm',
        'kalix.admin.statemachine.view.StateMachineGrid'
    ],
    items: [
        {
            title: '状态机查询',
            xtype: 'stateMachineSearchForm'
        }, {
            xtype: 'stateMachineGridPanel',
            title: '状态机列表'
        }
    ]
});
