/**
 * 字典模型
 *
 * @author majian <br/>
 *         date:2015-7-9
 * @version 1.0.0
 */
Ext.define('kalix.admin.dict.model.DictModel', {
    extend: 'kalix.model.BaseModel',
    fields: [
        {name: 'dictName', type: 'string'},
        {name: 'label', type: 'string'},
        {name: 'value', type: 'string'},
        {name: 'type', type: 'string'},
        {name: 'description', type: 'string'},
        {name: 'sort', type: 'string'}
    ]
});