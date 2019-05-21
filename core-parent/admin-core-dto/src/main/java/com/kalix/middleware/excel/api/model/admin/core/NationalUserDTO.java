package com.kalix.middleware.excel.api.model.admin.core;

import com.kalix.framework.core.api.web.model.BaseDTO;
import com.kalix.middleware.excel.api.annotation.ExcelField;

/**
 * 国家教育考生实体
 *
 * @author: hqj
 * @CreateDate: 2019-03-04
 * @version: 1.0
 */

public class NationalUserDTO extends BaseDTO {
    private Long userType;      // 用户类型
    private String loginName;   // 登录名
    private String idCards;     // 身份证号
    private String code;        // 学号
    private String name;        // 姓名
    private String sex;         // 性别
    private String mobile;      // 手机
    private String examCardNumber; // 准考证号

    @ExcelField(title = "用户类型", type=0, align = 1, dictType = "用户类型", sort = 10)
    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    @ExcelField(title = "登录名", type=0, align = 1, sort = 20)
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @ExcelField(title = "身份证号", type=0, align = 1, sort = 30)
    public String getIdCards() {
        return idCards;
    }

    public void setIdCards(String idCards) {
        this.idCards = idCards;
    }

    @ExcelField(title = "学号", type=0, align = 1, sort = 40)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ExcelField(title = "姓名", type=0,align = 1, sort = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title = "性别", type=0,align = 1, sort = 60)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @ExcelField(title = "手机", type=0,align = 1, sort = 70)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @ExcelField(title = "准考证号", type=0,align = 1, sort = 80)
    public String getExamCardNumber() {
        return examCardNumber;
    }

    public void setExamCardNumber(String examCardNumber) {
        this.examCardNumber = examCardNumber;
    }
}
