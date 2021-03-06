package com.kalix.admin.core.biz;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.kalix.admin.core.api.biz.IOrganizationBeanService;
import com.kalix.admin.core.api.biz.IUserBeanService;
import com.kalix.admin.core.api.dao.IOrganizationBeanDao;
import com.kalix.admin.core.api.dao.IOrganizationUserBeanDao;
import com.kalix.admin.core.api.dao.IUserBeanDao;
import com.kalix.admin.core.dto.model.OrganizationDTO;
import com.kalix.admin.core.dto.model.OrganizationUserDTO;
import com.kalix.admin.core.dto.model.SchoolZoneDTO;
import com.kalix.admin.core.entities.OrganizationBean;
import com.kalix.admin.core.entities.OrganizationUserBean;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.admin.core.util.Compare;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.impl.biz.CodeUtil;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.Assert;
import com.kalix.framework.core.util.HttpClientUtil;
import com.kalix.framework.core.util.SerializeUtil;
import com.kalix.middleware.excel.api.model.admin.core.NationalUserDTO;
import com.kalix.middleware.excel.api.model.admin.core.OrgUserDTO;
import org.apache.commons.lang.StringUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 机构管理服务实现
 *
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
public class OrganizationBeanServiceImpl extends ShiroGenericBizServiceImpl<IOrganizationBeanDao, OrganizationBean> implements IOrganizationBeanService {
    private static final String FUNCTION_NAME = "机构";
    private IOrganizationUserBeanDao organizationUserDao;
    private IUserBeanService userService;
    private IUserBeanDao userDao;

    public OrganizationBeanServiceImpl() {
        super.init(OrganizationBean.class.getName());
    }

    public void setOrganizationUserDao(IOrganizationUserBeanDao organizationUserDao) {
        this.organizationUserDao = organizationUserDao;
    }

    public void setUserService(IUserBeanService userService) {
        this.userService = userService;
    }

