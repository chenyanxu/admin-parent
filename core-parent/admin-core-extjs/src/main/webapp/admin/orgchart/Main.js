/**
 * 组织机构图展示
 *
 * @author chenyanxu
 * @version 1.0.0
 */
Ext.define('kalix.admin.orgchart.Main', {
  extend: 'Ext.panel.Panel',
  requires: [
    'kalix.orgchart.view.OrgChart'
  ],
  xtype: 'orgchartPanel',
  layout: {
    type: 'fit'
  },
  title:'组织结构树',
  iconCls:'iconfont icon-organization-chart',
  padding:5,
  items: [
    {
      xtype: 'orgChart',
      autoLoad: false,
      listeners: {
        afterrender: function (target) {
          if (!target.autoLoad) {
            Ext.Ajax.request({
              url: CONFIG.restRoot + '/camel/rest/orgs/40810',
              method: 'GET',
              scope: target,
              callback: function (options, success, response) {
                var resp = Ext.JSON.decode(response.responseText);

                this.controller.loadData(resp);
              }
            });
          }
        }
      },
      jsonData: {
        id: "root",
        name: "吉林动画学院",
        data: {},
        children: [
          {
            id: "node-1",
            name: "董事会机构",
            data: {},
            children: [
              {
                id: "node-1-1",
                name: "董事长办公室",
                data: {}
              },
              {
                id: "node-1-2",
                name: "人才培养质量监控办公室",
                data: {}
              },
              {
                id: "node-1-3",
                name: "督导与绩效管理办公室",
                data: {}
              }
            ]
          },
          {
            id: "node-2",
            name: "教学单位",
            data: {},
            children: [
              {
                id: "node-2-1",
                name: "公共基础教学部",
                data: {}
              },
              {
                id: "node-2-2",
                name: "思想政治理论教学部",
                data: {}
              },
              {
                id: "node-2-3",
                name: "外语教学部",
                data: {}
              },
              {
                id: "node-2-4",
                name: "力行职业发展学院",
                data: {}
              },
              {
                id: "node-2-5",
                name: "高等职业学院",
                data: {}
              }
            ]
          },
          {
            id: "node-3",
            name: "科研单位",
            data: {},
            children: [
              {
                id: "node-3-1",
                name: "现代动画技术吉林高等学校工程研究中心",
                data: {}
              },
              {
                id: "node-3-2",
                name: "游戏与互动媒体技术吉林高等学校工程研究中心",
                data: {}
              },
              {
                id: "node-3-3",
                name: "动画研究院",
                data: {}
              },
              {
                id: "node-3-4",
                name: "文化产业研究院",
                data: {}
              }
            ]

          },
          {
            id: "node-4",
            name: "教辅单位",
            data: {},
            children: [
              {
                id: "node-4-1",
                name: "档案馆",
                data: {}
              },
              {
                id: "node-4-2",
                name: "吉林国际动漫博物馆",
                data: {}
              },
              {
                id: "node-4-3",
                name: "图书馆",
                data: {},
                children: [
                  {
                    id: "node-4-3-1",
                    name: "综合业务部",
                    data: {}
                  },
                  {
                    id: "node-4-3-2",
                    name: "技术部",
                    data: {}
                  },
                  {
                    id: "node-4-3-3",
                    name: "采编部",
                    data: {}
                  }
                ]
              }
            ]
          }
        ]
      }
    }
  ]
});