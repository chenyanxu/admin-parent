/**
 * 职位模型
 *
 * @author
 * @version 1.0.0
 */


Ext.define('kalix.app.duty.model.DutyModel', {
    extend: 'kalix.model.BaseModel',

    //todo 在此修改模型定义
    fields: [{
        name: 'name',
        type: 'string'
    },{
        name: 'department',
        type: 'string'
    }, {
        name: 'comment',
        type: 'string'
    }
    ],
    //todo 在此修改模型验证提示信息
    validators: {

        department: [{
            type: 'presence',
            message: '所在部门不能为空!'
        }
        ],
        name: [{
            type: 'presence',
            message: '职位名称不能为空!'
        }]
    }
});
