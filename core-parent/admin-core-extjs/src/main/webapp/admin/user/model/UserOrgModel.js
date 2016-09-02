/**
 * 组织结构 2016-08-03 by p
 *
 */
Ext.define('kalix.admin.user.model.UserOrgModel', {
    extend: 'kalix.model.BaseModel',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'code', type: 'string'},
        {name: 'name', type: 'string'}
    ]
});