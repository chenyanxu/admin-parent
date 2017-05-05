/**
 * 用户模型
 *
 * @author majian
 *         date:2015-7-3
 * @version 1.0.0
 */
Ext.define('kalix.sys.templateconfig.model.TemplateConfigModel', {
    extend: 'kalix.model.BaseModel',
    fields: [
        {name: 'templateId', type: 'int', validators: [{type: 'presence'}]},//模板id
        {name: 'fieldName', type: 'string', validators: [{type: 'presence'}]},//属性名称
        {name: 'fieldDesc', type: 'string'}//属性描述
    ]
});