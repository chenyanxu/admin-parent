/**
 * 审计表格控制器
 *
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.sys.templateconfig.controller.TemplateConfigGridController', {
    extend: 'kalix.controller.BaseGridController',
    alias: 'controller.templateconfigGridController',
    onAdd: function (target) {
        if(!this.getView().store.proxy.extraParams.jsonStr
            ||!Ext.JSON.decode(this.getView().store.proxy.extraParams.jsonStr)
            ||!Ext.JSON.decode(this.getView().store.proxy.extraParams.jsonStr).templateId) {
            alert('请选择左侧模板');
            return;
        }

        var view = Ext.create(this.cfgForm);
        var vm = view.lookupViewModel();

        vm.set('rec', Ext.create(this.cfgModel));
        vm.set('iconCls', vm.get('addIconCls'));
        vm.set('title', vm.get('addTitle'));
        vm.set('store',this.getView().store);

        var templateId = Ext.JSON.decode(this.getView().store.proxy.extraParams.jsonStr).templateId;
        vm.get('rec').set('templateId',templateId);
        vm.get('rec').dirty = false;
        vm.get('rec').vm=vm;
        this.viewModelExtraInit(vm);
        view.show();
    },
    viewModelExtraInit:function(vm){
        //If have extra init,overwrite this method

    }
});