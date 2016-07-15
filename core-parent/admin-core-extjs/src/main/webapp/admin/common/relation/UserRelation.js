/**
 * @author chenyanxu
 * mixin to other class
 * the function use to add user ids  to a relation table
 */
Ext.define('kalix.admin.common.relation.UserRelation', {
    requires:['kalix.admin.user.store.UserStore'],
    onAddUser: function (grid, rowIndex) {
        var relationTo=this.type.split('Grid')[0];
        var rec = grid.getStore().getAt(rowIndex);
        var baseUrl = CONFIG.restRoot + '/camel/rest/'+relationTo+'s';
        var me = this;
        var userStore=this.createItemSelectorUserStore(rec.data);
        var win = Ext.create('kalix.view.components.common.BaseItemSelectorWindow',
          {
              title:'添加用户 - '+rec.data.name,
              iconCls:'iconfont icon-add-user-column',
              Config: {
                  baseUrl:baseUrl,
                  recoredId:rec.data.id,
                  fieldLabel: '姓名',
                  store: userStore
              }
          });

        win.show();
    },
    createItemSelectorUserStore:function(){
      return Ext.create('kalix.admin.user.store.UserStore',{pageSize:0});
    }
});