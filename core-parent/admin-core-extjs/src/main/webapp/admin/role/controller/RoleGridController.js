/**
 * 角色表格控制器
 *
 * @author majian <br/>
 *         date:2015-7-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.role.controller.RoleGridController', {
    extend: 'kalix.controller.BaseGridController',
    requires: 'kalix.admin.user.view.UserAddItemSelector',
    alias: 'controller.roleGridController',

    onAddUser: function (grid, rowIndex, colIndex) {
        var rec = grid.getStore().getAt(rowIndex);
        if (rec == null) {
            Ext.MessageBox.alert(CONFIG.ALTER_TITLE_INFO, '请选择要添加用户的角色!');
            return;
        }

        var win = Ext.create('Ext.Window', {
            width: 710,
            height: 470,
            border: false,
            modal: true,
            //resizable:false,
            //icon: 'admin/resources/images/group_add.png',
            iconCls:'iconfont icon-add-user-column',
            title: '添加用户',
            items: [
                {
                    xtype: 'displayfield',
                    labelAlign: 'right',
                    fieldLabel: '&nbsp;&nbsp;&nbsp;&nbsp;角色',
                    labelWidth: 60,
                    value: rec.data.name
                }]
        });
        win.show();
        var loadMask = new Ext.LoadMask({
            msg: '加载中...',
            target: win
        });
        loadMask.show();
        var roleUserUrl = CONFIG.restRoot + '/camel/rest/roles/roleUsers';
        var me = this;
        //获得已选用户
        Ext.Ajax.request({
            url: roleUserUrl + '/users/' + rec.data.id,
            method: 'GET',
            callback: function (options, success, response) {
                var users = Ext.JSON.decode(response.responseText);
                var dataSotre = Ext.create('kalix.admin.user.store.UserItemSelectorStore');
                var addUserForm = Ext.create('Ext.form.Panel', {
                    width: 700,
                    itemId: 'addUserForm',
                    bodyPadding: 10,
                    layout: 'fit',
                    buttonAlign: 'center',
                    items: [
                        {
                            itemId: 'userAddItemSelector',
                            xtype: 'userAddItemSelector',
                            value: users,
                            store: dataSotre,
                            height: 300
                        }
                    ],
                    buttons: [
                        {
                            text: '保存', iconCls:'iconfont icon-save iconfont-btn-small', handler: function () {
                            me.onSaveAddUser(roleUserUrl, this.up('#addUserForm'), rec);
                        }
                        },
                        {
                            text: '重置', iconCls:'iconfont icon-reset iconfont-btn-small', handler: function () {
                            var field = this.up('#addUserForm').down('#userAddItemSelector');
                            if (!field.disabled) {
                                field.clearValue();
                            }
                        }
                        }
                    ]
                });
                win.add(addUserForm);
                loadMask.hide();
            }
        });
    },
    onSaveAddUser: function (roleUserUrl, userAddForm, rec) {
        if (userAddForm != null && userAddForm.isValid()) {
            var userIds = userAddForm.down('#userAddItemSelector').getValue();
            var roleId = rec.data.id;
            Ext.Ajax.request({
                url: roleUserUrl,
                paramsAsJson: true,
                params: {
                    'roleId': roleId,
                    'userIds': userIds.join(',')
                },
                method: 'GET',
                callback: function (options, success, response) {
                    var resp = Ext.JSON.decode(response.responseText);
                    if (resp != null && resp.success) {
                        kalix.core.Notify.success(resp.msg, CONFIG.ALTER_TITLE_SUCCESS);
                    } else {
                        Ext.Msg.alert(CONFIG.ALTER_TITLE_FAILURE, resp.msg);
                    }
                    userAddForm.up('window').close();
                }
            });
        }
    },
    /**
     * 授权
     * @param grid
     * @param rowIndex
     * @param colIndex
     */
    onAuthorization: function (grid, rowIndex, colIndex) {
        var authorizationWindow = Ext.create('kalix.app.components.AuthorizationWindow');
        var rec = grid.getStore().getAt(rowIndex);
        authorizationWindow.roleId = rec.data.id;
        authorizationWindow.authorizationUrl = this.getView().lookupViewModel().get('authorizationUrl');
        authorizationWindow.show();
        var store = authorizationWindow.down('#authorizationTree').getStore();
        store.setProxy({
            type: 'ajax',
            url: CONFIG.restRoot + '/camel/rest/roles/' + rec.data.id + '/authorizations'
        });
        store.reload();
    }

});


