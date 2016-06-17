/**
 * 角色数据仓库
 *
 * @author majian <br/>
 *         date:2015-7-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.role.store.RoleStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.admin.role.model.RoleModel',
    alias: 'store.roleStore',
    xtype: 'roleStore',
    storeId: "roleStore",
    proxyUrl: CONFIG.restRoot + '/camel/rest/roles'
});