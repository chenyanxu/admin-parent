/**
 * 插件表格
 * @author
 * @version 1.0.0
 */
Ext.define('kalix.app.plugin.view.PluginGrid', {
    extend: 'kalix.view.components.common.BaseGrid',
    requires: [
        'kalix.app.plugin.controller.PluginGridController',
        'kalix.app.plugin.store.PluginStore',
    ],
    alias: 'widget.pluginGrid',
    xtype: 'pluginGridPanel',
    controller: {
        type: 'pluginGridController',
        storeId: 'pluginStore',
        cfgForm: 'kalix.app.plugin.view.PluginWindow',
        cfgViewForm: 'kalix.app.plugin.view.PluginViewWindow',
        cfgModel: 'kalix.app.plugin.model.PluginModel'
    },
    store: {
        type: 'pluginStore'
    },

    columns: {
        defaults: {flex: 1, renderer: 'addTooltip'},
        items: [
            {
                xtype: "rownumberer",
                text: "行号",
                width: 50,
                align: 'center',
                flex: 0,
                renderer: this.update
            },
            {
                text: '编号',
                dataIndex: 'id',
                hidden: true,
            }, {
                text: '名称',
                dataIndex: 'name',
            }, {
                text: '代码',
                dataIndex: 'code',
            },
            {
                text: '备注',
                dataIndex: 'remark',
            },
            {
                xtype: 'securityGridColumnCommon',
                items: [
                    {
                        getClass: function (v, meta, record) {
                            if (record.data.status) {
                                return "kalix_stop";
                            }
                            return "kalix_start";
                        },
                        getTip: function (value, metadata, record, row, col, store) {
                            if (record.data.status) {
                                return "停止";
                            }
                            return '启动';
                        },
                        permission: 'admin:appModule:pluginMenu:control',
                        handler: 'onAppStartStop'
                    },
                    {
                        icon: "resources/images/read.png",
                        permission: 'admin:appModule:pluginMenu:view',
                        tooltip: '查看',
                        handler: 'onView'
                    },
                    {
                        icon: "resources/images/edit.png",
                        permission: 'admin:appModule:pluginMenu:edit',
                        tooltip: '编辑',
                        handler: 'onEdit'
                    }, {
                        icon: "resources/images/delete.png",
                        permission: 'admin:appModule:pluginMenu:delete',
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
                permission: 'admin:appModule:pluginMenu:add',
                bind: {icon: '{add_image_path}'},
                handler: 'onAdd'
            }
        ]
    },
    constructor: function () {
        var scope = this;

        this.callParent(arguments);
        this.store.on('load', function (target, records, successful, eOpts) {
            var pluginIdArray = records.map(function (record) {
                return record.get('code');
            });
            var pluginIds = pluginIdArray.join('_');

            Ext.Ajax.request({
                url: CONFIG.restRoot + '/camel/rest/plugins/status?pluginIds=' + pluginIds,
                scope: {view: this, appIdArray: pluginIdArray, records: records},
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
