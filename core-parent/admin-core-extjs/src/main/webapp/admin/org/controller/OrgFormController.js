/**
 * 机构表格控制器
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.controller.OrgFormController', {
    extend: 'kalix.controller.BaseTreeFormController',
    alias: 'controller.orgFormController',
    mixins: {
        userRelation: 'kalix.admin.common.relation.UserRelation'
    },
    onSave: function () {
        var validate = true;

        var view = this.getView();
        var vm = this.getViewModel();

        if (vm.get('rec').get('name') == null || vm.get('rec').get('name') == '') {
            validate = false;

            var fieldItem = view.items.getAt(4);
            fieldItem.setActiveError("机构名称不能为空！！！");
        }

        if (vm.get('rec').get('code') == null || vm.get('rec').get('code') == '') {
            validate = false;

            var fieldItem = view.items.getAt(5);
            fieldItem.setActiveError("机构名称不能为空！！！");
        }

        if (validate) {
            this.callParent(arguments);
        }
    }
});