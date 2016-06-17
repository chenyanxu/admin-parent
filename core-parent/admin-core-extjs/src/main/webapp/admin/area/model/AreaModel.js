/**
 * 区域模型
 *
 * @author majian <br/>
 *         date:2015-7-24
 * @version 1.0.0
 */
Ext.define('kalix.admin.area.model.AreaModel', {
    extend: 'Ext.data.TreeModel',
    fields: [
        {name: 'id', type: 'string'},
        {name: 'name', type: 'string'},
        {name: 'text', type: 'string'},
        {name: 'code', type: 'string'},
        {name: 'jd', type: 'string'},
        {name: 'wd', type: 'string'},
        {name: 'centerCode', type: 'string'},
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