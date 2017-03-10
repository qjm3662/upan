package cn.qjm253.dao.daoImpl;

import cn.qjm253.entity.User;
import cn.qjm253.utils.Config;
import cn.qjm253.utils.MyExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * Created by qjm3662 on 2017/3/4 0004.
 */
@Repository("auth")
public class Auth {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private Gson gson = new GsonBuilder().setExclusionStrategies(new MyExclusionStrategy()).create();

    /**
     * 登录函数
     * @param response
     * @param value
     */
    public void login(HttpServletRequest request, HttpServletResponse response, String value){
        String cookieName = "#" + System.currentTimeMillis() + "#";
        try {
            cookieName = URLEncoder.encode(cookieName, "UTF-8");
            value = URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie cookie = new Cookie(Config.cookieName, cookieName);
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        HttpSession session = request.getSession();
        session.setAttribute(cookieName, value);
        redisTemplate.opsForValue().set(cookieName, value);
    }


    /**
     * 根据请求中的cookie获取用户信息
     * @param request
     * @return
     */
    public User getUser(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookies.length == 0){
            System.out.println("cookie is null");
            return null;
        }
        String cookieName = "";
        for (Cookie c : cookies) {
            if(c.getName().equals(Config.cookieName)){
                cookieName = c.getValue();
            }
        }
        System.out.println("cookieName : " + cookieName);
        String value = redisTemplate.opsForValue().get(cookieName);
        System.out.println("value" + value);
        if(value == null){
            return null;
        }
        try {
            value = URLDecoder.decode(value, "UTF-8");
            return gson.fromJson(value, User.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
