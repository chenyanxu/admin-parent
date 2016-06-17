/**
 * 用户表格控制器
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.admin.user.controller.UserGridController', {
    extend: 'kalix.controller.BaseGridController',
    alias: 'controller.userGridController',

    onEdit: function (grid, rowIndex, colIndex) {
        var viewModel = this.getViewModel();
        var selModel = grid.getStore().getData().items[rowIndex];
        var view = Ext.create('kalix.admin.user.view.UserEditWindow');
        var vm = view.lookupViewModel();

        vm.set('rec', selModel);
        vm.set('iconCls', 'iconfont icon-edit');
        vm.set('title', viewModel.get('edit_title'));
        view.show();
        grid.setSelection(selModel);
    },
    //修改密码
    onKey: function (grid, rowIndex, colIndex) {
        var viewModel = this.getViewModel();
        var selModel = grid.getStore().getData().items[rowIndex];

        var view = Ext.create('kalix.admin.user.view.UserKeyWindow');
        var vm = view.lookupViewModel();
        vm.set('rec', selModel);
        vm.set('icon', viewModel.get('key_image_path'));
        vm.set('title', viewModel.get('key_title'));
        view.show();
        grid.setSelection(selModel);
    }
});
