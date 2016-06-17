package com.kalix.admin.core.dao;

import com.kalix.admin.core.api.dao.IUserBeanDao;
import com.kalix.admin.core.entities.UserBean;
import com.kalix.framework.core.api.persistence.JsonData;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by dell on 14-1-16.
 */

public class UserBeanDaoImpl extends BaseAdminDao<UserBean, Long> implements IUserBeanDao {
    private final String className = UserBean.class.getName();

    /**
     * Constructor that sets the entity to User.class.
     */
    public UserBeanDaoImpl() {
//        super.setEntityManager(UserBean.class);
    }

    @Override
    public JsonData getUserList(int page,int limit) {
        return super.getAll(page, limit);
    }

    @Override
    public UserBean saveUser(UserBean user) {
        return super.save(user);
    }

    @Override
    public void removeUser(Long userId) {
        super.remove(userId);
    }

    @Override
    public UserBean getUser(Long userId) {
        return super.get(userId);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public UserBean getUser(String username) {
        UserBean user = this.findUnique("select u from UserBean u where u.loginName=?1", username);
        return user;
    }

    @Override
    public void updateUserLoginInfo(long id, String loginIp) {
        this.update("update UserBean u set u.loginIp=?1, u.loginDate=?2 where u.id = ?3", loginIp, new Date(), id);
    }

    /*@Override
    public CriteriaQuery buildCriteriaQuery(QueryDTO queryDTO) {
        UserDTO userDTO = (UserDTO) queryDTO;
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserBean> criteriaQuery = criteriaBuilder.createQuery(UserBean.class);
        Root<UserBean> from = criteriaQuery.from(UserBean.class);
        EntityType<UserBean> userBean_ = from.getModel(); //实体元数据
        List<Predicate> predicatesList = new ArrayList<Predicate>();

        if (userDTO.getName() != null && !userDTO.getName().trim().isEmpty()) {
            SingularAttribute<UserBean, String> name = (SingularAttribute<UserBean, String>) userBean_.getSingularAttribute("name");
            predicatesList.add(criteriaBuilder.like(from.get(name), "%" + userDTO.getName() + "%"));
        }
        if (userDTO.getLoginName() != null && !userDTO.getLoginName().trim().isEmpty()) {
            SingularAttribute<UserBean, String> loginName = (SingularAttribute<UserBean, String>) userBean_.getSingularAttribute("loginName");
            predicatesList.add(criteriaBuilder.like(from.get(loginName), "%" + userDTO.getLoginName() + "%"));
        }
        if (userDTO.getAvailable() != -1) {
            SingularAttribute<UserBean, Integer> available = (SingularAttribute<UserBean, Integer>) userBean_.getSingularAttribute("available");
            predicatesList.add(criteriaBuilder.equal(from.get(available), userDTO.getAvailable()));
        }
        criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
        CriteriaQuery select = criteriaQuery.select(from);
        return select;
    }*/


}