    public void setUserDao(IUserBeanDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void beforeSaveEntity(OrganizationBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");

        String userName = shiroService.getCurrentUserLoginName();
        if (userName != null) {
            entity.setCreateBy(userName);
            entity.setUpdateBy(userName);
        }
    }

    @Override
    @Transactional
    public void afterSaveEntity(OrganizationBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        if (entity.getParentId() == null) {
            entity.setParentId(-1L);
        }
        if (entity.getParentId() != -1) {
            OrganizationBean parentOrganizationBean = dao.get(entity.getParentId());
            if (parentOrganizationBean != null && parentOrganizationBean.getIsLeaf() == 1) {
                parentOrganizationBean.setIsLeaf(0L);
                dao.save(parentOrganizationBean);
            }
        }
    }

    @Override
    public boolean isUpdate(OrganizationBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");

        // 校验机构名称
        List<OrganizationBean> beans = dao.findByName(entity.getId(), entity.getName());
        if (beans != null && beans.size() > 0) {
            status.setFailure(true);
            status.setMsg(FUNCTION_NAME + "名称已经存在！");
            return false;
        }
        // 校验机构代码
        /*beans = dao.findByCode(entity.getId(), entity.getCode());
        if (beans != null && beans.size() > 0) {
            status.setFailure(true);
            status.setMsg(FUNCTION_NAME + "代码已经存在！");
            return false;
        }*/
        return true;
    }

    @Override
    public boolean isSave(OrganizationBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");

        // 校验机构名称
        List<OrganizationBean> beans = dao.findByName(0L, entity.getName());
        if (beans != null && beans.size() > 0) {
            status.setSuccess(false);
            status.setMsg(FUNCTION_NAME + "名称已经存在！");
            return false;
        }
        // 校验机构代码
        /*beans = dao.findByCode(0L, entity.getCode());
        if (beans != null && beans.size() > 0) {
            status.setSuccess(false);
            status.setMsg(FUNCTION_NAME + "代码已经存在！");
            return false;
        }*/
        String code = CodeUtil.createCode(IOrganizationBeanDao.class, entity.getParentId());
        if (code.isEmpty()) {
            status.setSuccess(false);
            status.setMsg(FUNCTION_NAME + "代码生成错误！");
            return false;
        } else {
            entity.setCode(code);
        }
        return true;
    }

    @Override
    public boolean isDelete(Long entityId, JsonStatus status) {
        if (dao.get(entityId) == null) {
            status.setFailure(true);
            status.setMsg(FUNCTION_NAME + "已经被删除！");
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void doDelete(long id, JsonStatus jsonStatus) {
        try {
            OrganizationBean bean = dao.get(id);
            if (bean != null) {
                // 删除子节点
                removeChildren(id);
                // 删除自己
                dao.remove(id);
                // 更新父节点
                updateParent(bean.getParentId());

                jsonStatus.setSuccess(true);
                jsonStatus.setMsg("删除" + FUNCTION_NAME + "成功！");
            } else {
                jsonStatus.setFailure(true);
                jsonStatus.setMsg(FUNCTION_NAME + "不存在或已被删除！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonStatus.setFailure(true);
            jsonStatus.setMsg(FUNCTION_NAME + "删除失败！");
        }
    }

    /**
     * 如果父节点下再没有子节点,将更新父节点状态
     *
     * @param parentId
     */
    @Transactional
    public void updateParent(Long parentId) {
        if (parentId != -1) {
            // 获取父节点
            OrganizationBean parentBean = dao.get(parentId);
            if (parentBean != null) {
                // 获取父节点下的所有子节点
                List<OrganizationBean> children = dao.findByParentId(parentId);
                if (children == null || children.isEmpty()) {
                    parentBean.setIsLeaf(1L);
                    dao.save(parentBean);
                }
            }
        }
    }

    @Transactional
    public void removeChildren(Long id) {
        List<OrganizationBean> children = dao.findByParentId(id);
        if (children != null && !children.isEmpty()) {
            children.stream()
                    .forEach(n -> {
                        removeChildren(n.getId());
                        this.deleteEntity(n.getId());
                        //dao.remove(n.getId());
                    });
        }
    }

    @Override
    public void beforeUpdateEntity(OrganizationBean entity, JsonStatus status) {
        super.beforeUpdateEntity(entity, status);
    }

    @Override
    @Transactional
    public JsonStatus updateEntity(OrganizationBean entity) {
        JsonStatus jsonStatus = new JsonStatus();
        try {
            if (isUpdate(entity, jsonStatus)) {
                OrganizationBean oldOrg = dao.get(entity.getId());
                oldOrg.setName(entity.getName());
                /*oldOrg.setCode(entity.getCode());*/
                oldOrg.setCenterCode(entity.getCenterCode());
                oldOrg.setUpdateBy(shiroService.getCurrentUserLoginName());
                oldOrg.setSzxqid(entity.getSzxqid());
                dao.save(oldOrg);
                jsonStatus.setSuccess(true);
                jsonStatus.setMsg("更新" + FUNCTION_NAME + "成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonStatus.setFailure(true);
            jsonStatus.setMsg("更新" + FUNCTION_NAME + "失败！");
        }
        return jsonStatus;

    }

    /**
     * 递归函数加载子机构
     *
     * @param root
     * @param elements
     */
    private void getChilden(OrganizationDTO root, List<OrganizationBean> elements, Mapper mapper, boolean isRecursion) {
        List<OrganizationDTO> children = new ArrayList<>();

        elements.stream().filter(n -> !root.getId().equals(-1L) && (root.getId().equals(n.getParentId())))
                .forEach(n -> {
                    OrganizationDTO organizationDTO = mapper.map(n, OrganizationDTO.class);
                    organizationDTO.setLeaf(!n.getIsLeaf().equals(0L));
                    organizationDTO.setParentName(root.getName());
                    organizationDTO.setText(n.getName());
                    organizationDTO.setSzxqid(n.getSzxqid());
                    if (n.getSzxqid() != null) {
                        try {
                            String rtnStr = null;
                            String access_token = this.shiroService.getSession().getAttribute("access_token").toString();
                            String sessionId = this.shiroService.getSession().getId().toString();
                            rtnStr = HttpClientUtil.shiroGet("/schoolZones/" + n.getSzxqid().toString(), sessionId, access_token);
                            if (rtnStr != null) {
                                SchoolZoneDTO schoolZoneDTO = SerializeUtil.unserializeJson(rtnStr, SchoolZoneDTO.class);
                                if (schoolZoneDTO != null) {
                                    organizationDTO.setSzxqname(schoolZoneDTO.getZwmc());
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    children.add(organizationDTO);

                    if (isRecursion && n.getIsLeaf().equals(0L)) {
                        getChilden(organizationDTO, elements, mapper, isRecursion);
                    }
                });
        /*for (OrganizationBean obj: elements) {
            if (obj.getId() != -1 && (root.getId() == obj.getParentId())) {
                OrganizationDTO organizationDTO = mapper.map(obj, OrganizationDTO.class);
                organizationDTO.setLeaf(obj.getIsLeaf() != 0);
                organizationDTO.setParentName(root.getName());
                organizationDTO.setText(obj.getName());
                children.add(organizationDTO);

                if (isRecursion && obj.getIsLeaf() == 0) {
                    getChilden(organizationDTO, elements, mapper, isRecursion);
                }
            }
        }*/

        root.setChildren(children);
    }

    private void getChilden(OrganizationDTO root, List<OrganizationBean> elements, Mapper mapper) {
        List<OrganizationDTO> children = new ArrayList<>();

        elements.stream().filter(n -> root.getId().equals(n.getParentId()))
                .forEach(n -> {
                    OrganizationDTO organizationDTO = mapper.map(n, OrganizationDTO.class);
                    organizationDTO.setLeaf(!n.getIsLeaf().equals(0L));
                    organizationDTO.setParentName(root.getName());
                    organizationDTO.setText(n.getName());
                    organizationDTO.setSzxqid(n.getSzxqid());
                    children.add(organizationDTO);
                });
        root.setChildren(children);
    }

    public OrganizationDTO getOrganizationDTO(Long id) {
        OrganizationBean bean = dao.get(id);

        if (bean != null) {
            return generateRoot(dao.findByCode(bean.getCode()), id);
        } else {
            return null;
        }
    }
//
//    public OrganizationDTO getOrganizationDTOByName(String name) {
//        List<OrganizationBean> beans = dao.findByName(0L, name);
//        if (beans != null && !beans.isEmpty()) {
//            OrganizationBean bean = beans.get(0);
//            return generateRoot(dao.findByCode(bean.getCode()), bean.getId());
//        } else {
//            return null;
//        }
//    }

    /**
     * 查询全部 机构
     * 不在关联到部门 2016-06-29
     *
     * @return
     */
    @Override
    public OrganizationDTO getAllOrg(Boolean isAll) {
        OrganizationDTO rtn = new OrganizationDTO();
        isAll = isAll == null ? true : isAll;
        if (isAll) {
            List<OrganizationBean> orgs = dao.getAll().stream()
                    .sorted(Compare.<OrganizationBean>compare()
                            .thenComparing((a, b) -> a.getCode().compareTo(b.getCode())))
                    .collect(Collectors.toList());
            rtn = generateRoot(orgs, -1L);
        } else {
            Long userId = this.shiroService.getCurrentUserId();
            List<OrganizationDTO> rtns = getOrgsTreeByUserId(userId);
            if (rtns.size() > 1) {
                rtn.setId(-1L);
                rtn.setChildren(rtns);
            } else if (rtns.size() == 1) {
                rtn = rtns.get(0);
            } else {
            }
        }
        return rtn;
    }

    /**
     * 查询指定机构的所有机构用户对应关系 2016-07-01 by p
     * 同时查询指定机构父机构和兄弟机构用户对应关系 2016-09-01 by p
     *
     * @param id
     * @return
     */
    @Override
    public List getUserIdsByOrganizationId(long id) {
        return organizationUserDao.findByOrgId(id).stream()
                .filter(n -> !n.getUserId().equals(0L))
                .map(OrganizationUserBean::getUserId)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public JsonData getOrganizationUsers(long orgId) {
        JsonData jsonData = new JsonData();
        List list = userDao.findByUserId(organizationUserDao.findParentAndBrotherByOrgId(orgId).stream()
                .filter(n -> !n.getUserId().equals(0L))
                .map(OrganizationUserBean::getUserId)
                .distinct().collect(Collectors.toList()), true);

        jsonData.setData(list);
        jsonData.setTotalCount((long) list.size());
        return jsonData;
    }

    @Override
    @Transactional
    public JsonStatus saveOrganizationUsers(List ids) {
        JsonStatus jsonStatus = new JsonStatus();

        if (ids == null || ids.size() != 2) {
            jsonStatus.setFailure(true);
            jsonStatus.setMsg("保存失败!");
        } else {
            long orgId = Long.valueOf((String) ids.get(0));
            String userId = ids.get(1).toString();

            try {
                String userName = shiroService.getCurrentUserLoginName();
                // 删除原有机构人员对应关系
                organizationUserDao.deleteByOrganizationId(orgId);

                // 重新添加机构人员对应关系
                if (StringUtils.isNotEmpty(userId)) {
                    Arrays.asList(userId.split(",")).stream()
                            .filter(n -> StringUtils.isNotEmpty(n.trim()))
                            .forEach(n -> organizationUserDao.save(new OrganizationUserBean(Long.parseLong(n), orgId, userName, userName)));
                }

                jsonStatus.setSuccess(true);
                jsonStatus.setMsg("保存成功!");
            } catch (Exception e) {
                e.printStackTrace();
                jsonStatus.setFailure(true);
                jsonStatus.setMsg("保存失败!");
            }
        }

        return jsonStatus;
    }

    @Override
    public JsonStatus importOrganizationUsers(OrgUserDTO userDto) {
        String orgCode = userDto.getOrgCode();
        String loginName = userDto.getLoginName();
        if (orgCode == null || orgCode.isEmpty() || loginName == null || loginName.isEmpty()) {
            return JsonStatus.failureResult("导入的信息为空");
        }

        List<OrganizationBean> orgBeanList = dao.findByNativeSql("select * from sys_organization where code = '"+orgCode+"'", OrganizationBean.class);
        if (orgBeanList == null || orgBeanList.isEmpty()) {
            return JsonStatus.failureResult("导入的机构编码不存在");
        }

        UserBean userBean = userService.getUserBeanByLoginName(loginName);
        if (userBean == null) {
            return JsonStatus.failureResult("导入的用户不存在");
        }

        Long orgId = orgBeanList.get(0).getId();
        Long userId = userBean.getId();
        String sql = "select * from sys_organization_user where orgid = " + orgId + " and userid = " + userId;
        List<OrganizationUserBean> orgUserList = organizationUserDao.findByNativeSql(sql, OrganizationUserBean.class);
        if (orgUserList != null && !orgUserList.isEmpty()) {
            return JsonStatus.failureResult("导入的用户已存在");
        }
        try {
            String userName = shiroService.getCurrentUserLoginName();
            organizationUserDao.save(new OrganizationUserBean(userId, orgId, userName, userName));
        } catch(Exception e) {
            e.printStackTrace();
        }

        return JsonStatus.successResult("导入成功");
    }

    /**
     * 获得所有根节点
     *
     * @param elements
     * @return
     */
    private List<OrganizationBean> getRootElements(List<OrganizationBean> elements, Long id) {
        return elements.stream().filter(n -> n.getParentId().equals(id))
                .collect(Collectors.toList());
    }

    private OrganizationDTO generateRoot(List<OrganizationBean> beans, Long id) {
        OrganizationDTO root;
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        String parentName = "根机构";

        root = new OrganizationDTO();

        for (OrganizationBean bean : beans) {
            if (bean.getId() == id) {
                root = mapper.map(bean, OrganizationDTO.class);
                root.setText(bean.getName());
                parentName = bean.getName();
                break;
            }
        }

        root.setId(id);

        if (beans != null && beans.size() > 0) {
            List<OrganizationBean> rootElements = getRootElements(beans, id);

            if (rootElements != null && rootElements.size() > 0) {
                for (OrganizationBean rootElement : rootElements) {
                    OrganizationDTO organizationDTO = mapper.map(rootElement, OrganizationDTO.class);
                    organizationDTO.setLeaf(rootElement.getIsLeaf() != 0);
                    organizationDTO.setParentName(parentName);
                    organizationDTO.setText(rootElement.getName());
                    organizationDTO.setSzxqid(rootElement.getSzxqid());
                    if (rootElement.getSzxqid() != null) {
                        try {
                            String rtnStr = null;
                            String access_token = this.shiroService.getSession().getAttribute("access_token").toString();
                            String sessionId = this.shiroService.getSession().getId().toString();
                            rtnStr = HttpClientUtil.shiroGet("/schoolZones/" + rootElement.getSzxqid().toString(), sessionId, access_token);
                            if (rtnStr != null) {
                                SchoolZoneDTO schoolZoneDTO = SerializeUtil.unserializeJson(rtnStr, SchoolZoneDTO.class);
                                if (schoolZoneDTO != null) {
                                    organizationDTO.setSzxqname(schoolZoneDTO.getZwmc());
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    getChilden(organizationDTO, beans, mapper, true);
                    root.getChildren().add(organizationDTO);
                }
            }
        }

        return root;
    }

    /**
     * 根据UserId，在用户与部门的关联表中查询部门信息。
     *
     * @param userId
     * @return
     */
    public JsonData getOrgsByUserId(long userId) {

        List list = dao.findByNativeSql("select id, code, name from " + dao.getTableName() + " where id in (select orgid from " + organizationUserDao.getTableName() + " where userid = " + userId + ")", OrganizationDTO.class);

        return JsonData.toJsonData(list);
    }

    /**
     * 查询指定用户id所属机构ids
     *
     * @param userId          指定用户id
     * @param includeChildOrg 是否包含子机构, true包含/false不包含
     * @return
     */
    @Override
    public List<Long> getOrgsByUserId(Long userId, Boolean includeChildOrg) {
        //JsonData jsonData = null;
        List<Long> rtnList = new ArrayList<Long>();
        List<OrganizationDTO> orgList = new ArrayList<>();
        if (includeChildOrg) {
            // 用户拥有的机构列表
            /*List<OrganizationBean> list = this.dao.findById(organizationUserDao.findByUserId(userId)
                    .stream().map(OrganizationUserBean::getOrgId).collect(Collectors.toList()));*/
            List<OrganizationDTO> list = dao.findByNativeSql("select id, code, name from " + dao.getTableName() +
                            " where id in (select orgid from " + organizationUserDao.getTableName() + " where userid = " + userId + ")",
                    OrganizationDTO.class);
            // 查找最顶层机构code长度
            int minLength = 100;
            for (OrganizationDTO bean : list) {
                if (bean.getCode().length() < minLength) {
                    minLength = bean.getCode().length();
                }
            }
            // 拼接sql
            String sql = "";
            for (OrganizationDTO bean : list) {
                if (bean.getCode().length() == minLength) {
                    if (sql.isEmpty()) {
                        sql = "org.code like '" + bean.getCode() + "%'";
                    } else {
                        sql += " or org.code like '" + bean.getCode() + "%'";
                    }
                }
            }
            if (!sql.isEmpty()) {
                orgList = dao.findByNativeSql("select id, code, name from " + dao.getTableName() + " as org where (" + sql + ")",
                        OrganizationDTO.class);
            }
        } else {
            JsonData jsonData = getOrgsByUserId(userId);
            orgList = jsonData.getData();
        }
        //jsonData = JsonData.toJsonData(orgList);
        for (OrganizationDTO obj : orgList) {
            rtnList.add(obj.getId());
        }
        return rtnList;
    }

    /**
     * 根据用户id获取指定用户的机构列表
     *
     * @param userId
     * @return
     */
    public List<OrganizationDTO> getOrgsTreeByUserId(long userId) {
        List<OrganizationDTO> orgsTree = new ArrayList<>();

        // 用户拥有的机构用户列表
        List<OrganizationUserDTO> list = dao.findByNativeSql("select a.id, a.userid, (select name from sys_user b where b.id = a.userid) as username, a.orgid as departmentid,(select name from sys_organization c where c.id = a.orgid) as departmentname from sys_organization_user a where a.userid=" + userId, OrganizationUserDTO.class);

        // 全部组织机构列表
        List<OrganizationBean> orgs = dao.getAll();

        // 用户拥有的机构列表，并过滤不存在的和重复的机构
        List<OrganizationBean> self = orgs.stream()
                .filter(n -> list.stream().map(m -> m.getDepartmentId()).collect(Collectors.toList()).contains(n.getId()))
                .distinct()
                .collect(Collectors.toList());

        self.stream()
                .forEach(n -> {
                    // 处理用户拥有的机构存在隶属关系
                    if (self.stream().filter(m -> !m.getCode().equals(n.getCode()) && n.getCode().indexOf(m.getCode()) == 0).count() == 0) {
                        orgsTree.add(generateRoot(orgs, n.getId()));
                    }
                });

        return orgsTree;
    }

    /**
     * 根据用户名获取指定用户的兄弟机构列表
     *
     * @param name
     * @return
     */
    public List<Long> getOrgsBrotherByUserName(String name) {
        List<Long> list = new ArrayList<>();
        JsonData jsonData = new JsonData();
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        UserBean userBean = userService.getUserBeanByLoginName(name);
        // 用户实体不能为空
        if (userBean != null) {
            List<OrganizationBean> org = dao.getAll();
            organizationUserDao.findByUserId(userBean.getId()).stream()
                    .forEach(n -> {
                        OrganizationBean bean = dao.get(n.getOrgId());
                        // 用户所属机构不能为空
                        if (bean != null) {
                            OrganizationDTO dto = new OrganizationDTO();
                            dto.setId(bean.getParentId());
                            getChilden(dto, org, mapper);
                            dto.getChildren().stream().forEach(m -> list.add(m.getId()));
                        }
                    });
        }

        // 过滤重复兄弟机构
        List<Long> returnList = list.stream().distinct().collect(Collectors.toList());

        jsonData.setData(returnList);
        jsonData.setTotalCount((long) returnList.size());
        return returnList;
    }

    @Override
    public String getParentOrgIdPath(Long id) {
        return getParentOrgIds(id, "");
    }

    private String getParentOrgIds(Long id, String childIds) {
        OrganizationBean org = this.getEntity(id);
        Long parentId = org.getParentId();
        if (childIds.equals("")) {
            childIds = id.toString();
        } else {
            childIds = id.toString() + "," + childIds;
        }
        if (parentId != -1L) {
            childIds = getParentOrgIds(parentId, childIds);
        }
        return childIds;
    }
}