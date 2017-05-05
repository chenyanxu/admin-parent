/**
 * 模板添加表单
 *
 * @author zangyanming <br/>
 *         date:2017-4-20
 * @version 1.0.0
 */

Ext.define('kalix.sys.template.view.TemplateWindow', {
    extend: 'kalix.view.components.common.BaseWindow',
    requires: [
        'kalix.sys.template.controller.TemplateWindowController'
    ],
    alias: 'widget.templateWindow',
    controller: {
        type: 'templateWindowController'
    },
    xtype: "templateWindow",
    width: 400,
    items: [
        {
            xtype: 'baseForm',
            enableKeyEvents: true,
            items: [
                {
                    fieldLabel: '模板名称',
                    bind: {
                        value: '{rec.name}'
                    }
                },
                {
                    fieldLabel: '模板描述',
                    xtype: 'textarea',
                    bind: {
                        value: '{rec.desc}'
                    }
                },
                {
                    fieldLabel: '模板内容',
                    xtype: 'textarea',
                    id:'TemplateWindowId',
                    bind: {
                        value: '{rec.content}'
                    },
                    enableKeyEvents: true,
                    listeners: {
                        specialkey: 'onPress'
                    }
                }
            ]
        }
    ]
});