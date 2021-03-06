package com.kalix.admin.template.dao;

import com.kalix.admin.template.api.dao.ITemplateContentBeanDao;
import com.kalix.admin.template.entities.TemplateContentBean;
import com.kalix.framework.core.impl.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

/**
 * @类描述：审计配置管理
 * @创建人： yangz
 * @创建时间：2018/02/09
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class TemplateBeanContentDaoImpl extends GenericDao<TemplateContentBean, Long> implements ITemplateContentBeanDao {
    private String uuid;

    public TemplateBeanContentDaoImpl() {
        uuid = UUID.randomUUID().toString();
    }

    @Override
    @PersistenceContext(unitName = "admin-template-unit")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
