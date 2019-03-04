package com.kalix.admin.core.entities;

import com.kalix.framework.core.api.persistence.UserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @类描述：用户实体实现类
 * @创建人：sunlf
 * @创建时间：2014-04-26 上午11:10
 * @修改人：
 * @修改时间：
 * @修改备注：
 */
@Entity
@Table(name = "sys_user")
@ApiModel("用户<br>UserBean")
public class UserBean extends UserEntity {
    @ApiModelProperty(value = "用户类型", example = "0")
    private Long userType;
    @ApiModelProperty(value = "工号", example = "0")
    private String code;
    @ApiModelProperty(value = "岗位", example = "0")
    private Integer position;
    @ApiModelProperty(value = "身份证号", example = "0")
    private String idCards;
//    @ApiModelProperty(value="性别（男 女）",allowableValues = "男,女",position=2,example = "男")
//    private String sex;
//    @ApiModelProperty(value="登录名",position=3,example = "test_login")
//    private String loginName;
//    @ApiModelProperty(value="姓名",position =4,example = "陈某")
//    private String name;
//    @ApiModelProperty(value="密码",position = 5,example = "123")
//    private String password;
//    @ApiModelProperty(value="邮箱",position=6,example = "text@kalix.ocm")
//    private String email;
//    @ApiModelProperty(value="固定电话",position=7,example = "043288888888")
//    private String phone;
//    @ApiModelProperty(value="手机",position = 8,example = "18866667777")
//    private String mobile;
//    @ApiModelProperty(value="头像地址",position = 9,example = "http://head.png")
//    private String icon;
//    @ApiModelProperty(value="是否可用：（0-不可用 1-可用）",allowableValues = "0,1",position = 10,example = "1")
//    private Long available = 1L;
//    @ApiModelProperty(value="最后登陆IP",hidden=true)
//    private String loginIp;
//    @ApiModelProperty(value="最后登陆日期",hidden = true)
//    private Date loginDate;
//
//    public String getLoginName() {
//        return loginName;
//    }
//
//    public void setLoginName(String loginName) {
//        this.loginName = loginName;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//
//    public String getLoginIp() {
//        return loginIp;
//    }
//
//    public void setLoginIp(String loginIp) {
//        this.loginIp = loginIp;
//    }
//
//    public Date getLoginDate() {
//        return loginDate;
//    }
//
//    public void setLoginDate(Date loginDate) {
//        this.loginDate = loginDate;
//    }
//
//    public Long getAvailable() {
//        return available;
//    }
//
//    public void setAvailable(Long available) {
//        this.available = available;
//    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
//
//    public String getSex() {
//        return sex;
//    }
//
//    public void setSex(String sex) {
//        this.sex = sex;
//    }
//
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUserType() { return userType;}

    public void setUserType(Long userType) {this.userType = userType;}
//
//    public String getIcon() {
//        return icon;
//    }
//
//    public void setIcon(String icon) {
//        this.icon = icon;
//    }


    public String getIdCards() {
        return idCards;
    }

    public void setIdCards(String idCards) {
        this.idCards = idCards;
    }
}
