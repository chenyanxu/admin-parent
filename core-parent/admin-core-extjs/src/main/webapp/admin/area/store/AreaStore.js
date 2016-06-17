/**
 * 区域数据仓库
 *
 * @author majian <br/>
 *         date:2015-7-24
 * @version 1.0.0
 */
Ext.define('kalix.admin.area.store.AreaStore', {
    extend: 'Ext.data.TreeStore',
    alias: 'store.areaStore',
    xtype: 'areaStore',
    storeId: "areaStore",
    autoLoad: true,
    model: 'kalix.admin.area.model.AreaModel',
    
    root: {
        expanded: true,
        children: [
            { text: 'detention', leaf: true },
            { text: 'homework', expanded: true, children: [
                { text: 'book report', leaf: true },
                { text: 'algebra', leaf: true}
            ] },
            { text: 'buy lottery tickets', leaf: true }
        ]
    },
    
    proxy: {
        type: "ajax",
        url: CONFIG.restRoot + '/camel/rest/areas'
    }
});