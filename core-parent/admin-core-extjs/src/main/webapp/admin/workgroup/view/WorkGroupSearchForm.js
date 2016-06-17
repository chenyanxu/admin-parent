/**
 * 工作组查询表单
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.workgroup.view.WorkGroupSearchForm', {
    extend: 'kalix.view.components.common.BaseSearchForm',
    alias: 'widget.workgroupSearchForm',
    xtype: 'workgroupSearchForm',
    storeId: 'workgroupStore',
    items: [
        {
            xtype: 'textfield',
            fieldLabel: '工作组名称',
            labelAlign: 'right',
            labelWidth: 80,
            width: 200,
            name: 'name'
        }]
});
