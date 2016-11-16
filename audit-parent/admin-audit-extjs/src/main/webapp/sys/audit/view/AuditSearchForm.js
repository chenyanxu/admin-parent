/**
 * 审计查询表单
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.audit.view.AuditSearchForm', {
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
            name: '%appName%'
        },
        {
            xtype: 'textfield',
            fieldLabel: '功能名称',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: '%funName%'
        },
        {
            xtype: 'textfield',
            fieldLabel: '操作人',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: '%actor%'
        },
        {
            //查询条件-操作开始时间
            xtype: 'datefield',
            format: 'Y-m-d H:i:s',
            formatText:'格式为YYYY-mm-dd H:i:s',
            fieldLabel: '操作时间:',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            margin: '0 0 0 20',
            name: 'creationDate:begin:gt'
        },
        {
            //标签
            xtype: 'label',
            text: '-',
            margin: '5 5 0 5'
        },
        {
            //查询条件-操作截至时间
            xtype: 'datefield',
            format: 'Y-m-d H:i:s',
            formatText:'格式为YYYY-mm-dd H:i:s',
            headLabel: true,
            labelAlign: 'right',
            width: 140,
            name: 'creationDate:end:lt'
        }
    ]
});
