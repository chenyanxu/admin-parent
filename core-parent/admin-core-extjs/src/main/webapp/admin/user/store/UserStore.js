/**
 * 用户数据仓库
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.user.store.UserStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.admin.user.model.UserModel',
    alias: 'store.userStore',
    xtype: 'userStore',
    storeId: "userStore",
    proxyUrl: CONFIG.restRoot + '/camel/rest/users'
});