/**
 * 部门模型
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.duty.model.DutyModel', {
    extend: 'kalix.model.BaseModel',
    fields: [
        {name: 'name',validators:[{type:'presence'}]},
        {name: 'comment'},
        {name: 'orgid'}
    ]
});