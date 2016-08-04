/**
 * 机构数据仓库
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.store.OrgUserStore', {
    extend: 'Ext.data.TreeStore',
    alias: 'store.orgUserStore',
    xtype: 'orgUserStore',
    storeId: 'orgUserStores',
    autoLoad:true,
    proxy:{
        type:'ajax',
        url: CONFIG.restRoot + '/camel/rest/orgs/users'
    }
});