package com.kalix.admin.core.biz;

import com.kalix.admin.core.api.biz.IAreaBeanService;
import com.kalix.admin.core.api.biz.IOrganizationBeanService;
import com.kalix.admin.core.api.dao.IAboutBeanDao;
import com.kalix.admin.core.api.dao.IAreaBeanDao;
import com.kalix.admin.core.api.dao.IDepartmentBeanDao;
import com.kalix.admin.core.api.dao.IDepartmentUserBeanDao;
import com.kalix.admin.core.dto.model.AreaDTO;
import com.kalix.admin.core.entities.AboutBean;
import com.kalix.admin.core.entities.AreaBean;
import com.kalix.framework.core.api.biz.JsonStatus;
import com.kalix.framework.core.impl.biz.ShiroGenericBizServiceImpl;
import com.kalix.framework.core.util.Assert;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称:  urgent-project
 * 类描述:    区域服务实现类
 * 创建人:    sunlf
 * 创建时间:  2014/8/7 16:02
 * 修改人:    sunlf
 * 修改时间:  2014/8/7 16:02
 * 修改备注:  [说明本次修改内容]
 */
public class AreaBeanServiceImpl extends ShiroGenericBizServiceImpl<IAreaBeanDao, AreaBean> implements IAreaBeanService {
    private static final String FUNCTION_NAME = "区域";
    private IOrganizationBeanService orgService;
    private IAboutBeanDao aboutBeanDao;
    private IDepartmentUserBeanDao depUserBeanDao;
    private IDepartmentBeanDao depBeanDao;

    public AreaBeanServiceImpl() {
        super.init(AreaBean.class.getName());
    }

    public void setDepUserBeanDao(IDepartmentUserBeanDao depUserBeanDao) {
        this.depUserBeanDao = depUserBeanDao;
    }

    public void setDepBeanDao(IDepartmentBeanDao depBeanDao) {
        this.depBeanDao = depBeanDao;
    }

    public void setOrgService(IOrganizationBeanService orgService) {
        this.orgService = orgService;
    }

    public void setAboutBeanDao(IAboutBeanDao aboutBeanDao) {
        this.aboutBeanDao = aboutBeanDao;
    }

    @Override
    public List<AreaBean> query(AreaBean areaBean) {
        return dao.find("select a from AreaBean a where a.name like ?1", "%" + areaBean.getName() + "%");
    }

    @Override
    public List<AreaBean> getRootBeanList() {
        return dao.find("select u from AreaBean u where u.parent is null");
    }

    @Override
    public List<AreaBean> getRootBeanListByQhdm() {
        AboutBean aboutBean = aboutBeanDao.get(1L);
        String qhdm = "";
        if (aboutBean != null) {
            qhdm = aboutBean.getXzqh_dm();
        }
        return dao.find("select u from AreaBean u where u.code = ?1", qhdm);
    }

    @Override
    public List<AreaBean> getChildBeanList(AreaBean node) {
        return dao.find("select u from AreaBean u where u.parent= ?1", node);
    }



    @Override
    public void beforeDeleteEntity(Long id, JsonStatus status) {
        List<AreaBean> areaBeans = dao.find("select ob from AreaBean ob where ob.id = ?1", id);
        if(areaBeans!=null&&!areaBeans.isEmpty()) {
            removeChildren(id);
            AreaBean area=areaBeans.get(0);
            //departmentBeanService.deleteByOrgId(id);
            updateParent(area.getParentId());
            status.setSuccess(true);
            status.setMsg("删除" + FUNCTION_NAME + "成功！");
        }else{
            status.setSuccess(true);
            status.setMsg(FUNCTION_NAME + "已经被删除！");
        }
    }

