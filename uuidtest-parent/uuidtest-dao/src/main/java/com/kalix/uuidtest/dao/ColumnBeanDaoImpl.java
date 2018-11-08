package com.kalix.uuidtest.dao;

import com.kalix.framework.core.impl.dao.GenericUUIDDao;
import com.kalix.uuidtest.api.dao.IColumnBeanDao;
import com.kalix.uuidtest.entities.ColumnBean;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Administrator on 2018/5/13.
 */
public class ColumnBeanDaoImpl extends GenericUUIDDao<ColumnBean, String> implements IColumnBeanDao {
    @Override
    @PersistenceContext(unitName = "uuidtest-cm")
    public void setEntityManager(EntityManager em) {
        super.setEntityManager(em);
    }
}
