package cn.qjm253.controller;

import cn.qjm253.dao.daoImpl.FileDaoImpl;
import cn.qjm253.dao.UserDao;
import cn.qjm253.dao.daoImpl.*;
import cn.qjm253.utils.MyExclusionStrategy;
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
    @Autowired
    protected Gson gson;
//    public static Gson gson = new GsonBuilder().setExclusionStrategies(new MyExclusionStrategy()).serializeNulls().create();

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
