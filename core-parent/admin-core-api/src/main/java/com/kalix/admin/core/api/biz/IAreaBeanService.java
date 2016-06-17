package com.kalix.admin.core.api.biz;

import com.kalix.admin.core.dto.model.AreaDTO;
import com.kalix.admin.core.entities.AreaBean;
import com.kalix.framework.core.api.biz.IBizService;

import java.util.List;

/**
 * 项目名称:  urgent-project
 * 类描述:    区域服务接口类
 * 创建人:    sunlf
 * 创建时间:  2014/8/7 16:01
 * 修改人:    sunlf
 * 修改时间:  2014/8/7 16:01
 * 修改备注:  [说明本次修改内容]
 */
public interface IAreaBeanService extends IBizService<AreaBean> {
    List<AreaBean> query(AreaBean areaBean);

    List<AreaBean> getRootBeanList();

    List<AreaBean> getRootBeanListByQhdm();

    List<AreaBean> getChildBeanList(AreaBean node);

    AreaDTO getAllArea();
}
