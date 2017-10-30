/**
 * 用户组件
 *
 * @author majian <br/>
 *         date:2015-6-18
 * @version 1.0.0
 */
Ext.define('kalix.admin.dataauth.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
         'kalix.admin.dataauth.view.DataauthGrid',
         'kalix.admin.dataauth.view.DataauthSearchForm'
    ],
    items: [
        {
            title: '数据权限查询',
            xtype: 'dataauthSearchForm'
        }, {
            xtype: 'dataauthGridPanel',
            id: 'dataauthGridPanel',
            title: '数据权限列表'
        }
    ]
});
