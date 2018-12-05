package com.caidapao.fgo.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by caidapao on 2017/11/27.
 * json对象公具类
 */
public class JsonUtils {
    public static ObjectMapper mapper = new ObjectMapper();

    /**
     * 将一个对象转成json字符串
     * @param instance
     * @return
     */
    public static String toJsonString(Object instance){
        try {
            return mapper.writeValueAsString(instance);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json字符串转成对象
     * @param jsonString
     * @param type
     * @param <T>
     * @return
     */
    public static  <T> T toBean(String jsonString,Class<T> type){
        try {
            return mapper.readValue(jsonString, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
