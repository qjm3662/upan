package cn.qjm253.controller;

import cn.qjm253.dao.daoImpl.FeedBackDaoImpl;
import cn.qjm253.dao.daoImpl.FileDaoImpl;
import cn.qjm253.dao.daoImpl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/3/3/003.
 */
public class BaseController {
    @Autowired
    protected UserDaoImpl userDao;
    @Autowired
    protected FileDaoImpl fileDao;
    @Autowired
    protected FeedBackDaoImpl feedBackDao;
}
