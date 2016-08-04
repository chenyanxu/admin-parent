package com.kalix.admin.core.api.biz;

import com.kalix.admin.core.dto.model.OrganizationDTO;
import com.kalix.admin.core.entities.OrganizationBean;
import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.api.persistence.JsonData;

import java.util.List;

/**
 * 机构管理服务接口
 * @author majian date:2015-7-21
 *
 * 修改 2016-07-01 by p
 * 原为对应部门的方法，修改为对应机构
 * 修改类名及部分方法
 *
 * @version 1.0.0
 */
public interface IOrganizationBeanService extends IBizService<OrganizationBean> {

    OrganizationDTO getAllOrg();

    //OrganizationDTO getOrganizationDTO(Long id);

    //OrganizationDTO getOrganizationDTOByName(String name);

    List getUserIdsByOrganizationId(long orgId);

    JsonData getOrganizationUsers(long orgId);

    /**
     *
     * @param ids
     * ids is a list has tow elements
     * ids[0] the orgId
     * ids[1] the user ids join with ','
     * @return
     */
    JsonStatus saveOrganizationUsers(List ids);

    List<OrganizationDTO> getByUserId();
}
