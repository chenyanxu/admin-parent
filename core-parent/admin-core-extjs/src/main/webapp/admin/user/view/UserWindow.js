/**
 * 用户添加表单
 *
 * @author majian <br/>
 *         date:2015-6-18
 * @version 1.0.0
 */

Ext.define('kalix.admin.user.view.UserWindow', {
  extend: 'kalix.view.components.common.BaseWindow',
  requires: [
    'kalix.admin.user.controller.UserWindowController',
    'kalix.admin.adminDict.component.AdminDictCombobox'
  ],
  alias: 'widget.userWindow',
  controller: {
    type: 'userWindowController'
  },
  xtype: 'userWindow',
  width: 400,
  items: [{
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
        },
        listeners: {
          blur: 'blur'
        }
      }, {
        fieldLabel: '姓名',
        bind: {
          value: '{rec.name}'
        }
      }, {
        xtype: 'combobox',
        fieldLabel: '性别',
        editable: false,
        queryMode: 'local',
        displayField: 'name',
        valueField: 'value',
        store: {
          data: [
            {name: '男', value: '男'},
            {name: '女', value: '女'}
          ]
        },
        bind: {
          value: '{rec.sex}'
        }
      }, {
        inputType: 'password',
        fieldLabel: '密码',
        beforeLabelTextTpl: '<span class="field-required" data-qtip="必填选项">*</span>',
        name: 'password',
        bind: {
          value: '{rec.password}'
        },
        listeners: {
          change: 'change'
        }
      }, {
        inputType: 'password',
        fieldLabel: '确认密码',
        beforeLabelTextTpl: '<span class="field-required" data-qtip="必填选项">*</span>',
        name: 'confirmPassword',
        bind: {
          value: '{rec.confirmPassword}'
        },
        listeners: {
          change: 'change'
        }
      }, {
        fieldLabel: '邮箱',
        bind: {
          value: '{rec.email}'
        }
      }, {
        fieldLabel: '岗位名称',
        xtype: 'adminDictCombobox',
        dictType: '岗位名称',
        bind: {
          value: '{rec.position}'
        },
        render: null
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
        editable: false,
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