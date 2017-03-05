package cn.qjm253.utils;

/**
 * Created by qjm3662 on 2017/1/23.
 */
public class CodeMSG {
    public static final String CODE = "code";
    public static final String ERR_MSG = "errMSG";


    public static final int ALREADY_LOGIN = 0;              //正常登陆状态
    public static final int SUCCESS = 0;
    public static final int FAIL = -11;
    public static final int USERNAME_OR_PASSWORD_ERROR = -1; //用户名或密码错误
    public static final int CODE_ERROR = -2;                 //没有此文件可以下载，即验证码错误
    public static final int NO_CODE_ERROR = -3;                   //没有输入验证码
    public static final int FILE_SIZE_TO_LARGE_ERROR = -4;        //上传文件过大
    public static final int USERNAME_ALREADY_EXISTS = -5;       //用户名已存在
    public static final int USERNAME_NOT_EXISTS = -6;           //用户名不存在或传入的参数为空串
    public static final int COOKIE_INVALID = -7;                //Cookie失效（登录失效）
    public static final int PARAM_ERROR_FOLLOW = -8;             //没有传用户名或关注对象用户名
    public static final int USER_OR_TARGET_NOT_EXIST = -9;      //用户不存在或关注对象不存在
    public static final int PASSWORD_ERROR = -10;               //密码错误


    public static String getCodeMSG(int code){
        switch (code){
            case ALREADY_LOGIN:
                return "已经登录或注册成功";
            case USERNAME_OR_PASSWORD_ERROR:
                return "用户名或密码错误";
            case CODE_ERROR:
                return "没有此文件可以下载，即验证码错误";
            case NO_CODE_ERROR:
                return "没有输入验证码";
            case FILE_SIZE_TO_LARGE_ERROR:
                return "上传文件过大";
            case USERNAME_ALREADY_EXISTS:
                return "用户名已存在";
            case USERNAME_NOT_EXISTS:
                return "用户名不存在或传入的参数为空串";
            case COOKIE_INVALID:
                return "Cookie失效（登录失效）";
            case PARAM_ERROR_FOLLOW:
                return "没有传用户名或关注对象用户名";
            case USER_OR_TARGET_NOT_EXIST:
                return "用户不存在或关注对象不存在";
            case PASSWORD_ERROR:
                return "密码错误";
            case FAIL:
                return "失败";
            default:
                return "默认信息";
        }
    }
}
