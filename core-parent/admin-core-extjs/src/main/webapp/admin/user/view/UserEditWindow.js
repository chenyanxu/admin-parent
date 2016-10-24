/**
 * 用户添加表单
 *
 * @author majian <br/>
 *         date:2015-6-18
 * @version 1.0.0
 */

Ext.define('kalix.admin.user.view.UserEditWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.controller.BaseWindowController',
        'kalix.admin.adminDict.component.AdminDictCombobox'
    ],
    alias: 'widget.userWindow',
    controller: {
        type: 'baseWindowController',
        storeId: 'userStore'
    },
    xtype: "userWindow",
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
                allowBlank: false,
                bind: {
                    value: '{rec.loginName}'
                }
            }, {
                fieldLabel: '姓名',
                allowBlank: false,
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
                allowBlank: false,
                bind: {
                    value: '{rec.position}'
                }
            }, {
                fieldLabel: '邮箱',
                allowBlank: false,
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
                allowBlank: false,
                bind: {
                    value: '{rec.mobile}'
                }
            }]
    }]
});