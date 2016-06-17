/**
 * 应用表格
 * @author majian <br/>
 *         date:2015-7-3
 * @version 1.0.0
 */
Ext.define('kalix.app.application.view.ApplicationGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.app.application.controller.ApplicationGridController',
        'kalix.app.application.store.ApplicationStore'
    ],
    alias: 'widget.applicationGrid',
    xtype: 'applicationGridPanel',
    controller: {
        type: 'applicationGridController',
        storeId: 'applicationStore',
        cfgForm: 'kalix.app.application.view.ApplicationWindow',
        cfgViewForm: 'kalix.app.application.view.ApplicationViewWindow',
        cfgModel: 'kalix.app.application.model.ApplicationModel'
    },
    store: {
        type: 'applicationStore'
    },
    columns: {
        defaults: {flex: 1, renderer: 'addTooltip'},
        items: [
            {
                xtype: "rownumberer",
                text: "行号",
                width: 50,
                flex: 0,
                align: 'center',
                renderer: this.update
            },
            {text: '编号', dataIndex: 'id', hidden: true},
            {text: '名称', dataIndex: 'name'},
            {text: '应用代码', dataIndex: 'code'},
            {text: '创建人', dataIndex: 'createBy'},
            {
                text: '创建日期', dataIndex: 'creationDate'
            },
            {
                xtype: 'securityGridColumnCommon',
                items: [
                    {
                        getClass: function (v, meta, record) {
                            if (record.data.status) {
                                return "iconfont icon-stop";
                            }
                            return "iconfont icon-start";
                        },
                        getTip: function (value, metadata, record, row, col, store) {
                            if (record.data.status) {
                                return "停止";
                            }
                            return '启动';
                        },
                        permission: 'admin:appModule:applicationMenu:control',
                        handler: 'onAppStartStop'
                    },
                    {
                        iconCls: 'iconfont icon-view-column',
                        permission: 'admin:appModule:applicationMenu:view',
                        tooltip: '查看',
                        handler: 'onView'
                    },
                    {
                        iconCls: 'iconfont icon-edit-column',
                        permission: 'admin:appModule:applicationMenu:edit',
                        tooltip: '编辑',
                        handler: 'onEdit'
                    }, {
                        iconCls: 'iconfont icon-delete',
                        permission: 'admin:appModule:applicationMenu:delete',
                        tooltip: '删除',
                        handler: 'onDelete'
                    }
                ]
            }
        ]
    },
    tbar: {
        xtype: 'securityToolbar',
        verifyItems: [
            {
                text: '添加',
                xtype: 'button',
                permission: 'admin:appModule:applicationMenu:add',
                iconCls: 'iconfont icon-add',
                handler: 'onAdd'
            }
        ]
    },
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