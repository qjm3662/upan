package cn.qjm253.utils;

import java.util.Random;
import java.util.UUID;

/**
 * Created by qjm3662 on 2017/1/31.
 */
public class UUIDUtil {
    //添加标识码
    public static String addUUID(String msg){
        return UUID.randomUUID() + msg;
    }

    //剔除标识码
    public static String deleteUUID(String msg){
        return msg.substring(36, msg.length());
    }

    //生成随机字符串
    public static String getRandomString(int length){
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        int num;
        for(int i = 0; i < length; i++){
            num = random.nextInt(62);
            buf.append(str.charAt(num));
        }
        return buf.toString();
    }
}
