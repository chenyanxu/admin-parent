/**
 * 角色多选组件数据仓库
 *
 * @author majian <br/>
 *         date:2015-7-3
 * @version 1.0.0
 */
Ext.define('kalix.admin.role.store.RoleItemSelectorStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.admin.role.model.RoleModel',
    alias: 'store.roleItemSelectorStore',
    xtype: 'roleItemSelectorStore',
    autoLoad: true,
    fields: ['id', 'name'],
    proxy: {
        type: 'ajax',
        limitParam: null,
        url: CONFIG.restRoot + '/camel/rest/roles/all',
        reader: {
            type: "json",
            root: "data",
            totalProperty: 'totalCount'
        }
    }
});