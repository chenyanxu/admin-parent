/**
 * 机构模型
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.orgNoArea.model.OrgNoAreaModel', {
    extend: 'Ext.data.TreeModel',
    fields: [
        {name: 'id', type: 'string'},
        {name: 'name', type: 'string'},
        {name: 'text', type: 'string'},
        {name: 'code', type: 'string'},
        {name: 'leaf', type: 'boolean'},
        {name: 'parentId', type: 'int'},
        {name: 'parentName', type: 'string'},
        {name: 'createBy', type: 'string'},
        {name: 'creationDate'},
        {name: 'updateBy', type: 'string'},
        {name: 'updateDate', type: 'int'}
    ]
});