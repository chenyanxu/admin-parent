/**
 * 机构列表
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.org.view.OrgTreeList', {
    extend: 'kalix.view.components.common.BaseTreeList',
    alias: 'widget.orgTreeList',
    xtype: 'orgTreeList',
    store:  Ext.create('kalix.admin.org.store.OrgStore'),
    title: '机构列表',
    iconCls: 'iconfont icon-organization-management'
});