/**
 * @author chenyanxu
 */
Ext.define('kalix.admin.duty.controller.DutyGridController', {
  extend: 'kalix.controller.BaseGridController',
  alias: 'controller.dutyGridController',
  mixins: {
    userRelation: 'kalix.admin.common.relation.UserRelation'
  },
  viewModelExtraInit: function (vm) {
    var treeContainer=this.getView().findParentByType('panel').items.getAt(0);
    var treePanel = treeContainer.items.getAt(1);
    var selection = treePanel.selection;

    vm.set('orgName', selection.data.name);

    if (0 == vm.get('rec').get('id')) {
      vm.get('rec').set('orgid', selection.data.id);
      vm.get('rec').dirty = false;
    }
  },
  onAdd: function () {
    var treeContainer=this.getView().findParentByType('panel').items.getAt(0);
    var treePanel = treeContainer.items.getAt(1);
    var selection = treePanel.selection;

    if (selection) {
      this.callParent(arguments);
    }
    else {
      Ext.Msg.alert(CONFIG.ALTER_TITLE_FAILURE, '请选择一个机构!');
    }
  },
  createItemSelectorUserStore: function (data) {
    return Ext.create('kalix.admin.user.store.UserStore', {
        pageSize: 0,
        proxyUrl: CONFIG.restRoot + '/camel/rest/orgs/'+data.orgid+'/users',
        autoLoad:true
      }
    );
  },
  onOrgColumnRender: function() {
    var mainPanel = Ext.app.Application.instance.getApplication()._mainView.controller.getReferences().mainCardPanel.getLayout().getActiveItem();
    var selectTree = mainPanel.controller.getReferences().dutyOrgTreeList.getSelection();
    return selectTree[0].data.name;
  }
});