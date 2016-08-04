/**
 * 组织结构 2016-08-03 by p
 *
 */
Ext.define('kalix.admin.user.model.UserOrgModel', {
    extend: 'kalix.model.BaseModel',
    fields: [
        {name: 'userId', type: 'int'},
        {name: 'userName', type: 'string'},
        {name: 'departmentId', type: 'int'},
        {name: 'departmentName', type: 'string'}
    ]
});