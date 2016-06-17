/**
 * 功能模型
 *
 * @author majian <br/>
 *         date:2015-7-31
 * @version 1.0.0
 */
Ext.define('kalix.app.function.model.FunctionModel', {
    extend: 'Ext.data.TreeModel',
    xtype:'functionModel',
    fields: [
        {name: 'id', type: 'string'},
        {name: 'name', type: 'string'},
        {name: 'text', type: 'string'},
        {name: 'code', type: 'string'},
        {name: 'remark', type: 'string'},
        {name: 'leaf', type: 'boolean'},
        {name: 'parentId', type: 'int'},
        {name: 'parentName', type: 'string'},
        {name: 'areaId', type: 'int'},
        {name: 'createBy', type: 'string'},
        {name: 'creationDate'},
        {name: 'updateBy', type: 'string'},
        {name: 'updateDate', type: 'int'}
    ]
});