/**
 * 角色查询表单
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.role.view.RoleSearchForm', {
    extend: 'kalix.view.components.common.BaseSearchForm',
    alias: 'widget.roleSearchForm',
    xtype: 'roleSearchForm',
    storeId: 'roleStore',
    items: [
        {
            xtype: 'textfield',
            fieldLabel: '名称',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: 'name'
        }]
});
