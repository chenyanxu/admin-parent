/**
 * 应用模型
 *
 * @author majian <br/>
 *         date:2015-7-3
 * @version 1.0.0
 */
Ext.define('kalix.app.application.model.ApplicationModel', {
    extend: 'kalix.model.BaseModel',
    fields: [
        {name: 'name', type: 'string'},
        {name: 'remark', type: 'string'},
        {name: 'code', type: 'string'},
        //{name: 'location', type: 'string'},
        {name: 'status', defaultValue: false}
    ]
});