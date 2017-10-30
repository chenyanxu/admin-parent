/**
 * 角色查询表单
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.dataauth.view.DataauthSearchForm', {
    extend: 'kalix.view.components.common.BaseSearchForm',
    requires: [
        'kalix.admin.adminDict.component.AdminDictCombobox'
    ],
    alias: 'widget.dataauthSearchForm',
    xtype: 'dataauthSearchForm',
    items: [
        {
            fieldLabel: '数据权限名称',
            xtype: 'adminDictCombobox',
            dictType: '数据权限',
            allowBlank: false,
            name: 'type'
        }]
});
