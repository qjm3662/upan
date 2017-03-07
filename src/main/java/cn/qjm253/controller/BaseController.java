package cn.qjm253.controller;

import cn.qjm253.dao.daoImpl.FileDaoImpl;
import cn.qjm253.dao.UserDao;
import cn.qjm253.dao.daoImpl.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/3/3/003.
 */
public class BaseController {
    @Autowired
    protected UserDao userDao;
    @Autowired
    protected FileDaoImpl fileDao;
    @Autowired
    protected FeedBackDaoImpl feedBackDao;
    @Autowired
    protected FollowInfoDaoImpl followInfoDao;
    @Autowired
    protected Auth auth;

    public static Gson gson = new GsonBuilder().serializeNulls().create();

    public String createHql(String hql, String objectName, String[] paramsName){
        if(paramsName.length == 0){
            return null;
        }
        if(paramsName.length == 1){
            hql += " where " + objectName + "." + paramsName[0] + "=:" + paramsName[0];
        }else{
            hql += " where " + objectName + "." + paramsName[0] + "=:" + paramsName[0];
            for (int i = 1; i < paramsName.length; i++) {
                hql += " and " + objectName + "." + paramsName[i] + "=:" + paramsName[i];
            }
        }
        return hql;
    }
}
