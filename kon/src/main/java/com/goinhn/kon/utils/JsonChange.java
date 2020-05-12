package com.goinhn.kon.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json和对象转换
 *
 * @author goinhn
 */
public class JsonChange {

    public static String objectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";

        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static Object jsonToObject(String json, Object object) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            object = mapper.readValue(json, object.getClass());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return object;
    }

}
