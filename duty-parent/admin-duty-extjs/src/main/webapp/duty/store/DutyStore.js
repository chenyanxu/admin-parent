/**
 * 职位数据仓库
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.duty.store.DutyStore', {
    extend: 'kalix.store.BaseStore',
    model: 'kalix.admin.duty.model.DutyModel',
    alias: 'store.dutyStore',
    xtype: 'dutyStore',
    storeId: 'dutyStore',
    proxyUrl: CONFIG.restRoot + '/camel/rest/orgs/-1/dutys'
});