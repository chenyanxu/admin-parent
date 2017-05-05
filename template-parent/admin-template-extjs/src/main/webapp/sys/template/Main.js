/**
 * 审计首页
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.template.Main', {
    extend: 'kalix.container.BaseContainer',
    requires: [
        'kalix.sys.templateconfig.Main',
        'kalix.sys.template.view.TemplateGrid',
        'kalix.sys.template.view.TemplateSearchForm',
        'kalix.sys.template.controller.TemplateMainController'
    ],
    xtype:'templateMain',
    storeId: 'templateStore',
    controller:{
        type:'templateMainController'
    },
    layout: {
        type: 'hbox',
        align: 'top'
    },
    items: [
        {
            width:'40%',
            xtype:'baseContainer',
            items: [
                {
                    title: '模板查询',
                    xtype: 'templateSearchForm'
                },
                {
                    xtype: 'templateGridPanel',
                    title: '模板列表'
                }
            ]
        },
        {
            width:'60%',
            xtype:'baseContainer',
            items: [
                {
                    title: '模板配置查询',
                    xtype: 'templateconfigSearchForm',
                    reference:'myform',
                    hidden:true
                },
                {
                    xtype: 'templateconfigGridPanel',
                    title: '模板配置列表',
                    reference:'mygrid'
                }
            ]
        }
    ]
});
