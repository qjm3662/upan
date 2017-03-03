package cn.qjm253.controller;

import cn.qjm253.entity.FileInfo;
import cn.qjm253.entity.User;
import cn.qjm253.utils.HibernateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Modifier;
import java.util.List;

/**
 * Created by qjm3662 on 2017/3/3 0003.
 */
@RequestMapping("/GetFileInfoAction")
@Controller
public class GetFileInfoController extends BaseController{
    @RequestMapping(value = "/{identifyCode}")
    public @ResponseBody boolean GetFileInfo(@PathVariable String identifyCode){
//        String hql = "from FileInfo f where f.identifyCode = ?";
//        FileInfo fileInfo = fileDao.findOne(hql, identifyCode);
        User u1 = new User("Robbin5", "123456", "null", "The better day", 1);
        boolean b = userDao.save(u1);
        return b;
    }
}
