/**
 * 部门数据仓库
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.dutyNoArea.store.DutyNoAreaStore', {
    extend: 'Ext.data.TreeStore',
    alias: 'store.dutyNoAreaStore',
    xtype: 'dutyNoAreaStore',
    storeId: 'dutyNoAreaStore',
    autoLoad: true,
    proxy:{
        type:'ajax',
        url: CONFIG.restRoot + '/camel/rest/orgs/'
    }
});