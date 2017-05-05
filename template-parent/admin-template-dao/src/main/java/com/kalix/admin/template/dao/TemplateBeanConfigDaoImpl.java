package com.kalix.admin.template.dao;

import com.kalix.admin.template.api.dao.ITemplateConfigBeanDao;
import com.kalix.admin.template.entities.TemplateConfigBean;
import com.kalix.framework.core.impl.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

/**
 * @类描述：审计配置管理
 * @创建人： zhangqingxin
 * @创建时间：2014/10/10
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
public class TemplateBeanConfigDaoImpl extends GenericDao<TemplateConfigBean, Long> implements ITemplateConfigBeanDao {
    private String uuid;

    public TemplateBeanConfigDaoImpl() {
        uuid = UUID.randomUUID().toString();
    }

    @Override
    @PersistenceContext(unitName = "admin-template-unit")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
