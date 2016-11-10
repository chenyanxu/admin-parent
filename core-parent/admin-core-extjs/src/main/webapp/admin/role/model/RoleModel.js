/**
 * 角色模型
 *
 * @author majian <br/>
 *         date:2015-7-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.role.model.RoleModel', {
    extend: 'kalix.model.BaseModel',
    fields: [
        {name: 'name', type: 'string',validators: [{type: 'presence'}]},
        {name: 'app', type: 'string',validators: [{type: 'presence'}]},
        {name: 'remark', type: 'string'}
    ]
});