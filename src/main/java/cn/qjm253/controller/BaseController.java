package cn.qjm253.controller;

import cn.qjm253.dao.daoImpl.FeedBackDaoImpl;
import cn.qjm253.dao.daoImpl.FileDaoImpl;
import cn.qjm253.dao.daoImpl.UserDaoImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    public static Gson gson = new GsonBuilder().serializeNulls().create();

    public String createHql(String hql, String objectName, String[] paramsName){
        if(paramsName.length == 0){
            return null;
        }
        if(paramsName.length == 1){
            hql += " where " + objectName + "." + paramsName[0] + "=:" + paramsName[0];
        }
        return hql;
    }
}
