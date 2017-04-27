/**
 * 用户模型
 *
 * @author majian
 *         date:2015-7-3
 * @version 1.0.0
 */
Ext.define('kalix.sys.auditconfig.model.AuditConfigModel', {
    extend: 'kalix.model.BaseModel',
    fields: [
        {name: 'clsName', type: 'string', validators: [{type: 'presence'}]},//类名称
        {name: 'appName', type: 'string', validators: [{type: 'presence'}]},//应用名称
        {name: 'funName', type: 'string', validators: [{type: 'presence'}]},//功能名称
        {name: 'enable', type: 'boolean', defaultValue: true, validators: [{type: 'presence'}]},//是否监控
        {name: 'remark', type: 'string'}//备注
    ]
});