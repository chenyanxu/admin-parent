/**
 * 重置密码表单
 *
 * @author majian <br/>
 *         date:2015-6-18
 * @version 1.0.0
 */

Ext.define('kalix.admin.user.view.UserKeyWindow', {
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
            inputType: 'password',
            fieldLabel: '密码',
            allowBlank: false,
            name: 'password',
            bind: {
                activeError: '{validation.password}',
                value: '{rec.password}'
            },
            blankText: '密码不能为空!'
        }, {
            inputType: 'password',
            fieldLabel: '确认密码',
            allowBlank: false,
            bind: {
                activeError: '{validation.confirmPassword}',
                value: '{rec.confirmPassword}'
            },
            vtype: 'password',
            firstPasswordFieldName: 'password',
            blankText: '确认密码不能为空!'
        }]
    }]
});