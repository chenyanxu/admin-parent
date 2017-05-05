/**
 * 模板查询表单
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.template.view.TemplateSearchForm', {
    extend: 'kalix.view.components.common.BaseSearchForm',
    alias: 'widget.templateSearchForm',
    xtype: 'templateSearchForm',
    storeId: 'templateStore',
    items: [
        {
            xtype: 'textfield',
            fieldLabel: '模板名称',
            labelAlign: 'right',
            labelWidth: 60,
            width: 200,
            name: '%name%'
        }
        //,
        //{
        //    xtype: 'textfield',
        //    fieldLabel: '模板描述',
        //    labelAlign: 'right',
        //    labelWidth: 60,
        //    width: 200,
        //    name: '%desc%'
        //}
    ]
});
