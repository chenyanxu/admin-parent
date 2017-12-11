/**
 * 角色模型
 *
 * @author majian <br/>
 *         date:2015-7-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.dataauth.model.DataauthModel', {
    extend: 'kalix.model.BaseModel',
    fields: [
        {name: 'appName', type: 'string'},
        {name: 'type', type: 'string',validators: [{type: 'presence'}]},
        {name: 'remark', type: 'string'},
        {name: 'name', type: 'string'},
        {name: 'menuName', type: 'string'},
        {name: 'appId', type: 'string',validators: [{type: 'presence'}]}
    ]
});