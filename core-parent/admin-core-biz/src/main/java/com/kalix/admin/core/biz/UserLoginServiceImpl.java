package com.kalix.admin.core.biz;

import com.kalix.admin.core.api.biz.IPermissionBeanService;
import com.kalix.admin.core.api.dao.IUserBeanDao;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.framework.core.api.ErrorCodeValue;
import com.kalix.framework.core.api.security.IUserLoginService;
import com.kalix.framework.core.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by dell on 14-3-25.
 */

public class UserLoginServiceImpl implements IUserLoginService {
    //    @Named
    /*@Inject
    @Reference(id="userBeanDao",serviceInterface = IUserBeanDao.class)*/
    private IUserBeanDao userBeanDao;


    private IPermissionBeanService permissionBeanService;

    /**
     * 设置cookie
     *
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    private static void setCookie(HttpServletResponse response, String name,
                                  String value, int maxAge) {
        if (value == null) {
            value = "";
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void setPermissionBeanService(IPermissionBeanService permissionBeanService) {
        this.permissionBeanService = permissionBeanService;
    }

    public void setUserBeanDao(IUserBeanDao userBeanDao) {
        this.userBeanDao = userBeanDao;
    }

    public boolean validateUserStatus(String username) {
        UserBean user = userBeanDao.getUser(username);
        if (user == null) {
            throw new UnknownAccountException("Unknown Account Exception!");
        }
        if (user.getAvailable() == 0) {
            throw new LockedAccountException("Unknown Account Exception!");
        }
        return true;
    }
    @Override
    public Map login(String username, String password) {
        Map map = new HashMap();
        int result = -1;

            UserBean user = userBeanDao.getUser(username);
            if (user == null) {
                throw new UnknownAccountException("Unknown Account Exception!");
            }
            //判断密码和用户类型是否对应
            if (encrypt(password).equals(user.getPassword())) {
                Map resMap = new HashMap();
                resMap.put("user_id", user.getId());
                resMap.put("name", user.getName());
                resMap.put("user_name", user.getLoginName());
                resMap.put("password", user.getPassword());
                map.put("response", resMap);

            } else {
                throw new IncorrectCredentialsException("Incorrect Credentials Exception!");
            }

        return map;

    }

    @Override
    public void updateUserLoginInfo(long id, String loginIp) {
        userBeanDao.updateUserLoginInfo(id, loginIp);
    }

    @Override
    public List<String> getUserPermission(String username) {
        List<String> stringList = new ArrayList<String>();
        UserBean userBean = userBeanDao.getUser(username);
        if (userBean != null) {
            stringList.addAll(permissionBeanService.getApplicationCodesByUserId(userBean.getId()));
            stringList.addAll(permissionBeanService.getFunctionCodesByUserId(userBean.getId()));
        }
        return stringList;
    }

    /**
     * 获得登录用户名
     *
     * @return
     */
    @Override
    public String getLoginName() {
        Subject subject = SecurityUtils.getSubject();
        String userName = (String) subject.getPrincipal();
        return userName;
    }

    private String encrypt(String text) {
        return MD5Util.encode(text);
    }

    @Override

//    @UserExist
    public Map loginByPhone(String username, String password, String client,
                            HttpServletRequest request, HttpServletResponse response) {
        Map map = new HashMap();
        int result = -1;
        try {
            UserBean user = userBeanDao.findUnique("select u from UserBean u where u.loginName=?1", username);
            if (user != null && password.equals(user.getPassword())) {
                logon(user, client, request, response);
                result = 1;
                Map resMap = new HashMap();
                resMap.put("id", user.getId());
                resMap.put("name", user.getName());
                resMap.put("login_name", user.getLoginName());
                resMap.put("password", user.getPassword());
                map.put("response", resMap);
            }
        } catch (Exception e) {
            map.put("errorCode", ErrorCodeValue.INNER_ERROR);
            e.printStackTrace();
        } finally {
            map.put("result", result);
        }
        return map;
    }

    private HashMap logon(UserBean user, String client, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int rs = 1;
        HashMap rsMap = new HashMap();
        try {
            String map_provide = "1";//先设成1 用时再改
            //String value = user.getId()+"::"+user.getLoginName()+"::"+user.getName()+"::"+map_provide+"::"+client;
            String value = user.getId() + "::" + user.getLoginName();
            int maxAge;
            if (client.equals("0")) {
                maxAge = 24 * 60 * 60;
            } else {
                maxAge = 60 * 24 * 60 * 60;
            }
            //String key = UtilTools.generateKey().toString();
            //value = UtilTools.encryptString(key, value);
            //value = URLEncoder.encode(value + "||" + key, "UTF-8");
            setCookie(response, "jldaren.cooperate", value, maxAge);
            rsMap.put("result", rs);
            rsMap.put("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsMap;
    }

    @Override
    public Map updateToken(HttpServletRequest request, String token, Long user_id) {
        int result = -1;
        Map map = new HashMap();
        try {
            if (user_id != null) {
                userBeanDao.update("update UserRelBean u set u.token=null where u.token='" + token + "'");
                userBeanDao.update("update UserRelBean u set u.token='" + token + "' where u.user_id=" + user_id);
                result = 1;
            }
        } catch (Exception e) {
            map.put("errorCode", ErrorCodeValue.INNER_ERROR);
            e.printStackTrace();
        } finally {
            map.put("result", result);
        }
        return map;
    }
}
