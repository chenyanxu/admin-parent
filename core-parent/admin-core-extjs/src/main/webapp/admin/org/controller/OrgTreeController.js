/**
 * 机构表格控制器
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.controller.OrgTreeController', {
    extend: 'kalix.controller.BaseTreeController',
    alias: 'controller.orgTreeController',
    mixins: {
        userRelation: 'kalix.admin.common.relation.UserRelation'
    },
    cfgForm:'kalix.admin.org.view.OrgForm'
});