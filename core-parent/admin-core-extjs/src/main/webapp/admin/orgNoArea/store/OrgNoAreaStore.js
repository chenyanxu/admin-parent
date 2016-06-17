/**
 * 机构数据仓库
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.orgNoArea.store.OrgNoAreaStore', {
    extend: 'Ext.data.TreeStore',
    alias: 'store.orgNoAreaStore',
    xtype: 'orgNoAreaStore',
    storeId: 'orgNoAreaStore',
    //model: 'kalix.admin.orgNoArea.model.OrgNoAreaModel',
    autoLoad:true,
    proxy:{
        type:'ajax',
        url: CONFIG.restRoot + '/camel/rest/orgs/'
    }
});