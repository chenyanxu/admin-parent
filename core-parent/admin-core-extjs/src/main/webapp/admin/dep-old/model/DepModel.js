/**
 * 部门模型
 *
 * @author majian <br/>
 *         date:2015-7-23
 * @version 1.0.0
 */
Ext.define('kalix.admin.dep.model.DepModel', {
    extend: 'Ext.data.TreeModel',
    fields: [
        {name: 'id', type: 'string'},
        {name: 'name', type: 'string'},
        {name: 'code', type: 'string'},
        {name: 'centerCode', type: 'string'},
        {name: 'leaf', type: 'boolean'},
        {name: 'parentId', type: 'int'},
        {name: 'parentName', type: 'string'},
        {name: 'orgId', type: 'int'},
        {name: 'createBy', type: 'string'},
        {name: 'creationDate', type: 'int'},
        {name: 'updateBy', type: 'string'},
        {name: 'updateDate', type: 'int'}
    ]
});