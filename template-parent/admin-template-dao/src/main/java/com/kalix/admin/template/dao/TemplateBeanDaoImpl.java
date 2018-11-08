package com.kalix.admin.template.dao;

import com.kalix.admin.template.api.dao.ITemplateBeanDao;
import com.kalix.admin.template.entities.TemplateBean;
import com.kalix.framework.core.impl.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
// import java.util.UUID;

/**
 * @类描述：审计管理
 * @创建人： zhangqingxin
 * @创建时间：2014/10/10
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class TemplateBeanDaoImpl extends GenericDao<TemplateBean, String> implements ITemplateBeanDao {
//    private String uuid;

//    public TemplateBeanDaoImpl() {
//        uuid = UUID.randomUUID().toString();
//    }

    @Override
    @PersistenceContext(unitName = "admin-template-unit")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
