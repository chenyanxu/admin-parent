/**
 * 用户新增表单
 *
 * @author majian <br/>
 *         date:2015-6-18
 * @version 1.0.0
 */

Ext.define('kalix.admin.user.view.UserEditWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.admin.user.viewModel.UserViewModel',
        'kalix.controller.BaseWindowController',
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.userWindow',
    viewModel: 'userViewModel',
    controller: {
        type: 'baseWindowController',
        storeId: 'userStore'
    },
    xtype: "userWindow",
    width: 400,
    items: [{
        xtype: 'baseForm',
        items: [{
            fieldLabel: '登录名',
            allowBlank: false,
            bind: {
                activeError: '{validation.loginName}',
                value: '{rec.loginName}'
            }
        }, {
            fieldLabel: '姓名',
            allowBlank: false,
            bind: {
                activeError: '{validation.name}',
                value: '{rec.name}'
            }
        }, {
            fieldLabel: '邮箱',
            allowBlank: false,
            bind: {
                activeError: '{validation.email}',
                value: '{rec.email}'
            }
        }, {
            fieldLabel: '电话号',
            bind: {
                //activeError: '{validation.phone}',
                value: '{rec.phone}'
            }
        }, {
            fieldLabel: '手机号',
            allowBlank: false,
            bind: {
                activeError : '{validation.mobile}',
                value: '{rec.mobile}'
            }
        }, {
            xtype: 'combobox',
            fieldLabel: '状态',
            editable: false,
            bind: {
                store: '{rec.availableOptions}',
                value: '{rec.available}'
            }
        }]
    }]
});