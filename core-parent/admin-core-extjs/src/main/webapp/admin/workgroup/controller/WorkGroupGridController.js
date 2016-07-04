/**
 * 工作组表格控制器
 *
 * @author majian <br/>
 *         date:2015-7-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.workgroup.controller.WorkGroupGridController', {
  extend: 'kalix.controller.BaseGridController',
  alias: 'controller.workgroupGridController',
  mixins: {
    userRelation: 'kalix.admin.common.relation.UserRelation',
    roleRelation: 'kalix.admin.common.relation.RoleRelation'
  }
});