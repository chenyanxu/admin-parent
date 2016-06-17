/**
 * 部门数据仓库
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.depNoArea.store.DepNoAreaStore', {
    extend: 'Ext.data.TreeStore',
    alias: 'store.depNoAreaStore',
    xtype: 'depNoAreaStore',
    storeId: 'depNoAreaStore',
    autoLoad: true,
    proxy:{
        type:'ajax',
        url: CONFIG.restRoot + '/camel/rest/org/-10/depts'
    }
});