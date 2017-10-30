/**
 * 角色查看表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.admin.dataauth.view.DataauthViewWindow', {
  extend: 'kalix.view.components.common.BaseWindow',
  alias: 'widget.dataauthViewWindow',
  xtype: 'dataauthViewWindow',
  width: 400,
  items: [{
    defaults: {readOnly: true},
    xtype: 'baseForm',
    items: [{
        fieldLabel: '数据权限',
        xtype: 'adminDictCombobox',
        dictType: '数据权限',
        bind: {
            value: '{rec.type}'
        }
    },
      {
        fieldLabel: '所属应用',
        allowBlank: false,
        bind: {
          activeError: '{validation.app}',
          value: '{rec.appName}'
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