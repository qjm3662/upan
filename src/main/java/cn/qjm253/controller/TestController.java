package cn.qjm253.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/3/3/003.
 */
@Controller
public class TestController {

    @RequestMapping(value = "/test")
    public @ResponseBody String test(){
        return "test";
    }

}
