package cn.qjm253.controller;

import cn.qjm253.utils.CodeMSG;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/6 0006.
 */
@Controller
public class GetShareCenterController extends BaseController{

    @RequestMapping(value = "/GetShareCenterAction", produces = "application/json;charset=UTF-8", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> getShareCenter(){
        String[] params = {"isPublic"};
        String hql = "from FileInfo f";
        hql = createHql(hql, "f", params);
        List list = fileDao.queryAll(hql, params, true);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(CodeMSG.CODE, CodeMSG.SUCCESS);
        result.put("shares", list);
        return result;
    }
}
