/**
 * 审计首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.templateconfig.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.sys.templateconfig.view.TemplateConfigGrid',
        'kalix.sys.templateconfig.view.TemplateConfigSearchForm'
    ],
    alias: 'widget.templateconfigMain',
    xtype: 'templateconfigMain',
    storeId: 'templateconfigStore',
    items: [
        {
            title: '模板配置查询',
            xtype: 'templateconfigSearchForm'
        },
        {
            xtype: 'templateconfigGridPanel',
            id: 'templateconfigGridPanel',
            title: '模板配置列表'
        }
    ]
});
