package cn.qjm253.dao.daoImpl;

import cn.qjm253.dao.FileDao;
import cn.qjm253.dao.daoImpl.BaseDaoImpl;
import cn.qjm253.entity.FileInfo;
import cn.qjm253.entity.FollowInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/3/3/003.
 */
@Transactional
@Repository(value = "fileDaoImpl")
public class FileDaoImpl extends BaseDaoImpl<FileInfo, Integer> implements FileDao{
}
