package cn.qjm253.dao.daoImpl;

import cn.qjm253.entity.FileInfo;
import cn.qjm253.entity.FollowInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/3/6 0006.
 */
@Transactional
@Repository(value = "followInfoDaoImpl")
public class FollowInfoDaoImpl extends BaseDaoImpl<FollowInfo, Integer>{
}
