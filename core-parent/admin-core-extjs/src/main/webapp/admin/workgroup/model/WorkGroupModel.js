/**
 * 工作组模型
 *
 * @author majian <br/>
 *         date:2015-7-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.workgroup.model.WorkGroupModel', {
    extend: 'kalix.model.BaseModel',
    fields: [
        {name: 'name', type: 'string'},
        {name: 'app', type: 'string'},
        {name: 'remark', type: 'string'}
    ]
});