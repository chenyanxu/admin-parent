/**
 * 授权窗口
 * @author majian <br/>
 *         date:2015-8-3
 * @version 1.0.0
 */
Ext.define('kalix.admin.common.components.AuthorizationViewWindow', {
    extend: 'Ext.Window',
    xtype: 'authorizationviewWindow',
    iconCls:'iconfont icon-permission-column',
    width: 680,
    border: false,
    modal: true,
    data: {
        roleId: null,
        authorizationUrl: null
    },
    title: "权限查看",
    layout: 'form',
    buttonAlign: 'center',
    items: [{
        itemId: 'authorizationviewTree',
        xtype: 'treepanel',
        height: 430,
        autoScroll: true,
        border: true,
        rootVisible: false,
        store: null,
        disableSelection: true,
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
            text: '关闭',
            handler: function () {
                Ext.ComponentQuery.query('authorizationviewWindow')[0].close();
            }
        }
    ]
});