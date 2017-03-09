package cn.qjm253.controller;

import cn.qjm253.entity.FeedBack;
import cn.qjm253.entity.FollowInfo;
import cn.qjm253.entity.SimpleUserInfo;
import cn.qjm253.entity.User;
import cn.qjm253.utils.CodeMSG;
import cn.qjm253.utils.Config;
import org.apache.shiro.codec.Base64;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qjm3662 on 2017/3/4 0004.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    public static final String DEFAULT_AVATAR_PATH = Config.url + "/download?fileName=default_avatar.jpg";


    /**
     * 注册
     *
     * @param username
     * @param password
     * @param sex
     * @param response
     * @return
     */
    @RequestMapping(value = "/register", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> register(@RequestParam("username") String username, @RequestParam("password") String password,
                                 @RequestParam(value = "sex", required = false) int sex, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (username.equals("") || password.equals("")) {
            result.put(CodeMSG.CODE, CodeMSG.USERNAME_NOT_EXISTS);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.USERNAME_NOT_EXISTS));
            return result;
        }
        String hql = "select username from User u";
        String[] params = {"username"};
        hql = createHql(hql, "u", params);
        if (userDao.query(hql, params, username) == null) {       //数据库中没有该用户，允许注册
            String base4Pwd = Base64.encodeToString(password.getBytes());
            User user = new User();
            user.setUsername(username);
            user.setPassword(base4Pwd);
            user.setNickname("优云小新");
            user.setAvatar(DEFAULT_AVATAR_PATH);
            user.setSex(sex);
            user.setSignature("Everything is possible!");
            if (userDao.save(user)) {
                result.put("code", CodeMSG.SUCCESS);
                result.put("avatar", DEFAULT_AVATAR_PATH);
            } else {
                result.put(CodeMSG.CODE, CodeMSG.FAIL);
                result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.FAIL));
            }
        } else {
            result.put(CodeMSG.CODE, CodeMSG.USERNAME_ALREADY_EXISTS);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.USERNAME_ALREADY_EXISTS));
        }
        return result;
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> login(@RequestParam String username, @RequestParam String password,
                              HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (username.equals("") || password.equals("")) {
            result.put(CodeMSG.CODE, CodeMSG.USERNAME_NOT_EXISTS);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.USERNAME_NOT_EXISTS));
            return result;
        }
        String hql = "select new User(uid, username, password) from User u";
        String[] params = {"username"};
        String base64Pwd = Base64.encodeToString(password.getBytes());
        hql = createHql(hql, "u", params);
        User u = userDao.query(hql, params, username);
        if (u == null) {       //数据库中没有该用户，不允许登录
            result.put(CodeMSG.CODE, CodeMSG.USERNAME_NOT_EXISTS);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.USERNAME_NOT_EXISTS));
        } else {
            if (u.getPassword().equals(base64Pwd)) {
                auth.login(request, response, gson.toJson(u));
                result.put(CodeMSG.CODE, CodeMSG.SUCCESS);
            } else {
                result.put(CodeMSG.CODE, CodeMSG.PASSWORD_ERROR);
                result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.PASSWORD_ERROR));
            }
        }
        return result;
    }


    /**
     * 修改用户信息
     *
     * @param nickname
     * @param signature
     * @param sex
     * @param request
     * @return
     */
    @RequestMapping(value = "/ModifyUserInfoAction", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> modifyUserInfoAction(@RequestParam String nickname, @RequestParam String signature,
                                             @RequestParam int sex, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        User user = auth.getUser(request);
        if (user == null) {       //没有登录或登录失效
            result.put(CodeMSG.CODE, CodeMSG.NOT_LOGIN_OR_LOGIN_INVALID);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.NOT_LOGIN_OR_LOGIN_INVALID));
            return result;
        }
        String[] params = {"signature", "sex", "nickname", "usn"};
        String hql = "update User u set u.signature = :signature, u.sex = :sex, u.nickname = :nickname " +
                "where u.username = :usn";
        if (!userDao.update(hql, params, signature, sex, nickname, user.getUsername())) {      //更新失败
            result.put(CodeMSG.CODE, CodeMSG.FAIL);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.FAIL));
        } else {
            result.put(CodeMSG.CODE, CodeMSG.SUCCESS);
        }
        return result;
    }

    /**
     * 关注
     *
     * @param targetUsername
     * @param request
     * @return
     */
    @RequestMapping(value = "/follow", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> followSb(@RequestParam String targetUsername, HttpServletRequest request) {
        System.out.println("what");
        Map<String, Object> result = new HashMap<String, Object>();
        if (targetUsername == null || targetUsername.equals("")) {
            result.put(CodeMSG.CODE, CodeMSG.FAIL);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.FAIL));
        }
        User user = auth.getUser(request);
        if (user == null) {       //没有登录或登录失效
            result.put(CodeMSG.CODE, CodeMSG.NOT_LOGIN_OR_LOGIN_INVALID);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.NOT_LOGIN_OR_LOGIN_INVALID));
            return result;
        }
        String[] params = {"username"};
        String hql = "from User u";
        hql = createHql(hql, "u", params);
        User other = userDao.query(hql, params, targetUsername);
        if (other == null) {      //要关注的对象不存在
            result.put(CodeMSG.CODE, CodeMSG.USERNAME_NOT_EXISTS);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.USERNAME_NOT_EXISTS));
            return result;
        }
        FollowInfo followInfo = new FollowInfo();
        followInfo.setMyselfName(user.getUsername());
        followInfo.setOther(other);
        followInfo.setOtherName(other.getUsername());
        followInfo.setCreateTime(System.currentTimeMillis());
        if(!followInfoDao.save(followInfo)){
            result.put(CodeMSG.CODE, CodeMSG.FAIL);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.FAIL));
        }
        result.put(CodeMSG.CODE, CodeMSG.SUCCESS);
        return result;
    }

    /**
     * 取消关注
     *
     * @param targetUsername
     * @param request
     * @return
     */
    @RequestMapping(value = "/unFollow", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> unFollowSb(@RequestParam String targetUsername, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (targetUsername == null || targetUsername.equals("")) {
            result.put(CodeMSG.CODE, CodeMSG.FAIL);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.FAIL));
        }
        User user = auth.getUser(request);
        if (user == null) {       //没有登录或登录失效
            result.put(CodeMSG.CODE, CodeMSG.NOT_LOGIN_OR_LOGIN_INVALID);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.NOT_LOGIN_OR_LOGIN_INVALID));
            return result;
        }
        String[] params = {"myselfName", "otherName"};
        String hql = "delete from FollowInfo f";
        hql = createHql(hql, "f", params);
        if (!followInfoDao.update(hql, params, user.getUsername(), targetUsername)) {      //删除关注信息失败
            result.put(CodeMSG.CODE, CodeMSG.FAIL);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.FAIL));
        } else {
            result.put(CodeMSG.CODE, CodeMSG.SUCCESS);
        }
        return result;
    }

    /**
     * 获取关注的人信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/followInfo", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> getFollowInfo(HttpServletRequest request) {
        User user = auth.getUser(request);
        Map<String, Object> result = new HashMap<String, Object>();
        if (user == null) {       //未登录或者登录失效
            result.put(CodeMSG.CODE, CodeMSG.NOT_LOGIN_OR_LOGIN_INVALID);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.NOT_LOGIN_OR_LOGIN_INVALID));
        } else {
            String[] params = {"myselfName"};
            String hql = "from FollowInfo fi";
            hql = createHql(hql, "fi", params);
            List<FollowInfo> list = followInfoDao.queryAll(hql, params, user.getUsername());
            result.put(CodeMSG.CODE, CodeMSG.SUCCESS);
            if (list == null) {
                result.put("followings", null);
            } else {
                for (FollowInfo f : list) {
                    f.setSimpleUserInfo(new SimpleUserInfo(f.getOther()));
                }
                result.put("followings", list);
            }
        }
        return result;
    }

    /**
     * 修改密码
     *
     * @param request
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/revisePSD", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> revisePassword(HttpServletRequest request, @RequestParam String oldPassword,
                                       @RequestParam String newPassword) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (oldPassword == null || oldPassword.equals("") || newPassword == null || newPassword.equals("")) {
            result.put(CodeMSG.CODE, CodeMSG.FAIL);
            return result;
        }
        User user = auth.getUser(request);
        if (user == null) {       //未登录或者登录失效
            result.put(CodeMSG.CODE, CodeMSG.NOT_LOGIN_OR_LOGIN_INVALID);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.NOT_LOGIN_OR_LOGIN_INVALID));
            return result;
        }
        user = userDao.get(User.class, user.getUid());
        try {
            newPassword = URLEncoder.encode(newPassword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return result;
        }
        if (user.getPassword().equals(Base64.encodeToString(oldPassword.getBytes()))) {   //用户旧密码正确
            String[] params = {"password", "usn"};
            String hql = "update User u set u.password = :password where u.username = :usn";
            if (userDao.update(hql, params, Base64.encodeToString(newPassword.getBytes()), user.getUsername())) {
                result.put(CodeMSG.CODE, CodeMSG.SUCCESS);
            } else {
                result.put(CodeMSG.CODE, CodeMSG.FAIL);
            }
        } else {
            result.put(CodeMSG.CODE, CodeMSG.USERNAME_OR_PASSWORD_ERROR);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.USERNAME_OR_PASSWORD_ERROR));
        }
        return result;
    }

    /**
     * 用户反馈
     *
     * @param request
     * @param text
     * @param phoneNumber
     * @return
     */
    @RequestMapping(value = "/feedback", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> feedBack(HttpServletRequest request, @RequestParam String text,
                                 @RequestParam String phoneNumber) {
        Map<String, Object> result = new HashMap<String, Object>();
        User user = auth.getUser(request);
        if (user == null) {       //未登录或者登录失效
            result.put(CodeMSG.CODE, CodeMSG.NOT_LOGIN_OR_LOGIN_INVALID);
            result.put(CodeMSG.ERR_MSG, CodeMSG.getCodeMSG(CodeMSG.NOT_LOGIN_OR_LOGIN_INVALID));
            return result;
        }
        FeedBack feedBack = new FeedBack();
        feedBack.setPhoneNumber(phoneNumber);
        feedBack.setText(text);
        feedBack.setUsername(user.getUsername());
        if (feedBackDao.save(feedBack)) {
            result.put(CodeMSG.CODE, CodeMSG.SUCCESS);
        } else {
            result.put(CodeMSG.CODE, CodeMSG.FAIL);
        }
        return result;
    }

}
