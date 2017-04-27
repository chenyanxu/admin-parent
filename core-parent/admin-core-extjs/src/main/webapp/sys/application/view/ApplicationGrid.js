/**
 * 应用表格
 * @author majian <br/>
 *         date:2015-7-3
 * @version 1.0.0
 */
Ext.define('kalix.admin.application.view.ApplicationGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.admin.application.controller.ApplicationGridController',
        'kalix.admin.application.store.ApplicationStore'
    ],
    alias: 'widget.applicationGrid',
    xtype: 'applicationGridPanel',
    controller: {
        type: 'applicationGridController',
        cfgForm: 'kalix.admin.application.view.ApplicationWindow',
        cfgViewForm: 'kalix.admin.application.view.ApplicationViewWindow',
        cfgModel: 'kalix.admin.application.model.ApplicationModel'
    },
    autoLoad:false,
    store: {
        type: 'applicationStore'
    },
    columns: {
        defaults: {flex: 1, renderer: 'addTooltip'},
        items: [
            {
                xtype: 'rownumberer',
                text: '行号',
                width: 50,
                flex: 0,
                align: 'center',
                renderer: this.update
            },
            {text: '编号', dataIndex: 'id', hidden: true},
            {text: '名称', dataIndex: 'name'},
            {text: '应用代码', dataIndex: 'code'},
            {text: '应用图标', dataIndex: 'iconCls'},
            // {text: '创建人', dataIndex: 'createBy'},
            // {
            //     text: '创建日期', dataIndex: 'creationDate'
            // },
            {
                xtype: 'securityGridColumnCommon',
                verifyItems: [
                    {
                        getClass: function (v, meta, record) {
                            if (record.data.code == 'admin') {
                                return 'kalix_hidden';
                            }
                            else {
                                if (record.data.status) {
                                    return 'iconfont icon-stop';
                                }
                                return 'iconfont icon-start';
                            }
                        },
                        getTip: function (value, metadata, record, row, col, store) {
                            if (record.data.status) {
                                return '停止';
                            }
                            return '启动';
                        },
                        permission: 'control',
                        handler: 'onAppStartStop'
                    },
                    {
                        iconCls: 'iconfont icon-view-column',
                        permission: 'view',
                        tooltip: '查看',
                        handler: 'onView'
                    }
                    // ,
                    // {
                    //     /*iconCls: 'iconfont icon-edit-column',
                    //     permission: 'edit',
                    //     tooltip: '编辑',
                    //      handler: 'onEdit'*/
                    //     getClass: function (v, meta, record) {
                    //         if (record.data.code == 'admin') {
                    //             return "kalix_hidden";
                    //         }
                    //         else {
                    //             return "iconfont icon-edit-column";
                    //         }
                    //     },
                    //     permission: 'edit',
                    //     tooltip: '编辑',
                    //     handler: 'onEdit'
                    // }, {
                    //     /*iconCls: 'iconfont icon-delete',
                    //     permission: 'delete',
                    //     tooltip: '删除',
                    //      handler: 'onDelete'*/
                    //     getClass: function (v, meta, record) {
                    //         if (record.data.code == 'admin') {
                    //             return "kalix_hidden";
                    //         }
                    //         else {
                    //             return "iconfont icon-delete";
                    //         }
                    //     },
                    //     permission: 'delete',
                    //     tooltip: '删除',
                    //     handler: 'onDelete'
                    // }
                ]
            }
        ]
    },
    // tbar: {
    //     xtype: 'securityToolbar',
    //     verifyItems: [
    //         {
    //             text: '添加',
    //             xtype: 'button',
    //             permission: 'add',
    //             iconCls: 'iconfont icon-add',
    //             handler: 'onAdd'
    //         }
    //     ]
    // },
    constructor: function () {
        var scope = this;

        this.callParent(arguments);
        this.store.on('load', function (target, records, successful, eOpts) {
            var appIdArray = records.map(function (record) {
                return record.get('code');
            });
            var appIds = appIdArray.join('_');

            Ext.Ajax.request({
                url: CONFIG.restRoot + '/camel/rest/osgi/bundle/status?appIds=' + appIds,
                scope: {view: this, appIdArray: appIdArray, records: records},
                success: function (response, opts) {
                    var obj = Ext.decode(response.responseText);

                    records.forEach(function (record) {
                        if (obj[record.get('code')]) {
                            record.set('status', true);
                        }
                        else {
                            record.set('status', false);
                        }
                        record.dirty = false;
                    });
                },
                failure: function (response, opts) {
                    console.log('server-side failure with status code ' + response.status);
                }
            });
        }, scope);
        this.store.load();
    }
});