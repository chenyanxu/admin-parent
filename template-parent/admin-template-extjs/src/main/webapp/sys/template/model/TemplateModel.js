/**
 * 模板模型
 *
 * @author majian
 *         date:2015-7-3
 * @version 1.0.0
 */
Ext.define('kalix.sys.template.model.TemplateModel', {
    extend: 'kalix.model.BaseModel',
    fields: [
        {name: 'name', type: 'string',validators: [{type: 'presence'}]},//模板名称
        {name: 'desc', type: 'string'},//模板描述
        {name: 'content', type: 'string'}//模板内容
    ]
});