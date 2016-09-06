/**
 * 机构组件
 *
 * @author zangyanming <br/>
 *         date:2016-3-10
 * @version 1.0.0
 */
// Ext.define('kalix.admin.org.Main', {
//     extend: 'Ext.panel.Panel',
//     requires: [
//         'kalix.admin.org.view.OrgTree'
//     ],
//     xtype: 'orgPanel',
//     layout: {
//         type: 'vbox',
//         align: 'stretch'
//     },
//     items: [
//         {
//             xtype: 'orgTree',
//             title: '机构列表',
//             flex: 2
//         }
//     ]
// });
Ext.define('kalix.admin.org.Main', {
  extend: 'kalix.container.BaseTreeContainer',
  requires: [
    'kalix.admin.org.view.OrgTree'
  ],
  tree: {
    xtype: 'orgTree',
    title: '机构列表'
  }
});