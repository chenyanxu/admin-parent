/**
 * 区域视图模型
 *
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
Ext.define('kalix.admin.area.viewModel.AreaViewModel', {
    extend: 'Ext.app.ViewModel',
    requires: [
        'kalix.admin.area.store.AreaStore'
    ],
    alias: 'viewmodel.areaViewModel',
    data: {
        addTitle: '新增区域',
        editTitle: '编辑区域',
        url: CONFIG.restRoot + '/camel/rest/areas'
    }
});