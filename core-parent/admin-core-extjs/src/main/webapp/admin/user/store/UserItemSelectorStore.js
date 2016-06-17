/**
 * 用户多选组件数据仓库
 *
 * @author majian <br/>
 *         date:2015-7-3
 * @version 1.0.0
 */
Ext.define('kalix.admin.user.store.UserItemSelectorStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.admin.user.model.UserModel',
    alias: 'store.userItemSelectorStore',
    xtype: 'userItemSelectorStore',
    pageSize: 0,
    autoLoad: true,
    fields: ['id', 'name'],
    proxy: {
        type: 'ajax',
        limitParam: null,
        url: CONFIG.restRoot + '/camel/rest/users/all',
        reader: {
            type: "json",
            root: "data",
            totalProperty: 'totalCount'
        }
    }
});