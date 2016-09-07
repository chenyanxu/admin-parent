/**
 * 机构表格
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.view.OrgTree', {
  extend: 'kalix.view.components.common.BaseTree',
  requires: [
    'kalix.admin.org.controller.OrgTreeController'
  ],
  alias: 'widget.orgTree',
  xtype: 'orgTree',
  controller: 'orgTreeController',
  store: Ext.create('kalix.admin.org.store.OrgStore'),
  title: '机构列表',
  iconCls: 'iconfont icon-organization-management',
  columns: {
    defaults: {flex: 1},
    items: [{text: '编号', dataIndex: 'id', hidden: true},
      {text: '上级机构', dataIndex: 'parentName', hidden: true},
      {xtype: 'treecolumn', text: '名称', dataIndex: 'name'},
      {text: '机构代码', dataIndex: 'code'},
      {text: '创建人', dataIndex: 'createBy'},
      {
        text: '创建日期', dataIndex: 'creationDate'
      },
      {
        header: '操作',
        xtype: 'securityGridColumnCommon',
        verifyItems: [{
          iconCls: 'iconfont icon-edit-column',
          tooltip: '编辑',
          handler: 'onEdit',
          permission: 'edit'
        }, {
          iconCls: 'iconfont icon-delete',
          tooltip: '删除',
          handler: 'onDelete',
          permission: 'delete'
        }, {
          iconCls: 'iconfont icon-add-user-column',
          tooltip: '添加用户',
          handler: 'onAddUser',
          permission: 'addUser'
        }]
      }
    ]
  },
  tbar: {
    xtype: 'securityToolbar',
    verifyItems: [
      {
        text: '添加',
        iconCls: 'iconfont icon-add',
        permission: 'add',
        handler: 'onAdd'
      }, {
        text: '刷新',
        iconCls: 'iconfont icon-refresh',
        permission:'',
        handler: 'onRefresh'
      }
    ]
  }
});