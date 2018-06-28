package com.kalix.admin.core.biz;

import com.kalix.framework.core.api.biz.IDashboardService;
import com.kalix.framework.core.api.dto.LineChartDTO;
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
        panelGroupDTO.setText("New Visits");
        panelGroupDTO.setEndVal(102400);
        panelGroupDTO.setDuration(2600);

        list.add(panelGroupDTO);

        panelGroupDTO = new PanelGroupDTO();
        panelGroupDTO.setKey("messages");
        panelGroupDTO.setIconName("message");
        panelGroupDTO.setText("Messages");
        panelGroupDTO.setEndVal(81212);
        panelGroupDTO.setDuration(3000);
        list.add(panelGroupDTO);

        panelGroupDTO = new PanelGroupDTO();
        panelGroupDTO.setKey("purchases");
        panelGroupDTO.setIconName("money");
        panelGroupDTO.setText("Purchases");
        panelGroupDTO.setEndVal(9280);
        panelGroupDTO.setDuration(3200);
        list.add(panelGroupDTO);

        panelGroupDTO = new PanelGroupDTO();
        panelGroupDTO.setKey("shoppings");
        panelGroupDTO.setIconName("shoppingCard");
        panelGroupDTO.setText("Shoppings");
        panelGroupDTO.setEndVal(13600);
        panelGroupDTO.setDuration(3600);
        list.add(panelGroupDTO);

        jsonData.setTotalCount(4L);
        jsonData.setData(list);
        return jsonData;
    }

    @Override
    public JsonData getLineChartData(String chartKey) {
        JsonData jsonData = new JsonData();
        List<LineChartDTO> list = new ArrayList<LineChartDTO>();
        List<Integer> expectedList = new ArrayList<Integer>();
        List<Integer> actualList = new ArrayList<Integer>();
        switch (chartKey) {
            case "newVisitis":
                expectedList.add(100);
                expectedList.add(120);
                expectedList.add(161);
                expectedList.add(134);
                expectedList.add(105);
                expectedList.add(160);
                expectedList.add(165);
                actualList.add(120);
                actualList.add(82);
                actualList.add(91);
                actualList.add(154);
                actualList.add(162);
                actualList.add(140);
                actualList.add(145);
                break;
            case "messages":
                expectedList.add(200);
                expectedList.add(192);
                expectedList.add(120);
                expectedList.add(144);
                expectedList.add(160);
                expectedList.add(130);
                expectedList.add(140);
                actualList.add(180);
                actualList.add(160);
                actualList.add(151);
                actualList.add(106);
                actualList.add(145);
                actualList.add(150);
                actualList.add(130);
                break;
            default:
                break;
        }
        LineChartDTO lineChartDTO = new LineChartDTO();
        lineChartDTO.setExpectedData(expectedList);
        lineChartDTO.setActualData(actualList);
        list.add(lineChartDTO);
        jsonData.setData(list);
        return jsonData;
    }
}
