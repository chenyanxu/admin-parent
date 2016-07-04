/**
 * @author chenyanxu
 * mixin to other class
 * the function use to add role ids  to a relation table
 */
Ext.define('kalix.admin.common.relation.RoleRelation', {
    onAddRole: function (grid, rowIndex) {
        var relationTo=this.type.split('Grid')[0];
        var rec = grid.getStore().getAt(rowIndex);
        var baseUrl = CONFIG.restRoot + '/camel/rest/'+relationTo+'s/'+relationTo+'Roles';
        var me = this;
        var win = Ext.create('kalix.view.components.common.BaseItemSelectorWindow',
          {
              title:'添加角色 - '+rec.data.name,
              iconCls:'iconfont icon-role-management-column',
              Config: {
                  selectItemsUrl:baseUrl + '/roles',
                  saveItemsUrl:baseUrl,
                  recoredId:rec.data.id,
                  fieldLabel: '名称',
                  store: Ext.create('kalix.admin.role.store.RoleItemSelectorStore')
              }
          });

        win.show();
    }
});