/**
 * 数据权限添加和修改表单
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.dataauth.view.DataauthWindow', {
  extend: 'kalix.view.components.common.BaseWindow',
  requires: [
    'kalix.view.components.common.BaseComboBox',
    'kalix.controller.BaseWindowController','kalix.admin.adminDict.component.AdminDictCombobox'
  ],
  alias: 'widget.dataauthWindow',
  controller: {
    type: 'baseWindowController'
  },
  xtype: 'dataauthWindow',
  width: 400,
  items: [{
    xtype: 'baseForm',
    items: [{
        fieldLabel: '数据权限',
        xtype: 'adminDictCombobox',
        dictType: '数据权限',
        allowBlank: false,
        bind: {
            value: '{rec.type}'
        }
    },
      {
        fieldLabel: '所属应用',
        xtype: 'baseComboBox',
        editable: false,
        valueField: 'id',
        displayField: 'text',
        store: Ext.create('kalix.store.BaseStore', {
          autoLoad: true,
          proxyUrl: CONFIG.restRoot + '/camel/rest/system/applications'
        }),
        bind: {
          value: '{rec.appId}'
        }
      },
        {
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