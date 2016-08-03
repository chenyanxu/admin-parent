package com.kalix.admin.core.biz;

import com.kalix.admin.core.api.biz.IOrganizationBeanService;
import com.kalix.admin.core.api.dao.IOrganizationBeanDao;
import com.kalix.admin.core.api.dao.IOrganizationUserBeanDao;
import com.kalix.admin.core.api.dao.IUserBeanDao;
import com.kalix.admin.core.dto.model.OrganizationDTO;
import com.kalix.admin.core.entities.OrganizationBean;
import com.kalix.admin.core.entities.OrganizationUserBean;
import com.kalix.admin.core.util.Compare;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.JsonStatus;
import com.kalix.framework.core.api.security.IShiroService;
import com.kalix.framework.core.impl.biz.GenericBizServiceImpl;
import com.kalix.framework.core.util.Assert;
import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

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
public class OrganizationBeanServiceImpl extends GenericBizServiceImpl<IOrganizationBeanDao, OrganizationBean> implements IOrganizationBeanService {
    private static final String FUNCTION_NAME = "机构";
    private IOrganizationUserBeanDao organizationUserDao;
    private IUserBeanDao userDao;
    private IShiroService shiroService;

    public OrganizationBeanServiceImpl() {
        super.init(OrganizationBean.class.getName());
    }

    public void setOrganizationUserDao(IOrganizationUserBeanDao organizationUserDao) {
        this.organizationUserDao = organizationUserDao;
    }

    public void setUserDao(IUserBeanDao userDao) {
        this.userDao = userDao;
    }

    public void setShiroService(IShiroService shiroService) {
        this.shiroService = shiroService;
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
    public void afterSaveEntity(OrganizationBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");

        if (entity.getParentId() != -1) {
            OrganizationBean parentOrganizationBean = dao.get(entity.getParentId());
            if (parentOrganizationBean != null && parentOrganizationBean.getIsLeaf() == 1) {
                parentOrganizationBean.setIsLeaf(0);
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
        beans = dao.findByCode(entity.getId(), entity.getCode());
        if (beans != null && beans.size() > 0) {
            status.setFailure(true);
            status.setMsg(FUNCTION_NAME + "代码已经存在！");
            return false;
        }
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
        beans = dao.findByCode(0L, entity.getCode());
        if (beans != null && beans.size() > 0) {
            status.setSuccess(false);
            status.setMsg(FUNCTION_NAME + "代码已经存在！");
            return false;
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
    public JsonStatus deleteEntity(long id) {
        JsonStatus jsonStatus = new JsonStatus();
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
        return jsonStatus;
    }

    /**
     * 如果父节点下再没有子节点,将更新父节点状态
     *
     * @param parentId
     */
    public void updateParent(Long parentId) {
        // 获取父节点
        OrganizationBean parentBean = dao.get(parentId);
        if (parentBean != null) {
            // 获取父节点下的所有子节点
            List<OrganizationBean> children = dao.findByParentId(parentId);
            if (children == null || children.isEmpty()) {
                parentBean.setIsLeaf(1);
                dao.save(parentBean);
            }
        }
    }

    public void removeChildren(Long id) {
        List<OrganizationBean> children = dao.findByParentId(id);
        if (children != null && !children.isEmpty()) {
            children.stream()
                    .forEach(n -> {
                        removeChildren(n.getId());
                        dao.remove(n.getId());
                    });
        }
    }

    @Override
    public void beforeUpdateEntity(OrganizationBean entity, JsonStatus status) {
        super.beforeUpdateEntity(entity, status);
    }

    @Override
    public JsonStatus updateEntity(OrganizationBean entity) {
        JsonStatus jsonStatus = new JsonStatus();
        try {
            if (isUpdate(entity, jsonStatus)) {
                OrganizationBean oldOrg = dao.get(entity.getId());
                oldOrg.setName(entity.getName());
                oldOrg.setCode(entity.getCode());
                oldOrg.setCenterCode(entity.getCenterCode());
                oldOrg.setUpdateBy(shiroService.getCurrentUserLoginName());
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
    private void getChilden(OrganizationDTO root, List<OrganizationBean> elements, Mapper mapper) {
        List<OrganizationDTO> children = new ArrayList<>();

        elements.stream().filter(n -> root.getId() != -1 && (root.getId() == n.getParentId()))
                .forEach(n -> {
                    OrganizationDTO organizationDTO = mapper.map(n, OrganizationDTO.class);
                    organizationDTO.setLeaf(n.getIsLeaf() != 0);
                    organizationDTO.setParentName(root.getName());
                    organizationDTO.setText(n.getName());
                    children.add(organizationDTO);
                    if (n.getIsLeaf() == 0) {
                        getChilden(organizationDTO, elements, mapper);
                    }
                });
        root.setChildren(children);
    }

//    public OrganizationDTO getOrganizationDTO(Long id) {
//        OrganizationBean bean = dao.get(id);
//
//        if (bean != null) {
//            return generateRoot(dao.findByCode(bean.getCode()), id);
//        } else {
//            return null;
//        }
//    }
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
    public OrganizationDTO getAllOrg() {
        List<OrganizationBean> orgs = dao.getAll().stream()
                .sorted(Compare.<OrganizationBean>compare()
                        .thenComparing((a, b) -> a.getCode().compareTo(b.getCode())))
                .collect(Collectors.toList());

        return generateRoot(orgs, -1L);
    }

    @Override
    public List getUserIdsByOrganizationId(long id) {
        return organizationUserDao.findByOrgId(id).stream()
                .filter(n -> n.getUserId() != 0)
                .map(n -> n.getUserId())
                .collect(Collectors.toList());
    }

    @Override
    public JsonData getOrganizationUsers(long orgId) {
        JsonData jsonData = new JsonData();
        List list = userDao.findByUserId(this.getUserIdsByOrganizationId(orgId), true);

        jsonData.setData(list);
        jsonData.setTotalCount((long) list.size());
        return jsonData;
    }

    @Override
    public JsonStatus saveOrganizationUsers(List ids) {
        JsonStatus jsonStatus = new JsonStatus();

        if(ids==null || ids.size()!=2){
            jsonStatus.setFailure(true);
            jsonStatus.setMsg("保存失败!");
        }else{
            long orgId=Long.valueOf((String) ids.get(0));
            String userId=ids.get(1).toString();

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

    /**
     * 获得所有根节点
     *
     * @param elements
     * @return
     */
    private List<OrganizationBean> getRootElements(List<OrganizationBean> elements, Long id) {
        return elements.stream().filter(n -> n.getParentId() == id)
                .collect(Collectors.toList());
    }

    private OrganizationDTO generateRoot(List<OrganizationBean> beans, Long id) {
        OrganizationDTO root;
        Mapper mapper = new DozerBeanMapper();
        String parentName = "根机构";

        root = new OrganizationDTO();

        for (OrganizationBean bean : beans) {
            if (bean.getId() == id) {
                root = mapper.map(bean, OrganizationDTO.class);
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
                    getChilden(organizationDTO, beans, mapper);
                    root.getChildren().add(organizationDTO);
                }
            }
        }

        return root;
    }
}