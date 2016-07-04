/**
 * @author chenyanxu
 * mixin to other class
 * the function use to add user ids  to a relation table
 */
Ext.define('kalix.admin.common.relation.UserRelation', {
    onAddUser: function (grid, rowIndex) {
        var relationTo=this.type.split('Grid')[0];
        var rec = grid.getStore().getAt(rowIndex);
        var baseUrl = CONFIG.restRoot + '/camel/rest/'+relationTo+'s/'+relationTo+'Users';
        var me = this;
        var win = Ext.create('kalix.view.components.common.BaseItemSelectorWindow',
          {
              title:'添加用户 - '+rec.data.name,
              iconCls:'iconfont icon-add-user-column',
              Config: {
                  selectItemsUrl:baseUrl + '/users',
                  saveItemsUrl:baseUrl,
                  recoredId:rec.data.id,
                  fieldLabel: '姓名',
                  store: Ext.create('kalix.admin.user.store.UserItemSelectorStore')
              }
          });

        win.show();
    }
});