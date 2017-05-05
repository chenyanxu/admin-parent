/**
 * 角色表格
 * @author majian <br/>
 *         date:2015-7-10
 * @version 1.0.0
 */
Ext.define('kalix.admin.orgUser.view.OrgUserGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.admin.user.controller.UserGridController',
        'kalix.admin.user.store.UserStore',
        'kalix.admin.adminDict.component.AdminDictGridColumn',
        'kalix.view.components.common.IconColumn'
    ],
    alias: 'widget.orgUserGrid',
    xtype: 'orgUserGridPanel',

    controller: {
        type: 'userGridController',
        cfgModel: 'kalix.admin.user.model.UserModel'
    },
    store: {
        type: 'userStore'
    },
    autoLoad: false,
    viewConfig:{
        //stripeRows: false,//是否隔行换色
        getRowClass : function(record,rowIndex,rowParams,store){
            var type = record.get('available');

            if(type==0){
                return 'x-grid-row-red';
            }
        }
    },
    columns: [
        {
            xtype: 'rownumberer'
        },
        {
            text: '头像',
            xtype: 'iconcolumn',
            dataIndex: 'icon'
        },
        {
            text: '编号',
            dataIndex: 'id',
            hidden: true
        },
        {
            text: '工号',
            dataIndex: 'code'
        },
        {
            text: '登录名',
            dataIndex: 'loginName'
        },
        {
            text: '姓名',
            dataIndex: 'name'
        },
        {
            text: '性别',
            dataIndex: 'sex'
        },
        {
            text: '岗位名称',
            xtype: 'adminDictGridColumn',
            dictType: '岗位名称',
            dataIndex: 'position'
        },
        /*{
         text: '邮箱',
         dataIndex: 'email'
         },*/ /*{
         text: '电话',
         dataIndex: 'phone',
         },
         {
         text: '手机',
         dataIndex: 'mobile'
         },*/
        {
            text: '创建人',
            dataIndex: 'createBy'
        },
        {
            text: '创建日期',
            dataIndex: 'creationDate',
            flex: 2
        },
        {
            text: '机构',
            dataIndex: 'org'
        },
        {
            text: '职务',
            dataIndex: 'duty'
        },
        {
            text: '角色',
            dataIndex: 'role'
        },
        {
            text: '工作组',
            dataIndex: 'workGroup'
        },
        {
            text: '状态',
            dataIndex: 'available',
            renderer: function (value) {
                if (value != null && value == '1') {
                    return '启用';
                }
                return '停用';
            }
        }
    ]
});