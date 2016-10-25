package com.kalix.admin.duty.entities;

import com.kalix.framework.core.api.dao.CascadeManager;
import com.kalix.framework.core.api.persistence.PersistentEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @create 2016-10-25 16:02.
 */
public class CascadeActivator extends CascadeManager {
    @Override
    public List<Class<? extends PersistentEntity>> getEntityClass() {
        List<Class<? extends PersistentEntity>> list = new ArrayList<>();
        list.add(DutyBean.class);
        list.add(DutyUserBean.class);
        return list;
    }
}
