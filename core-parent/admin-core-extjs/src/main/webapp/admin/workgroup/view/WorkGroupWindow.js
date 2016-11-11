/**
 * 工作组添加和修改表单
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.workgroup.view.WorkGroupWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.controller.BaseWindowController'
    ],
    alias: 'widget.workgroupWindow',
    controller: {
        type: 'baseWindowController'
    },
    xtype: 'workgroupWindow',
    width: 400,
    items: [{
        xtype: 'baseForm',
        items: [{
            fieldLabel: '工作组名称',
            allowBlank: false,
            bind: {
                value: '{rec.name}'
            }
        },
            /*{
                fieldLabel: '所属应用',
                allowBlank: false,
                bind: {
                    value: '{rec.app}'
                }
            }, {*/
            {
                fieldLabel: '所属应用',
                xtype: 'baseComboBox',
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
            },{
                fieldLabel: '备注',
                xtype: 'textarea',
                bind: {
                    value: '{rec.remark}'
                }
            }]
    }]
});