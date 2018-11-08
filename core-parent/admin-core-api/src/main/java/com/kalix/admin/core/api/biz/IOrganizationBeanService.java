package com.kalix.admin.core.api.biz;

import com.kalix.admin.core.dto.model.OrganizationDTO;
import com.kalix.admin.core.entities.OrganizationBean;
import com.kalix.framework.core.api.biz.IBizService;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;

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

    OrganizationDTO getAllOrg(Boolean isAll);

    OrganizationDTO getOrganizationDTO(String id);

    //OrganizationDTO getOrganizationDTOByName(String name);

    List getUserIdsByOrganizationId(String orgId);

    JsonData getOrganizationUsers(String orgId);

    /**
     *
     * @param ids
     * ids is a list has tow elements
     * ids[0] the orgId
     * ids[1] the user ids join with ','
     * @return
     */
    JsonStatus saveOrganizationUsers(List ids);

    /**
     * 根据UserId，在用户与部门的关联表中查询部门信息。
     *
     * @param userId
     * @return
     */
    JsonData getOrgsByUserId(String userId);

    /**
     * 查询指定用户id所属机构ids
     * @param userId 指定用户id
     * @param includeChildOrg 是否包含子机构, true包含/false不包含
     * @return
     */
    List<String> getOrgsByUserId(String userId, Boolean includeChildOrg);

    /**
     * 根据用户id获取指定用户的机构列表
     *
     * @param userId
     * @return
     */
    List<OrganizationDTO> getOrgsTreeByUserId(String userId);

    /**
     * 根据用户名获取指定用户的兄弟机构列表
     *
     * @param name
     * @return
     */
    List<String> getOrgsBrotherByUserName(String name);

    /**
     * 根据当前机构id，找到其父机构id路径
     *
     * @param id
     * @return
     */
    String getParentOrgIdPath(String id);
}
