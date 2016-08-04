/**
 * 机构数据仓库
 *
 * @author zangyanming <br/>
 *         date:2016-8-4
 * @version 1.0.0
 */
Ext.define('kalix.admin.user.store.UserOrgStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.admin.user.model.UserOrgModel',
    alias: 'store.userOrgStore',
    xtype: 'userOrgStore',
    storeId: 'userOrgStore',
    autoLoad:true,
    proxyUrl: CONFIG.restRoot + '/camel/rest/users/25/orgs'
    //url: CONFIG.restRoot + '/camel/rest/users/'+ Ext.util.Cookies.get('currentUserId') +'/orgs'
});