/**
 * Created by hqj on 2017/4/26.
 */

Ext.define('kalix.admin.statemachine.view.StateMachineSearchForm', {
    extend: 'kalix.view.components.common.BaseSearchForm',
    requires: [
        'kalix.view.components.common.BaseComboBox'
    ],
    alias: 'widget.stateMachineSearchForm',
    xtype: 'stateMachineSearchForm',
    items: [
        {
            xtype: 'baseComboBox',
            fieldLabel: '所属应用',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            editable: false,
            valueField: 'text',
            displayField: 'text',
            store: Ext.create('kalix.store.BaseStore', {
                autoLoad: true,
                proxyUrl: CONFIG.restRoot + '/camel/rest/system/applications'
            }),
            name: 'app'
        }, {
            xtype: 'textfield',
            fieldLabel: '关键字',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: '%key%'
        }
    ]
});
