/**
 * 应用树数据仓库
 *
 * @author majian <br/>
 *         date:2015-7-3
 * @version 1.0.0
 */
Ext.define('kalix.app.application.store.ApplicationTreeListStore', {
    extend: 'Ext.data.TreeStore',
    alias: 'store.applicationListStore',
    xtype: 'applicationListStore',
    storeId: "applicationListStore",
    model: 'kalix.app.application.model.ApplicationTreeListModel',
    proxy: {
        type: "ajax",
        url: CONFIG.restRoot + '/camel/rest/applications/all/trees'
    }
});