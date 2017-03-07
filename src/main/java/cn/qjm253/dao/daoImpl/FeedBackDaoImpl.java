package cn.qjm253.dao.daoImpl;

import cn.qjm253.dao.FeedBackDao;
import cn.qjm253.entity.FeedBack;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/3/3/003.
 */
@Transactional
@Repository(value = "feedbackDaoImpl")
public class FeedBackDaoImpl extends BaseDaoImpl<FeedBack, Integer> implements FeedBackDao{
}
