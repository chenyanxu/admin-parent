/**
 * 角色表格控制器
 *
 * @author majian <br/>
 *         date:2015-7-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.dataauth.controller.DataauthGridController', {
    extend: 'kalix.controller.BaseGridController',
    requires: [
        'kalix.view.components.common.BaseItemSelectorWindow'
    ],
    alias: 'controller.dataauthGridController',
    mixins: {
        userRelation: 'kalix.admin.common.relation.UserRelation'
    }
});


