/**
 * 角色表格控制器
 *
 * @author majian <br/>
 *         date:2015-7-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.role.controller.RoleGridController', {
    extend: 'kalix.controller.BaseGridController',
    requires: [
        'kalix.view.components.common.BaseItemSelectorWindow'
    ],
    alias: 'controller.roleGridController',
    mixins: {
        userRelation: 'kalix.admin.common.relation.UserRelation'
    },
    /**
     * 授权
     * @param grid
     * @param rowIndex
     * @param colIndex
     */
    onAuthorization: function (grid, rowIndex, colIndex) {
        var authorizationWindow = Ext.create('kalix.admin.common.components.AuthorizationWindow');
        var rec = grid.getStore().getAt(rowIndex);
        authorizationWindow.roleId = rec.data.id;
        authorizationWindow.authorizationUrl = CONFIG.restRoot + '/camel/rest/roles/' + rec.data.id + '/authorizations';
        authorizationWindow.show();
        var store = authorizationWindow.down('#authorizationTree').getStore();
        store.setProxy({
            type: 'ajax',
            url: CONFIG.restRoot + '/camel/rest/roles/' + rec.data.id + '/authorizations'
        });

        store.reload();
    }

});


