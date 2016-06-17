/**
 * 应用查询表单
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.app.application.view.ApplicationSearchForm', {
    extend: 'kalix.view.components.common.BaseSearchForm',
    alias: 'widget.applicationSearchForm',
    xtype: 'applicationSearchForm',
    storeId: 'applicationStore',
    items: [
        {
            xtype: 'textfield',
            fieldLabel: '应用名称',
            labelAlign: 'right',
            labelWidth: 80,
            width: 200,
            name: 'name'
        }]
});
