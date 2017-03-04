package cn.qjm253.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by qjm3662 on 2017/3/4 0004.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{
    @RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
    public @ResponseBody String login(){

        return "";
    }

//    @RequestMapping(value = "/register", produces = "text/html;charset=UTF-8")
//    public @ResponseBody String register(@RequestParam("username") String username, @RequestParam("password") String password,
//                                         @RequestParam(value = "sex", required = false) int sex){
//
//    }


}
