package com.kalix.admin.core.biz;

import com.kalix.framework.core.api.biz.IDashboardService;
import com.kalix.framework.core.api.dto.PanelGroupDTO;
import com.kalix.framework.core.api.persistence.JsonData;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称:
 * 类描述:    admin-dashboard服务实现类
 * 创建人:    hqj
 * 创建时间:  2018/6/26
 * 修改人:
 * 修改时间:
 * 修改备注:  [说明本次修改内容]
 */
public class AdminDashboardServiceImpl implements IDashboardService {

    @Override
    public JsonData getPanelGroupData() {
        JsonData jsonData = new JsonData();
        List<PanelGroupDTO> list = new ArrayList<PanelGroupDTO>();
        PanelGroupDTO panelGroupDTO = new PanelGroupDTO();

        panelGroupDTO.setKey("newVisitis");
        panelGroupDTO.setIconName("peoples");
        panelGroupDTO.setIconBackgroundColor("#40c9c6");
        panelGroupDTO.setText("New Visits");
        panelGroupDTO.setEndVal(102400);
        panelGroupDTO.setDuration(2600);
        list.add(panelGroupDTO);

        panelGroupDTO.setKey("messages");
        panelGroupDTO.setIconName("message");
        panelGroupDTO.setIconBackgroundColor("#36a3f7");
        panelGroupDTO.setText("Messages");
        panelGroupDTO.setEndVal(81212);
        panelGroupDTO.setDuration(3000);
        list.add(panelGroupDTO);

        panelGroupDTO.setKey("purchases");
        panelGroupDTO.setIconName("money");
        panelGroupDTO.setIconBackgroundColor("#f4516c");
        panelGroupDTO.setText("Purchases");
        panelGroupDTO.setEndVal(9280);
        panelGroupDTO.setDuration(3200);
        list.add(panelGroupDTO);

        panelGroupDTO.setKey("shoppings");
        panelGroupDTO.setIconName("shoppingCard");
        panelGroupDTO.setIconBackgroundColor("#34bfa3");
        panelGroupDTO.setText("Shoppings");
        panelGroupDTO.setEndVal(13600);
        panelGroupDTO.setDuration(3600);
        list.add(panelGroupDTO);

        jsonData.setTotalCount(4L);
        jsonData.setData(list);
        return jsonData;
    }

    @Override
    public JsonData getLineChartData() {
        return null;
    }
}
