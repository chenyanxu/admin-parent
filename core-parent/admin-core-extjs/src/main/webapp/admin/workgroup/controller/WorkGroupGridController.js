/**
 * 工作组表格控制器
 *
 * @author majian <br/>
 *         date:2015-7-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.workgroup.controller.WorkGroupGridController', {
    extend: 'kalix.controller.BaseGridController',
    requires:['kalix.admin.user.view.UserAddItemSelector','kalix.admin.role.view.RoleAddItemSelector'],
    alias: 'controller.workgroupGridController',

    /**
     * 保存添加信息
     */
    onSaveAddUser: function (workGroupUserUrl, userAddForm, rec) {
        if (userAddForm != null && userAddForm.isValid()) {
            var userIds = userAddForm.down('#userAddItemSelector').getValue();
            var groupId = rec.data.id;
            Ext.Ajax.request({
                url: workGroupUserUrl,
                paramsAsJson: true,
                params: {
                    'groupId': groupId,
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
     * 保存添加信息
     */
    onSaveAddRole: function (workGroupRoleUrl, roleAddForm, rec) {
        if (roleAddForm != null && roleAddForm.isValid()) {
            var roleIds = roleAddForm.down('#roleAddItemSelector').getValue();
            var groupId = rec.data.id;
            Ext.Ajax.request({
                url: workGroupRoleUrl,
                paramsAsJson: true,
                params: {
                    'groupId': groupId,
                    'roleIds': roleIds.join(',')
                },
                method: 'GET',
                callback: function (options, success, response) {
                    var resp = Ext.JSON.decode(response.responseText);
                    if (resp != null && resp.success) {
                        kalix.core.Notify.success(resp.msg, CONFIG.ALTER_TITLE_SUCCESS);
                    } else {
                        Ext.Msg.alert(CONFIG.ALTER_TITLE_FAILURE, resp.msg);
                    }
                    roleAddForm.up('window').close();
                }
            });
        }
    },
    /**
     * 添加用户.
     */
    onAddUser: function (grid, rowIndex, colIndex) {
        var rec = grid.getStore().getAt(rowIndex);
        if (rec == null) {
            Ext.MessageBox.alert(CONFIG.ALTER_TITLE_INFO, '请选择要添加用户的工作组!');
            return;
        }

        var win = Ext.create('Ext.Window', {
            width: 710,
            height: 470,
            border: false,
            modal: true,
            //resizable:false,
            iconCls:'iconfont icon-add-user-column',
            title: '添加用户',
            items: [
                {
                    xtype: 'displayfield',
                    //labelAlign: 'right',
                    fieldLabel: '&nbsp;&nbsp;&nbsp;&nbsp;工作组',
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
        var workGroupUserUrl = this.getView().lookupViewModel().get('url') + '/workGroupUsers';
        var me = this;
        //获得已选用户
        Ext.Ajax.request({
            url: workGroupUserUrl + '/users/' + rec.data.id,
            method: 'GET',
            callback: function (options, success, response) {
                var users = Ext.JSON.decode(response.responseText);
                var dataSotre = Ext.create('kalix.admin.user.store.UserItemSelectorStore');
                var addUserForm = Ext.create('Ext.form.Panel', {
                    width: 700,
                    itemId: 'addUserForm',
                    bodyPadding: 10,
                    //height: 400,
                    layout: 'fit',
                    buttonAlign: 'center',
                    items: [
                        {
                            itemId: 'userAddItemSelector',
                            xtype: 'userAddItemSelector',
                            value: users,
                            store: dataSotre,
                            height:300
                        }
                    ],
                    buttons: [
                        {
                            text: '保存',
                            iconCls:'iconfont icon-save iconfont-btn-small',
                            handler: function () {
                                me.onSaveAddUser(workGroupUserUrl, this.up('#addUserForm'), rec);
                            }
                        },
                        {
                            text: '重置',
                            iconCls:'iconfont icon-reset iconfont-btn-small',
                            handler: function () {
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
    /**
     * 添加角色.
     */
    onAddRole: function (grid, rowIndex, colIndex) {
        var rec = grid.getStore().getAt(rowIndex);
        if (rec == null) {
            Ext.MessageBox.alert(CONFIG.ALTER_TITLE_INFO, '请选择要添加角色的工作组!');
            return;
        }

        var win = Ext.create('Ext.Window', {
            width: 710,
            height: 470,
            border: false,
            modal: true,
            //resizable:false,
            iconCls:'iconfont icon-role-management-column',
            title: '添加角色',
            items: [
                {
                    xtype: 'displayfield',
                    labelAlign: 'right',
                    fieldLabel: '&nbsp;&nbsp;&nbsp;&nbsp;工作组',
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
        var workGroupRoleUrl = this.getView().lookupViewModel().get('url') + '/workGroupRoles';
        var me = this;
        //获得已选角色
        Ext.Ajax.request({
            url: workGroupRoleUrl + '/roles/' + rec.data.id,
            method: 'GET',
            callback: function (options, success, response) {
                var roles = Ext.JSON.decode(response.responseText);
                var dataSotre = Ext.create('kalix.admin.role.store.RoleItemSelectorStore');
                var addRoleForm = Ext.create('Ext.form.Panel', {
                    width: 700,
                    itemId: 'addRoleForm',
                    bodyPadding: 10,
                    //height: 400,
                    layout: 'fit',
                    buttonAlign: 'center',
                    items: [
                        {
                            itemId: 'roleAddItemSelector',
                            xtype: 'roleAddItemSelector',
                            value: roles,
                            store: dataSotre,
                            height: 300
                        }
                    ],
                    buttons: [
                        {
                            text: '保存', iconCls:'iconfont icon-save iconfont-btn-small', handler: function () {
                            me.onSaveAddRole(workGroupRoleUrl, this.up('#addRoleForm'), rec);
                        }
                        },
                        {
                            text: '重置', iconCls:'iconfont icon-reset iconfont-btn-small', handler: function () {
                            var field = this.up('#addRoleForm').down('#roleAddItemSelector');
                            if (!field.disabled) {
                                field.clearValue();
                            }
                        }
                        }
                    ]
                });
                win.add(addRoleForm);
                loadMask.hide();
            }
        });
    }

});