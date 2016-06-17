package com.kalix.admin.core.biz;

import com.kalix.admin.core.api.biz.IOrganizationBeanService;
import com.kalix.admin.core.api.dao.IDepartmentUserBeanDao;
import com.kalix.admin.core.api.dao.IOrganizationBeanDao;
import com.kalix.admin.core.dto.model.OrganizationDTO;
import com.kalix.admin.core.entities.DepartmentUserBean;
import com.kalix.admin.core.entities.OrganizationBean;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.framework.core.api.biz.JsonStatus;
import com.kalix.framework.core.api.persistence.JsonData;
import com.kalix.framework.core.api.persistence.PersistentEntity;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.Assert;
import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构管理服务实现
 * @author majian <br/>
 *         date:2015-7-21
 * @version 1.0.0
 */
public class OrganizationBeanServiceImpl extends ShiroGenericBizServiceImpl<IOrganizationBeanDao, OrganizationBean> implements IOrganizationBeanService {
    private static final String FUNCTION_NAME = "机构";
    private IDepartmentUserBeanDao depUserBeanDao;

    public OrganizationBeanServiceImpl() {
        super.init(OrganizationBean.class.getName());
    }

    public void setDepUserBeanDao(IDepartmentUserBeanDao depUserBeanDao) {
        this.depUserBeanDao = depUserBeanDao;
    }

    @Override
    public void afterSaveEntity(OrganizationBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        OrganizationBean bean=(OrganizationBean)entity;
        if(bean.getParentId()!=-1){
            OrganizationBean parentOrganizationBean = dao.getOrg(bean.getParentId());
            if(parentOrganizationBean!=null&&parentOrganizationBean.getIsLeaf()==1){
                parentOrganizationBean.setIsLeaf(0);
                dao.saveOrg(parentOrganizationBean);
            }
        }
    }

