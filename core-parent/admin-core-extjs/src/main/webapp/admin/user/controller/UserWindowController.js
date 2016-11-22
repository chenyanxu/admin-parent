/**
 * @author chenyanxu
 */
Ext.define('kalix.admin.user.controller.UserWindowController', {
    extend: 'kalix.controller.BaseWindowController',
    alias: 'controller.userWindowController',
    change: function (target, newValue, oldValue, eOpts) {
        var model = this.getView().lookupViewModel();
        var saveBtn = this.getView().dockedItems.getAt(1).items.getAt(0);

        if (target.fieldLabel == '密码') {
            if (newValue.trim() == '' ||
                model.get('rec').get('confirmPassword') == undefined ||
                model.get('rec').get('confirmPassword').trim() == '') {
                saveBtn.disable();
                model.set('passwordRight', false);
            }
            else {
                if (newValue.trim() == model.get('rec').get('confirmPassword').trim()) {
                    saveBtn.enable();
                    model.set('passwordRight', true);
                }
                else {
                    saveBtn.disable();
                    model.set('passwordRight', false);
                }
            }
        }
        else {
            if (newValue.trim() == '' ||
                model.get('rec').get('password') == undefined ||
                model.get('rec').get('password').trim() == '') {
                saveBtn.disable();
                model.set('passwordRight', false);
            }
            else {
                if (newValue.trim() == model.get('rec').get('password').trim()) {
                    saveBtn.enable();
                    model.set('passwordRight', true);
                }
                else {
                    saveBtn.disable();
                    model.set('passwordRight', false);
                }
            }
        }
    },
    onClose: function () {
        var model = this.getView().lookupViewModel();

        if (model.get('passwordRight') == null || model.get('passwordRight')) {
            this.callParent(arguments);
        }
        else {
            Ext.Msg.alert(CONFIG.ALTER_TITLE_FAILURE, '密码不一致');
        }
    },
    onSave: function () {
        var vm = this.getView().lookupViewModel();
        if (vm.get('rec').get('password') == null || vm.get('rec').get('password') == '') {
            //Ext.Msg.alert(CONFIG.ALTER_TITLE_FAILURE, '密码不能为空！！！');
            var formItems = arguments[0].findParentByType('window').items;

            for (var formIndex = 0; formIndex < formItems.length; ++formIndex) {
                var fieldItems = formItems.getAt(formIndex).items;

                for (var fieldIndex = 0; fieldIndex < fieldItems.length; ++fieldIndex) {
                    var fieldItem = fieldItems.getAt(fieldIndex);
                    if (fieldItem.bind != null) {
                        if (fieldItem.name == 'password') {
                            fieldItem.setActiveError('密码不能为空！！！');
                        }

                        if (fieldItem.name == 'confirmPassword') {
                            fieldItem.setActiveError('确认密码不能为空！！！');
                        }
                        //var msg = validation[fieldItem.bind.value.stub.path.split('.')[1]];
                        //
                        //if (msg != undefined) {
                        //    fieldItem.setActiveError(msg);
                        //}
                    }
                }
            }
            this.callParent(arguments);
        }
        else {
            this.callParent(arguments);
        }
    },
    blur: function (target, event, eOpts) {
        var lv = target.lastValue;
        var rest_url = CONFIG.restRoot + '/camel/rest/users/loginname/' + lv;
        Ext.Ajax.request({
            url: rest_url,
            callback: function (options, success, response) {
                var resp = Ext.JSON.decode(response.responseText);
                if (resp) {
                    target.setActiveError('登录名已经存在，请换一个！');
                }
            }
        });
    }
});
