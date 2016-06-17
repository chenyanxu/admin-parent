/**
 * 机构数据仓库
 *
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.store.OrgStore', {
    extend: 'Ext.data.TreeStore',
    alias: 'store.orgStore',
    xtype: 'orgStore',
    storeId: 'orgStore'
    //model: 'kalix.admin.org.model.OrgModel'
});