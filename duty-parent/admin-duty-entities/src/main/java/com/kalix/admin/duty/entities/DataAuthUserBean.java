package com.kalix.admin.duty.entities;

import com.kalix.framework.core.api.persistence.PersistentEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @类描述：数据权限与用户关联实体类
 * @创建人：hqj
 * @创建时间：2017-10-12
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
@Entity
@Table(name = "sys_data_auth_user")
public class DataAuthUserBean extends PersistentEntity {
    private Long userId;     //用户id
    private Long dataAuthId; //数据权限id

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDataAuthId() {
        return dataAuthId;
    }

    public void setDataAuthId(Long dataAuthId) {
        this.dataAuthId = dataAuthId;
    }
}
