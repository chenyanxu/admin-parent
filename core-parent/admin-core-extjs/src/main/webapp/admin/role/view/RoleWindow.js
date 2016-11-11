/**
 * 角色添加和修改表单
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.role.view.RoleWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.view.components.common.BaseComboBox',
        'kalix.controller.BaseWindowController'
    ],
    alias: 'widget.roleWindow',
    controller: {
        type: 'baseWindowController'
    },
    xtype: "roleWindow",
    width: 400,
    items: [{
        xtype: 'baseForm',
        items: [{
            fieldLabel: '角色名称',
            //allowBlank: false,
            bind: {
               // activeError: '{validation.name}',
                value: '{rec.name}'
            }
        },
        /*{
            fieldLabel: '所属应用',
           // allowBlank: false,
            bind: {
              //  activeError: '{validation.app}',
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
        }, {
            fieldLabel: '备注',
            //allowBlank: false,
            xtype: 'textarea',
            bind: {
                //activeError: '{validation.remark}',
                value: '{rec.remark}'
            }
        }]
    }]
});