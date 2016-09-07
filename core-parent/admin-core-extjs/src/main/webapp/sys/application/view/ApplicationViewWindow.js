/**
 * 应用查看表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.admin.application.view.ApplicationViewWindow', {
  extend: 'kalix.view.components.common.BaseWindow',

  alias: 'widget.applicationViewWindow',
  xtype: "applicationViewWindow",
  width: 400,
  items: [{
    defaults: {readOnly: true},
    xtype: 'baseForm',
    items: [{
      fieldLabel: '名称',
      bind: {
        value: '{rec.name}'
      }
    },
      {
        fieldLabel: '应用代码',
        bind: {
          value: '{rec.code}'
        }
      },{
        fieldLabel: '应用图标',
        bind: {
          value: '{rec.iconCls}'
        }
      }, {
        fieldLabel: '备注',
        xtype: 'textarea',
        bind: {
          value: '{rec.remark}'
        }
      }]
  }]
});