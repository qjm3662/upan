package cn.qjm253.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Created by Administrator on 2017/3/9/009.
 */
public class MyExclusionStrategy implements ExclusionStrategy{
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return fieldAttributes.getAnnotation(JsonIgnore.class) != null;
    }

    public boolean shouldSkipClass(Class<?> aClass) {
        return aClass.getAnnotation(JsonIgnore.class) != null;
    }
}
