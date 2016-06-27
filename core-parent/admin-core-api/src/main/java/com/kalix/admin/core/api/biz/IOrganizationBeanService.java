package com.kalix.admin.core.api.biz;

import com.kalix.admin.core.dto.model.OrganizationDTO;
import com.kalix.admin.core.entities.OrganizationBean;
import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.api.persistence.JsonData;

import java.util.List;

/**
 * 机构管理服务接口
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
public interface IOrganizationBeanService extends IBizService<OrganizationBean> {

    OrganizationDTO getAllOrg();

    OrganizationDTO getOrg(Long id);

    OrganizationDTO getOrgByName(String name);

    OrganizationDTO getAllByAreaId(Long id);

    void deleteByAreaId(Long id);

    OrganizationDTO getAllByOrgId(Long orgId);

    List getUsersByDepartmentId(long id);

    JsonData getUserAllAndDepartmentUsers(long depId);

    JsonStatus saveDepartmentUsers(long depId, String userId);
}
