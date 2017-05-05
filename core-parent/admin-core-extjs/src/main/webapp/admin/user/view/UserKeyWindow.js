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
        'kalix.admin.user.controller.UserKeyWindowController'
    ],
    alias: 'widget.userWindow',
    controller: {
        type: 'userKeyWindowController'
    },
    xtype: 'userWindow',
    width: 400,
    items: [{
        xtype: 'baseForm',
        items: [{
            inputType: 'password',
            fieldLabel: '密码',
            name: 'password',
            bind: {
                value: '{rec.password}'
            },
            listeners:{
                change:'change'
            }
        }, {
            inputType: 'password',
            fieldLabel: '确认密码',
            bind: {
                value: '{rec.confirmPassword}'
            },
            beforeLabelTextTpl : '<span class="field-required" data-qtip="必填选项">*</span>',
            listeners:{
                change:'change'
            }
        }]
    }]
});