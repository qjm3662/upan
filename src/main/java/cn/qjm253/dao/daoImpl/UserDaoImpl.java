package cn.qjm253.dao.daoImpl;

import cn.qjm253.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/3/3/003.
 */
@Transactional
@Repository(value = "userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl<User, Integer>{

}