    @Override
    public void afterDeleteEntity(Long id, JsonStatus status) {

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
    public JsonStatus deleteEntity(long entityId) {
        JsonStatus jsonStatus = new JsonStatus();
        try {
            if (dao.get(entityId) == null) {
                jsonStatus.setFailure(true);
                jsonStatus.setMsg(FUNCTION_NAME + "{" + entityId + "}不存在！");
            } else {
                List<AreaBean> AreaBeans = dao.find("select ob from AreaBean ob where ob.id = ?1", entityId);
                if(AreaBeans!=null&&!AreaBeans.isEmpty()) {
                    removeChildren(entityId);
                    AreaBean org=AreaBeans.get(0);
                    dao.remove(entityId);
                    orgService.deleteByAreaId(entityId);
                    //departmentBeanService.deleteByOrgId(id);
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
     * @param id
     */
    public void updateParent(Long id){
        List<AreaBean> AreaBeans = dao.find("select ob from AreaBean ob where ob.id = ?1", id); //获得父节点
        if(AreaBeans!=null&&AreaBeans.size()>0){
            List<AreaBean> children = dao.find("select ob from AreaBean ob where ob.parentId = ?1", id); //获得父节点
            if(children==null||children.isEmpty()) {
                AreaBean parent = AreaBeans.get(0);
                parent.setIsLeaf(1);
                dao.save(parent);
            }
        }
    }


    @Override
    public void afterSaveEntity(AreaBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        AreaBean bean=(AreaBean)entity;
        if(bean.getParentId()!=-1){
            AreaBean parentAreaBean = (AreaBean) dao.get(bean.getParentId());
            if(parentAreaBean!=null&&parentAreaBean.getIsLeaf()==1){
                parentAreaBean.setIsLeaf(0);
                dao.save(parentAreaBean);
            }
        }
    }

    @Override
    public boolean isUpdate(AreaBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        AreaBean bean=(AreaBean)entity;
        List<AreaBean> beans = dao.find("select ob from AreaBean ob where ob.name = ?1", bean.getName());
        if(beans!=null&&beans.size()>0){
            AreaBean _area=beans.get(0);
            if(_area.getId()!=entity.getId()) {
                status.setFailure(true);
                status.setMsg(FUNCTION_NAME + "已经存在！");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSave(AreaBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        AreaBean bean=(AreaBean)entity;
        List<AreaBean> beans = dao.find("select ob from AreaBean ob where ob.name = ?1", bean.getName());
        if(beans!=null&&beans.size()>0){
            status.setSuccess(false);
            status.setMsg(FUNCTION_NAME + "已经存在！");
            return false;
        }
        return true;
    }

    @Override
    public void beforeUpdateEntity(AreaBean entity, JsonStatus status) {
        Assert.notNull(entity, "实体不能为空.");
        AreaBean oldArea = (AreaBean) dao.get(entity.getId());
        AreaBean area=(AreaBean)entity;
        area.setParentId(oldArea.getParentId());
        area.setIsLeaf(oldArea.getIsLeaf());

        super.beforeUpdateEntity(entity,status);
    }

    @Override
    public void afterUpdateEntity(AreaBean entity, JsonStatus status) {
        super.afterUpdateEntity(entity, status);
    }

    public void removeChildren(Long id){
        List<AreaBean> AreaBeans = dao.find("select ob from AreaBean ob where ob.parentId = ?1", id);
        if(AreaBeans!=null&&AreaBeans.size()>0){
            for(AreaBean org:AreaBeans){
                if(org.getIsLeaf()==0){ //存在子节点
                    removeChildren(org.getId());
                }
                dao.remove(org.getId());
                orgService.deleteByAreaId(org.getId());
                //departmentBeanService.deleteByOrgId(org.getId());
            }
        }
    }
    
    public AreaDTO getAllArea() {
//        Subject currentUser=SecurityUtils.getSubject();
//        Assert.notNull(currentUser, "当前登录用户不能为空.");
//        Map userInfo=(Map)currentUser.getSession().getAttribute(PermissionConstant.SYS_CURRENT_USER);
//        Assert.notNull(userInfo.get("user_id"), "当前登录用户编号不能为空.");
//        //所属部门
//        long depId=depUserBeanDao.findDepartmentIdByUserId(Long.parseLong(String.valueOf(userInfo.get("user_id"))));
//        List<DepartmentBean> departmentBeans=depBeanDao.find("select d from DepartmentBean d where d.id=?1",depId);
//        Assert.notEmpty(departmentBeans,"当前用户所属部门不能为空.");
        AreaDTO root=new AreaDTO();
        root.setId(-1);
        List<AreaBean> beans = dao.getAll();
        if(beans!=null&&beans.size()>0){
            List<AreaBean> rootElements = getRootElements(beans);
            if(rootElements!=null&&rootElements.size()>0) {
                for(AreaBean rootElement:rootElements){
                    Mapper mapper = new DozerBeanMapper();
                    AreaDTO AreaDTO = mapper.map(rootElement, AreaDTO.class);
                    AreaDTO.setLeaf(rootElement.getIsLeaf() == 0 ? false : true);
                    AreaDTO.setParentName("根机构");
                    AreaDTO.setText(rootElement.getName());
                    getChilden(AreaDTO, beans, mapper);
                    root.getChildren().add(AreaDTO);
                }
            }
        }
        return root;
    }


    /**
     * 递归函数加载子节点
     *
     * @param root
     * @param elements
     */
    private void getChilden(AreaDTO root, List<AreaBean> elements, Mapper mapper) {
        List<AreaDTO> children = new ArrayList<AreaDTO>();

        for (AreaBean areaBean : elements) {
            if (root.getId() != -1 && (root.getId() == areaBean.getParentId())) {
                AreaDTO AreaDTO = mapper.map(areaBean, AreaDTO.class);
                AreaDTO.setLeaf(areaBean.getIsLeaf() == 0 ? false : true);
                AreaDTO.setParentName(root.getName());
                AreaDTO.setText(areaBean.getName());
                children.add(AreaDTO);
                if (areaBean.getIsLeaf() == 0) {
                    getChilden(AreaDTO, elements, mapper);
                }
            }
        }
        root.setChildren(children);
    }

    /**
     * 获得所有根节点
     * @param elements
     * @return
     */
    private List<AreaBean> getRootElements(List<AreaBean> elements) {
        List<AreaBean> roots=new ArrayList<AreaBean>();
        for (AreaBean element : elements) {
            if (element.getParentId() == -1) {
                roots.add(element);
            }
        }
        return roots;
    }
}
