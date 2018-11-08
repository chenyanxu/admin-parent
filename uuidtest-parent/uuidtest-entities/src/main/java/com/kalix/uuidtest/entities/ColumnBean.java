package com.kalix.uuidtest.entities;
import com.kalix.framework.core.api.persistence.PersistentEntity;
import com.kalix.framework.core.api.persistence.PersistentUUIDEntity;

import javax.persistence.*;

/**
 * Created by Administrator on 2018/5/13.
 * 栏目表
 */
@Entity
@Table(name = "uuidtest_column")
public class ColumnBean extends PersistentUUIDEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid-hex")
//    private String uuid;
    private String name;//栏目名称
    private int sequence;//排序
    private String url;//栏目地址


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    public String getUuid() {
//        return uuid;
//    }
//
//    public void setUuid(String uuid) {
//        this.uuid = uuid;
//    }
}
