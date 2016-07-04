/**
 * 部门模型
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.depNoArea.model.DepNoAreaModel', {
    extend: 'Ext.data.TreeModel',
    fields: [
        {name: 'id', type: 'string'},
        {name: 'name', type: 'string'},
        {name: 'code', type: 'string'},
        {name: 'leaf', type: 'boolean'},
        {name: 'parentId', type: 'int'},
        {name: 'parentName', type: 'string'},
        {name: 'orgId', type: 'int'},
        {name: 'createBy', type: 'string'},
        {name: 'creationDate'},
        {name: 'updateBy', type: 'string'},
        {name: 'updateDate', type: 'int'},
        {name: 'dept', type: 'boolean',defaultValue:true}
    ]
});