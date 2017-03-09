package cn.qjm253.controller;

import cn.qjm253.entity.FileInfo;
import cn.qjm253.entity.SimpleUserInfo;
import cn.qjm253.entity.User;
import cn.qjm253.utils.CodeMSG;
import cn.qjm253.utils.HibernateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qjm3662 on 2017/3/3 0003.
 */
@RequestMapping("/GetFileInfoAction")
@Controller
public class GetFileInfoController extends BaseController {

    @RequestMapping(value = "/{identifyCode}", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public
    @ResponseBody
    String GetFileInfo(@PathVariable String identifyCode) {
        String[] params = new String[]{"identifyCode"};
        String hql = "from FileInfo f";
        hql = createHql(hql, "f", params);
        FileInfo fileInfo = fileDao.query(hql, params, identifyCode);
        if(fileInfo != null && fileInfo.getOwner_user() != null){       //如果该文件是确有用户上传，就把用户的信息一并设置进去返回给客户端
            fileInfo.setOwner(new SimpleUserInfo(fileInfo.getOwner_user()));
        }
        return gson.toJson(fileInfo, FileInfo.class);
    }
}
