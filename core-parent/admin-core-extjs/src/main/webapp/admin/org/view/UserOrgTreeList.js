/**
 * 机构列表
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.view.UserOrgTreeList', {
    extend: 'kalix.view.components.common.BaseTreeList',
    alias: 'widget.userorgtreelist',
    xtype: 'userorgtreelist',
    store:  Ext.create('kalix.admin.org.store.UserOrgStore'),
    title: '机构列表',
    iconCls: 'iconfont icon-organization-management'
});