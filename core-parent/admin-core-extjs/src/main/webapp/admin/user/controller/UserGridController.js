/**
 * 用户表格控制器
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.user.controller.UserGridController', {
    extend: 'kalix.controller.BaseGridController',
    alias: 'controller.userGridController',
    requires: ['kalix.Notify'],
    onEdit: function (grid, rowIndex, colIndex) {
        var oldView=this.cfgForm;
        this.cfgForm='kalix.admin.user.view.UserEditWindow';
        this.callParent(arguments);
        this.cfgForm=oldView;
    },
    //修改密码
    onKey: function (grid, rowIndex, colIndex) {
        var viewModel = this.getViewModel();
        var selModel = grid.getStore().getData().items[rowIndex];

        var view = Ext.create('kalix.admin.user.view.UserKeyWindow');
        var vm = view.lookupViewModel();

        vm.set('rec', selModel);
        vm.set('iconCls', 'iconfont icon-view');
        vm.set('title', '修改密码');
        vm.set('store',this.getView().store);
        view.show();
        grid.setSelection(selModel);
    },
    /**
     * 查看权限
     * @param grid
     * @param rowIndex
     * @param colIndex
     */
    onAuthorization: function (grid, rowIndex, colIndex) {
        var authorizationWindow = Ext.create('kalix.admin.common.components.AuthorizationViewWindow');
        var rec = grid.getStore().getAt(rowIndex);
        authorizationWindow.roleId = rec.data.id;
        authorizationWindow.authorizationUrl = CONFIG.restRoot + '/camel/rest/users/'+ rec.data.id +'/authorizations';
        authorizationWindow.show();

        var tree = authorizationWindow.down('#authorizationviewTree');

        tree.on('checkchange', function(node , checked , eOpts) {
            node.set('checked', true);
        });

        var store = authorizationWindow.down('#authorizationviewTree').getStore();
        store.setProxy({
            type: 'ajax',
            url: CONFIG.restRoot + '/camel/rest/users/'+ rec.data.id +'/authorizations'
        });

        store.reload({
            callback: function (records, options, success) {
                if (records == "") {
                    kalix.Notify.success("当前用户暂无权限信息！！！", "提示信息");
                }
            }
        });
    }
});
