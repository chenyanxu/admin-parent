/**
 * 审计查询表单
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.auditconfig.view.AuditConfigSearchForm', {
    extend: 'kalix.view.components.common.BaseSearchForm',
    alias: 'widget.auditconfigSearchForm',
    xtype: 'auditconfigSearchForm',
    storeId: 'auditconfigStore',
    items: [
        {
            xtype: 'textfield',
            fieldLabel: '类名称',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: 'clsName'
        },
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
        }]
});
