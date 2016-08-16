/**
 * 机构数据仓库
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.store.UserOrgStore', {
    extend: 'Ext.data.TreeStore',
    alias: 'store.userorgstore',
    xtype: 'userorgstore',
    storeId: 'userorgstore',
    autoLoad:true,
    proxy:{
        type:'ajax',
        url: CONFIG.restRoot + '/camel/rest/users/'+ Ext.util.Cookies.get('currentUserId') +'/orgs/tree'
    }
});