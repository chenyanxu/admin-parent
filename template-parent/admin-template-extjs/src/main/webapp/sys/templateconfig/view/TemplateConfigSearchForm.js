/**
 * 审计查询表单
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.templateconfig.view.TemplateConfigSearchForm', {
    extend: 'kalix.view.components.common.BaseSearchForm',
    alias: 'widget.templateconfigSearchForm',
    xtype: 'templateconfigSearchForm',
    storeId: 'templateconfigStore',
    items: [
        {
            xtype: 'textfield',
            fieldLabel: '模板id',
            itemId: 'templateId',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: 'templateId',
            hidden: true
        },
        {
            xtype: 'textfield',
            fieldLabel: '属性名称',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: '%fieldName%'
        }
    ]
});
