/**
 * @author chenyanxu
 * mixin to other class
 * the function use to add role ids  to a relation table
 */
Ext.define('kalix.admin.common.relation.RoleRelation', {
  requires:['kalix.admin.role.store.RoleStore'],
  onAddRole: function (grid, rowIndex) {
    //there are two type controller: TreeController (14 char) and GridController (14 char).
    //if we name the biz controller in mod: bizName+TreeController or bizName+GridController.
    //we can get the 'relationTo' var in the next line code:
    var relationTo = this.type.substr(0, this.type.length - 14);
    var rec = grid.getStore().getAt(rowIndex);
    var baseUrl = CONFIG.restRoot + '/camel/rest/'+relationTo+'s';
    var me = this;
    var roleStore=Ext.create('kalix.admin.role.store.RoleStore',{pageSize:0,autoLoad:true});
    var win = Ext.create('kalix.view.components.common.BaseItemSelectorWindow',
      {
        title: '添加角色 - ' + rec.data.name,
        iconCls: 'iconfont icon-role-management-column',
        Config: {
          baseUrl:baseUrl,
          recoredId: rec.data.id,
          fieldLabel: '名称',
          store: roleStore
        }
      });
    win.show();
  }
});