/**
 * 审计查询表单
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.audit.view.AuditSearchForm', {
    extend: 'kalix.view.components.common.BaseSearchForm',
    alias: 'widget.auditSearchForm',
    xtype: 'auditSearchForm',
    storeId: 'auditStore',
    items: [
        {
            xtype: 'textfield',
            fieldLabel: '应用名称',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: 'appName'
        },
        {
            xtype: 'textfield',
            fieldLabel: '功能名称',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: 'funName'
        },{
            xtype: 'textfield',
            fieldLabel: '操作人',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: 'actor'
        }]
});
