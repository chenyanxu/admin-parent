/**
 * 部门选择下拉comboBox
 *
 * @author zangyanming <br/>
 *         date:2016-8-4
 * @version 1.0.0
 */
Ext.define('kalix.admin.user.component.UserOrgComboBox', {
  extend: 'Ext.form.field.ComboBox',
  requires: [
    'kalix.admin.user.store.UserOrgStore'
  ],
  alias: 'widget.userOrgComboBox',
  allowBlank: false,
  labelAlign: 'right',
  xtype: 'userOrgComboBox',
  queryMode: 'local',
  valueField: 'id',
  displayField: 'name',
  queryParam: false,
  minChars: 0,
  typeAhead: false,
  editable: false,
  store: {
    type: 'userOrgStore'
  },
  constructor: function () {
    this.callParent(arguments);

    this.store.on('load', function (target, records) {
      if (records.length > 0) {
        if (records.length == 1) {
          this.select(records[0]);
          this.lookupViewModel().get('rec').set('orgName', records[0].get('name'))
        }
      }
      else {
        kalix.Notify.warning(CONFIG.ALTER_TITLE_FAILURE, "未找到该用户的部门，请为该用户选择部门！");
      }
    }, this);
    this.on('change', function (target, newValue, oldValue) {
      if (oldValue || target.store.data.length>1) {
        this.lookupViewModel().get('rec').set('orgName', target.rawValue);
      }
    });
  }

});
