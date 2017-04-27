/**
 * 状态机模型
 *
 * @author hqj <br/>
 *         date:2017-4-26
 * @version 1.0.0
 */


Ext.define('kalix.admin.statemachine.model.StateMachineModel', {
    extend: 'kalix.model.BaseModel',
    fields: [
        {
            name: 'app',
            validators: [{type: 'presence'}]
        },
        {
            name: 'key',
            validators: [{type: 'presence'}]
        },
        {
            name: 'description'
        },
        {
            name: 'config'
        },
        {
            name: 'remark'
        }
    ]
});
