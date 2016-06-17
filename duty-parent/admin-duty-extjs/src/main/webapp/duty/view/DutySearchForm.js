/**
 * 职位查询表单
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.app.duty.view.DutySearchForm', {
    extend: 'kalix.view.components.common.BaseSearchForm',
    alias: 'widget.dutySearchForm',
    xtype: 'dutySearchForm',
    storeId: 'dutyStore',
    items: [
        {
            xtype: 'textfield',
            fieldLabel: '职位名称',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: 'name'
        }]
});
