package com.kalix.admin.core.biz;

import com.kalix.framework.core.api.biz.IDashboardService;
import com.kalix.framework.core.api.dto.PieSeriesDataDTO;
import com.kalix.framework.core.impl.biz.DashboardServiceImpl;

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
public class AdminDashboardServiceImpl extends DashboardServiceImpl implements IDashboardService {

    @Override
    public Integer getPanelGroupBizData(String chartKey) {
        return super.getPanelGroupBizData(chartKey);
    }

    @Override
    public List<Integer> getLineChartBizData(String chartKey, String legend) {
        return super.getLineChartBizData(chartKey, legend);
    }

    @Override
    public List<Integer> getRaddarChartBizData(String chartKey, String legend) {
        return super.getRaddarChartBizData(chartKey, legend);
    }

    @Override
    public List<PieSeriesDataDTO> getPieChartBizData(String chartKey) {
        return super.getPieChartBizData(chartKey);
    }

    @Override
    public List<Integer> getBarChartBizData(String chartKey, String legend) {
        return super.getBarChartBizData(chartKey, legend);
    }
}
