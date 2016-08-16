/**
 * 组织结构 2016-08-03 by p
 *
 */
Ext.define('kalix.admin.user.model.UserOrgModel', {
    extend: 'kalix.model.BaseModel',
    fields: [
        {name: 'orgId', type: 'int'},
        {name: 'orgCode', type: 'string'},
        {name: 'orgName', type: 'string'}
    ]
});