/**
 * 用户模型
 *
 * @author majian <br/>
 *         date:2015-7-3
 * @version 1.0.0
 */


Ext.define('kalix.admin.user.model.UserModel', {
  extend: 'kalix.model.BaseModel',
  fields: [
    {
      name: 'loginName',
      validators: [{type: 'presence'}]
    }, {
      name: 'password',
      validators: [{type: 'presence'}]
    },
    {
      name: 'confirmPassword'
    },
    {
      name: 'name',
      validators: [{type: 'presence'}]
    },
    {
      name: 'position'
    },
    {
      name: 'email',
      validators: [{type: 'presence'}, {type: 'mail'}]
    },
    {
      name: 'phone',
    },
    {
      name: 'mobile',
      type: 'string',
      validators: [{type: 'presence'},{type: 'mobile'}]
    },
    {
      name: 'loginIp',
      type: 'string'
    },
    {
      name: 'is_ent_user',
      type: 'int'
    },
    {
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
    }]
});
