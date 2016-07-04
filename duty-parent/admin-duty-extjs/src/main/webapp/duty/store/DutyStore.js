/**
 * 部门数据仓库
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.duty.store.DutyStore', {
    extend: 'Ext.data.TreeStore',
    alias: 'store.dutyStore',
    xtype: 'dutyStore',
    storeId: 'dutyStore',
    autoLoad: true,
    proxy:{
        type:'ajax',
        url: CONFIG.restRoot + '/camel/rest/orgs/'
    }
});