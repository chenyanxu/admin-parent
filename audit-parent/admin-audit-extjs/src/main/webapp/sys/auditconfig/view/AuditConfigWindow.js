/**
 * 审计查看表单
 *
 * @author
 * @version 1.0.0
 */

Ext.define('kalix.sys.auditconfig.view.AuditConfigWindow', {
  extend: 'kalix.view.components.common.BaseWindow',
  alias: 'widget.auditconfigWindow',
  xtype: 'auditconfigWindow',
  width: 400,
  items: [{
    defaults: {},
    xtype: 'baseForm',
    items: [
      {
        fieldLabel: '类名称',
        bind: {
          value: '{rec.clsName}'
        }
      },
      {
        fieldLabel: '应用名称',
        bind: {
          value: '{rec.appName}'
        }
      },
      {
        fieldLabel: '功能名称',
        bind: {
          value: '{rec.funName}'
        }
      }, {
        xtype: 'combobox',
        editable: false,
        valueField: 'key',
        displayField: 'name',
        // fieldStyle: 'font-size:15px;text-align:center;background:transparent;',
        store: {
          data: [
            {'name': '是', 'key': true},
            {'name': '否', 'key': false}
          ]
        },
        fieldLabel: '是否监控',
        bind: {
          value: '{rec.enable}'
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