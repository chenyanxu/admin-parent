/**
 * 用户模型
 *
 * @author majian <br/>
 *         date:2015-7-3
 * @version 1.0.0
 */


Ext.define('kalix.admin.user.model.UserModel', {
    extend: 'kalix.model.BaseModel',

    fields: [{
        name: 'loginName',
        type: 'string'
    }, {
        name: 'password',
        type: 'string'
    }, {
        name: 'confirmPassword',
        type: 'string'
    }, {
        name: 'name',
        type: 'string'
    }, {
        name: 'email',
        type: 'string'
    }, {
        name: 'phone',
        type: 'string'
    }, {
        name: 'mobile',
        type: 'string'
    }, {
        name: 'loginIp',
        type: 'string'
    }, {
        name: 'is_ent_user',
        type: 'int'
    }, {
        name: 'availableOptions',
        defaultValue: [
            ['1', '启用'],
            ['0', '停用']
        ]
    },
        {
            name: 'available',
            type: 'string',
            defaultValue: '1'
        }, {
            name: 'availableText',
            calculate: function (data) {
                var options = [
                    ['1', '启用'],
                    ['0', '停用']
                ];
                var available = data.available;

                return _.find(options, function (item) {
                    return item[0] === available;
                })[1];
            }
        }],

    validators: {
        loginName: [{
            type: 'presence',
            message: '登录名不能为空!'
        }
        ],
        name: [{
            type: 'presence',
            message: '姓名不能为空!'
        }
        ]/*,
        password: [{
            type: 'presence',
            message: '密码不能为空!'
        }
        ],
        confirmPassword: [{
            type: 'presence',
            message: '确认密码不能为空!'
        }
        ]*/,
        email: [{
            type: 'presence',
            message: '邮箱不能为空!'
        }, {
            type: 'email',
            message: '邮箱格式无效!'
        }
        ],
        /*
        phone: [{
            type: 'format',
            matcher: /^(\d{3}-\d{7,8}|\d{4}-\d{7,8}|\s*)$/,
            message: '电话号码格式不正确!'
        }
        ],*/
         mobile: [{
            type: 'presence',
            message: '手机号码不能为空!'
        }, {
            type: 'format',
            matcher: /^1[3,4,5,7,8]\d{9}$/,
            message: '手机号码格式不正确!'
        }
        ]
    }
});

//custom validation field that depends on another fields
Ext.apply(Ext.form.field.VTypes, {
    password: function(val, field) {
        var parentForm = field.up('form'); // get parent form

        // get the form's values
        var formValues = parentForm.getValues();

        // get the value from the configured 'First Password' field
        var firstPasswordValue = formValues[field.firstPasswordFieldName];

        // return true if they match
        return val === firstPasswordValue;
    },
    passwordText: '两次输入的密码不一致!'
});
