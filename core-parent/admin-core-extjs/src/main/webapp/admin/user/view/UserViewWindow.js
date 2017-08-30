/**
 * 用户添加表单
 *
 * @author majian <br/>
 *         date:2015-6-18
 * @version 1.0.0
 */

Ext.define('kalix.admin.user.view.UserViewWindow', {
  extend: 'kalix.view.components.common.BaseWindow',
  alias: 'widget.userViewWindow',
  requires: [
    'kalix.admin.adminDict.component.AdminDictCombobox'
  ],
  xtype: 'userViewWindow',
  width: 400,
  items: [{
    defaults: {readOnly: true},
    xtype: 'baseForm',
    items: [
      {
        fieldLabel: '工号',
        bind: {
          value: '{rec.code}'
        }
      },
      {
        fieldLabel: '登录名',
        bind: {
          value: '{rec.loginName}'
        }
      }, {
        fieldLabel: '姓名',
        bind: {
          value: '{rec.name}'
        }
      }, {
        fieldLabel: '性别',
        bind: {
          value: '{rec.sex}'
        }
      }, {
        fieldLabel: '岗位名称',
        xtype: 'adminDictCombobox',
        dictType: '岗位名称',
        bind: {
          value: '{rec.position}'
        }
      }, {
            fieldLabel: '人员类型',
            xtype: 'adminDictCombobox',
            dictType: '人员类型',
            bind: {
                value: '{rec.userType}'
            }
        }
      , {
        fieldLabel: '邮箱',
        bind: {
          value: '{rec.email}'
        }
      }, {
        fieldLabel: '电话号',
        bind: {
          value: '{rec.phone}'
        }
      }, {
        fieldLabel: '手机号',
        bind: {
          value: '{rec.mobile}'
        }
      }, {
        xtype: 'combobox',
        fieldLabel: '状态',
        queryMode: 'local',
        displayField: 'name',
        valueField: 'value',
        store: {
          data: [
            {name: '启用', value: 1},
            {name: '停用', value: 0}
          ]
        },
        bind: {
          value: '{rec.available}'
        }
      }]
  }]

});