/**
 * 用户新增表单
 *
 * @author majian <br/>
 *         date:2015-6-18
 * @version 1.0.0
 */

Ext.define('kalix.admin.user.view.UserViewWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.admin.user.viewModel.UserViewModel',
        'kalix.admin.user.store.UserStore'
    ],
    alias: 'widget.userViewWindow',
    viewModel: 'userViewModel',
    xtype: "userViewWindow",
    width: 400,

    items : [{
        defaults: {readOnly: true},
        xtype : 'baseForm',
        items : [{
            fieldLabel : '登录名',
            bind : {
                value : '{rec.loginName}'
            }
        }, {
            fieldLabel : '姓名',
            bind : {
                value : '{rec.name}'
            }
        },  {
            fieldLabel : '邮箱',
            bind : {
                activeError : '{validation.email}',
                value : '{rec.email}'
            }
        }, {
            fieldLabel : '电话号',
            bind : {
                value : '{rec.phone}'
            }
        }, {
            fieldLabel : '手机号',
            bind : {
                value : '{rec.mobile}'
            }
        }, {
            xtype : 'combobox',
            fieldLabel : '状态',
            bind : {
                store : '{rec.availableOptions}',
                value : '{rec.available}'
            }
        }]
    }]

});