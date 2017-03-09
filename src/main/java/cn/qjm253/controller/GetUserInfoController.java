package cn.qjm253.controller;

import cn.qjm253.entity.User;
import cn.qjm253.utils.CodeMSG;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/5 0005.
 */

@Controller
public class GetUserInfoController extends BaseController {

    @RequestMapping(value = "/GetUserInfoAction", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> getUserInfo(@RequestParam(value = "username") String username) {
        Map<String, Object> result = new HashMap<String, Object>();
        if(username.equals("")){
            result.put(CodeMSG.CODE, CodeMSG.USERNAME_NOT_EXISTS);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.USERNAME_NOT_EXISTS));
            return result;
        }
        String[] params = {"username"};
        String hql = "from User u";
        hql = createHql(hql, "u", params);
        User user = userDao.query(hql, params, username);
        if(user != null){
            result.put(CodeMSG.CODE, CodeMSG.SUCCESS);
            result.put("user", user);
        }else{
            result.put(CodeMSG.CODE, CodeMSG.USERNAME_NOT_EXISTS);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.USERNAME_NOT_EXISTS));
        }
        return result;
    }
}