    @Override
    public boolean isUpdate(OrganizationBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        OrganizationBean bean=(OrganizationBean)entity;
        List<OrganizationBean> beans = dao.find("select ob from OrganizationBean ob where ob.name = ?1", bean.getName());
        if(beans!=null&&beans.size()>0){
            OrganizationBean _org=beans.get(0);
            if(_org.getId()!=entity.getId()) {
                status.setFailure(true);
                status.setMsg(FUNCTION_NAME + "已经存在！");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSave(OrganizationBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        OrganizationBean bean=(OrganizationBean)entity;
        List<OrganizationBean> beans = dao.find("select ob from OrganizationBean ob where ob.name = ?1 and ob.areaId=?2", bean.getName(), bean.getAreaId());
        if(beans!=null&&beans.size()>0){
            status.setSuccess(false);
            status.setMsg(FUNCTION_NAME + "已经存在！");
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
            if (dao.getOrg(id) == null) {
                jsonStatus.setFailure(true);
                jsonStatus.setMsg(FUNCTION_NAME + "{" + id + "}不存在！");
            } else {
                List<OrganizationBean> organizationBeans = dao.find("select ob from OrganizationBean ob where ob.id = ?1", id);
                if(organizationBeans!=null&&!organizationBeans.isEmpty()) {
                    removeChildren(id);
                    OrganizationBean org=organizationBeans.get(0);
                    dao.removeOrg(id);
                    updateParent(org.getParentId());
                    jsonStatus.setSuccess(true);
                    jsonStatus.setMsg("删除" + FUNCTION_NAME + "成功！");
                }else{
                    jsonStatus.setSuccess(true);
                    jsonStatus.setMsg(FUNCTION_NAME + "已经被删除！");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsonStatus.setFailure(true);
            jsonStatus.setMsg("删除" + FUNCTION_NAME + "失败！");
        }
        return jsonStatus;

    }


    /**
     * 如果父节点下再没有子节点,将更新父节点状态
     * @param parentId
     */
    public void updateParent(Long parentId){
        List<OrganizationBean> organizationBeans = dao.find("select ob from OrganizationBean ob where ob.id = ?1", parentId); //获得父节点
        if(organizationBeans!=null&&organizationBeans.size()>0){
            List<OrganizationBean> children = dao.find("select ob from OrganizationBean ob where ob.parentId = ?1", parentId); //获得父节点
            if(children==null||children.isEmpty()) {
                OrganizationBean parent = organizationBeans.get(0);
                parent.setIsLeaf(1);
                dao.save(parent);
            }
        }
    }

    public void removeChildren(Long id){
        List<OrganizationBean> organizationBeans = dao.find("select ob from OrganizationBean ob where ob.parentId = ?1", id);
        if(organizationBeans!=null&&organizationBeans.size()>0){
            for(OrganizationBean org:organizationBeans){
                if(org.getIsLeaf()==0){ //存在子节点
                    removeChildren(org.getId());
                }
                dao.removeOrg(org.getId());
            }
        }
    }

    @Override
    public void beforeUpdateEntity(OrganizationBean entity, JsonStatus status) {
        super.beforeUpdateEntity(entity, status);
    }

    @Override
    public JsonStatus updateEntity(OrganizationBean entity) {
        OrganizationBean org=(OrganizationBean)entity;
        JsonStatus jsonStatus = new JsonStatus();
        try {
            if(isUpdate(org,jsonStatus)) {
                OrganizationBean oldOrg = dao.getOrg(org.getId());
                oldOrg.setName(org.getName());
                oldOrg.setCode(org.getCode());
                oldOrg.setCenterCode(org.getCenterCode());
                oldOrg.setUpdateBy(getShiroService().getCurrentUserName());
                dao.saveOrg(oldOrg);
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
        List<OrganizationDTO> children = new ArrayList<OrganizationDTO>();

        for (OrganizationBean organizationBean : elements) {
            if (root.getId() != -1 && (root.getId() == organizationBean.getParentId())) {
                OrganizationDTO organizationDTO = mapper.map(organizationBean, OrganizationDTO.class);
                organizationDTO.setLeaf(organizationBean.getIsLeaf() == 0 ? false : true);
                organizationDTO.setParentName(root.getName());
                organizationDTO.setText(organizationBean.getName());
                children.add(organizationDTO);
                if(organizationBean.getIsLeaf()==0) {
                    getChilden(organizationDTO, elements, mapper);
                }
            }
        }
        root.setChildren(children);
    }

    public OrganizationDTO getAllByAreaId(Long id) {
        List<OrganizationBean> beans = dao.find("select ob from OrganizationBean ob where ob.areaId = ?1", id);

        return generateRoot(beans,-1L);
    }

    public OrganizationDTO getOrg(Long id){
        OrganizationBean bean = dao.get(id);

        List<OrganizationBean> beans=null;

        if(bean!=null) {
            beans = dao.find("select ob from OrganizationBean ob where ob.code like ?1", bean.getCode() + "%");
        }

        return generateRoot(beans,id);
    }

    public OrganizationDTO getOrgByName(String name){
        List<OrganizationBean> beans1 = dao.find("select ob from OrganizationBean ob where ob.name = ?1",name);
        List<OrganizationBean> beans2=null;

        if(beans1!=null&&beans1.size()>0) {
            beans2 = dao.find("select ob from OrganizationBean ob where ob.code like ?1", beans1.get(0).getCode() + "%");

            return generateRoot(beans2,beans1.get(0).getId());
        }


        return generateRoot(null,0L);
    }

    public OrganizationDTO getAllOrg() {
        //List<OrganizationBean> orgs = dao.getAll();
        List<OrganizationBean> orgs = dao.find("select ob from OrganizationBean ob where ob.dept=false order by ob.code");

        return generateRoot(orgs,-1L);
    }

    @Override
    public void deleteByAreaId(Long id) {
        try {
            List<OrganizationBean> orgs = dao.find("select ob from OrganizationBean ob where ob.areaId = ?1", id);
            if(orgs!=null&&!orgs.isEmpty()){
                for(OrganizationBean org:orgs){
                    if(org!=null) {
                        dao.removeOrg(org.getId());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得所有根节点
     * @param elements
     * @return
     */
    private List<OrganizationBean> getRootElements(List<OrganizationBean> elements,Long id) {
        List<OrganizationBean> roots=new ArrayList<OrganizationBean>();
        for (OrganizationBean element : elements) {
            if (element.getParentId() == id) {
                roots.add(element);
            }
        }
        return roots;
    }

    private OrganizationDTO generateRoot(List<OrganizationBean> beans,Long id){
        OrganizationDTO root=null;
        Mapper mapper = new DozerBeanMapper();
        String parentName="根机构";

        root = new OrganizationDTO();

        for (OrganizationBean bean : beans) {
            if (bean.getId() == id) {
                root = mapper.map(bean, OrganizationDTO.class);
                parentName = bean.getName();
                break;
            }
        }

        root.setId(id);

        if(beans!=null&&beans.size()>0){
            List<OrganizationBean> rootElements = getRootElements(beans,id);

            if(rootElements!=null&&rootElements.size()>0) {
                for(OrganizationBean rootElement:rootElements){
                    OrganizationDTO organizationDTO = mapper.map(rootElement, OrganizationDTO.class);
                    organizationDTO.setLeaf(rootElement.getIsLeaf() == 0 ? false : true);
                    organizationDTO.setParentName(parentName);
                    organizationDTO.setText(rootElement.getName());
                    getChilden(organizationDTO, beans, mapper);
                    root.getChildren().add(organizationDTO);
                }
            }
        }

        return root;
    }


    // 以下方法提供给部门管理时使用
    @Override
    public OrganizationDTO getAllByOrgId(Long orgId) {
        List<OrganizationBean> beans = dao.find("select ob from OrganizationBean ob where ob.dept=true order by ob.code");
        return generateRoot(beans, orgId);
    }

    @Override
    public List getUsersByDepartmentId(long id) {
        List<String> userIds = new ArrayList<String>();
        List<DepartmentUserBean> departmentUserBeans = depUserBeanDao.find("select ob from DepartmentUserBean ob where ob.depId = ?1", id);
        if (departmentUserBeans != null && !departmentUserBeans.isEmpty()) {
            for (DepartmentUserBean departmentUserBean : departmentUserBeans) {
                if (departmentUserBean != null && departmentUserBean.getUserId() != 0) {
                    userIds.add(String.valueOf(departmentUserBean.getUserId()));
                }
            }
        }
        return userIds;
    }

    @Override
    public JsonData getUserAllAndDepartmentUsers(long depId) {
        JsonData jsonData = new JsonData();
        List<UserBean> users = dao.find("select u from UserBean u where u.id not in (select dub.userId from DepartmentUserBean dub)");
        List<PersistentEntity> persistentEntities = new ArrayList<PersistentEntity>();
        if (users != null && users.size() > 0) {
            for (UserBean user : users) {
                if (user != null) {
                    persistentEntities.add(user);
                }
            }
        }
        List<UserBean> departmentUserBeans = dao.find("select u from UserBean u where u.id in (select du.userId from DepartmentUserBean du where du.depId=?1)", depId);
        if (departmentUserBeans != null && departmentUserBeans.size() > 0) {
            for (UserBean departmentUserBean : departmentUserBeans) {
                if (departmentUserBean != null) {
                    persistentEntities.add(departmentUserBean);
                }
            }
        }
        jsonData.setData(persistentEntities);
        jsonData.setTotalCount((long) persistentEntities.size());
        return jsonData;
    }

    @Override
    public JsonStatus saveDepartmentUsers(long depId, String userId) {
        JsonStatus jsonStatus = new JsonStatus();

        try {
            depUserBeanDao.deleteByDepartmentId(depId);
            String userName = getShiroService().getCurrentUserName();
            if (StringUtils.isNotEmpty(userId)) {
                if (userId.indexOf(",") != -1) {
                    String[] userIds = userId.split(",");
                    for (String _userId : userIds) {
                        if (StringUtils.isNotEmpty(_userId.trim())) {
                            DepartmentUserBean departmentUserBean = new DepartmentUserBean();
                            departmentUserBean.setCreateBy(userName);
                            departmentUserBean.setUpdateBy(userName);
                            departmentUserBean.setDepId(depId);
                            departmentUserBean.setUserId(Long.parseLong(_userId));
                            depUserBeanDao.save(departmentUserBean);
                        }
                    }
                } else {
                    if (StringUtils.isNotEmpty(userId.trim())) {
                        DepartmentUserBean departmentUserBean = new DepartmentUserBean();
                        departmentUserBean.setCreateBy(userName);
                        departmentUserBean.setUpdateBy(userName);
                        departmentUserBean.setDepId(depId);
                        departmentUserBean.setUserId(Long.parseLong(userId));
                        depUserBeanDao.save(departmentUserBean);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonStatus.setFailure(true);
            jsonStatus.setMsg("保存失败!");
            return jsonStatus;
        }
        jsonStatus.setSuccess(true);
        jsonStatus.setMsg("保存成功!");
        return jsonStatus;
    }
}