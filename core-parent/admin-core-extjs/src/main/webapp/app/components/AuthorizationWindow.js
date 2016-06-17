/**
 * 授权窗口
 * @author majian <br/>
 *         date:2015-8-3
 * @version 1.0.0
 */
Ext.define('kalix.app.components.AuthorizationWindow', {
    extend: 'Ext.Window',
    xtype: 'authorizationWindow',
    //icon: 'admin/resources/images/application_add.png',
    iconCls:'iconfont icon-permission-column',
    width: 680,
    //height: 500,
    border: false,
    modal: true,
    data: {
        roleId: null,
        authorizationUrl: null
    },
    title: "权限分配",
    layout: 'form',
    buttonAlign: 'center',
    items: [{
        itemId: 'authorizationTree',
        xtype: 'treepanel',
        height: 430,
        autoScroll: true,
        border: true,
        rootVisible: false,
        store: null,
        listeners: {
            checkchange: function (node, checked, obj) {
                node.cascadeBy(function (n) {
                    n.set('checked', checked);
                });
                checkParent(node);
                function checkParent(node) {
                    node = node.parentNode;
                    if (!node) return;
                    var checkP = false;
                    node.cascadeBy(function (n) {
                        if (n != node) {
                            if (n.get('checked') == true) {
                                checkP = true;
                            }
                        }
                    });
                    node.set('checked', checkP);
                    checkParent(node);
                }
            }
        }
    }],
    buttons: [
        {
            xtype: 'button',
            text: '保存',
            handler: function () {
                var authorizationUrl = Ext.ComponentQuery.query('authorizationWindow')[0].authorizationUrl;
                var roleId = Ext.ComponentQuery.query('authorizationWindow')[0].roleId;
                if (authorizationUrl == null || authorizationUrl == "") {
                    Ext.MessageBox.alert(CONFIG.ALTER_TITLE_FAILURE, "保存路径不能为空!");
                    return;
                }
                if (roleId == null || roleId == "") {
                    Ext.MessageBox.alert(CONFIG.ALTER_TITLE_FAILURE, "角色编号不能为空!");
                    return;
                }
                var records = Ext.ComponentQuery.query('authorizationWindow')[0].down('#authorizationTree').getChecked();
                var ids = [];
                Ext.Array.each(records, function (rec) {
                    if (rec.get("parentId") == "root") {
                        ids.push("app:" + rec.get('id'));
                    } else {
                        ids.push("fun:" + rec.get('id'));
                    }
                });
                Ext.Ajax.request({
                    url: authorizationUrl,
                    paramsAsJson: true,
                    params: {
                        "roleId": roleId,
                        "authorizationIds": ids.join(',')
                    },
                    method: "GET",
                    callback: function (options, success, response) {
                        var resp = Ext.JSON.decode(response.responseText);
                        if (resp != null && resp.success) {
                            kalix.core.Notify.success(resp.msg, CONFIG.ALTER_TITLE_SUCCESS);
                        } else {
                            Ext.Msg.alert(CONFIG.ALTER_TITLE_FAILURE, resp.msg);
                        }
                        Ext.ComponentQuery.query('authorizationWindow')[0].close();
                    }
                });
            }
        }, {
            xtype: 'button',
            text: '关闭',
            handler: function () {
                Ext.ComponentQuery.query('authorizationWindow')[0].close();
            }
        }
    ]
});