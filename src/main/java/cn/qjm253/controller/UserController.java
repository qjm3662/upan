package cn.qjm253.controller;

import cn.qjm253.entity.User;
import cn.qjm253.utils.CodeMSG;
import cn.qjm253.utils.Config;
import org.apache.shiro.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qjm3662 on 2017/3/4 0004.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{
    public static final String DEFAULT_AVATAR_PATH = Config.url + "/download?fileName=default_avatar.jpg";

    @RequestMapping(value = "/register", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public @ResponseBody
    Map<String, Object> register(@RequestParam("username") String username, @RequestParam("password") String password,
                                 @RequestParam(value = "sex", required = false) int sex, HttpServletResponse response){
        Map<String, Object> result = new HashMap<String, Object>();
        if(username.equals("") || password.equals("")){
            result.put(CodeMSG.CODE, CodeMSG.USERNAME_NOT_EXISTS);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.USERNAME_NOT_EXISTS));
            return result;
        }
        String hql = "select username from User u";
        String[] params = {"username"};
        hql = createHql(hql, "u", params);
        if(userDao.query(hql, params, username) == null){       //数据库中没有该用户，允许注册
            String base4Pwd = Base64.encodeToString(password.getBytes());
            User user = new User();
            user.setUsername(username);
            user.setPassword(base4Pwd);
            user.setNickname("优云小新");
            user.setAvatar(DEFAULT_AVATAR_PATH);
            user.setSex(sex);
            user.setSignature("Everything is possible!");
            if(userDao.save(user)){
                result.put("code", CodeMSG.SUCCESS);
                result.put("avatar", DEFAULT_AVATAR_PATH);
            }else{
                result.put(CodeMSG.CODE, CodeMSG.FAIL);
                result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.FAIL));
            }
        }else{
            result.put(CodeMSG.CODE, CodeMSG.USERNAME_ALREADY_EXISTS);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.USERNAME_ALREADY_EXISTS));
        }
        return result;
    }

    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public @ResponseBody Map<String, Object> login(@RequestParam String username, @RequestParam String password,
                                                   HttpServletResponse response){
        Map<String, Object> result = new HashMap<String, Object>();
        if(username.equals("") || password.equals("")){
            result.put(CodeMSG.CODE, CodeMSG.USERNAME_NOT_EXISTS);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.USERNAME_NOT_EXISTS));
            return result;
        }
        String hql = "select new User(username, password) from User u";
        String[] params = {"username"};
        String base64Pwd = Base64.encodeToString(password.getBytes());
        hql = createHql(hql, "u", params);
        User u = userDao.query(hql, params, username);
        if(u == null) {       //数据库中没有该用户，不允许登录
            result.put(CodeMSG.CODE, CodeMSG.USERNAME_NOT_EXISTS);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.USERNAME_NOT_EXISTS));
        }else{
            if(u.getPassword().equals(base64Pwd)){
                auth.login(response, gson.toJson(u));
                result.put(CodeMSG.CODE, CodeMSG.SUCCESS);
            }else{
                result.put(CodeMSG.CODE, CodeMSG.PASSWORD_ERROR);
                result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.PASSWORD_ERROR));
            }
        }
        return result;
    }

//
//    @RequestMapping(value = "/ModifyUserInfoAction", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
//    public @ResponseBody Map<String, Object> modifyUserInfoAction(@RequestParam String nickname, @RequestParam String signatue,
//                                                                  @RequestParam int sex){
//
//
//    }

}
