package org.jeecg.modules.bigscreen.util.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public final class JsonUtil {

    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper objectMapper = new CustomObjectMapper();

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * 将 POJO 对象转为 JSON 字符串
     */
    public static <T> String toJson(T pojo) {
        String json ;
        try {
            json = objectMapper.writeValueAsString(pojo);
        } catch (Exception e) {
            throw new RuntimeException("transform json string error",e);
        }
        return json;
    }

    /**
     * 将 JSON 字符串转为 POJO 对象
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo = null;
        try {
            pojo = objectMapper.readValue(json, type);
        } catch (Exception e) {
            logger.error("- parse json object error,json string:{}\n class type:{}",json,type);
            throw new RuntimeException("parse json object error",e);
        }
        return pojo;
    }

    /**
     * JSON串转换为Java泛型对象，可以是各种类型，此方法最为强大。
     * @param jsonStr  JSON字符串
     * @param typeReference,例如: new TypeReference< List<FamousUser> >(){}
     * @return List对象列表
     */
    public static <T> T toCollection(String jsonStr, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(jsonStr, typeReference);
        } catch (Exception e) {
            logger.error("- parse json collection error,json string:{}\n class type:{}",jsonStr,typeReference);
            throw new RuntimeException("parse json collection error ",e);
        }
    }

    /**
     * JSON串转换为Java泛型对象，可以是各种类型，且泛型可嵌套
     * <p>
     * Created by zhengjingguo on 2017/6/19.
     *
     * @param <T>               泛型
     * @param jsonStr           JSON字符串
     * @param typeReference,例如: new TypeReference< List<FamousUser> >(){}
     * @return 反序列化后的对象
     */
    public static <T> T toPojo(String jsonStr, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(jsonStr, typeReference);
        } catch (Exception e) {
            logger.error("- parse json pojo error,json string:{}\n class type:{}",jsonStr,typeReference);
            throw new RuntimeException("parse json pojo error",e);
        }
    }

    /**
     * JSON串转换为Java泛型对象，可以是各种类型，且泛型可嵌套
     * <p>
     * Created by zhengjingguo on 2017/8/25.
     *
     * @param <T>     泛型
     * @param jsonStr JSON字符串
     * @param
     * @return 反序列化后的对象
     */
    public static <T> T toPojo(String jsonStr, Class outer, Class inner) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(outer, inner);
            return objectMapper.readValue(jsonStr, javaType);
        } catch (Exception e) {
            logger.error("- parse json pojo error,json string:{}\n class outer:{},class inner",jsonStr,outer,inner);
            throw new RuntimeException("parse json pojo with class type error",e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> json2map(String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, Map.class);
        } catch (Exception e) {
            logger.error("- parse json pojo error,json string:{}",jsonStr);
            throw new RuntimeException("parse json map error",e);
        }
    }

    @SuppressWarnings("rawtypes")
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    @SuppressWarnings("rawtypes")
    public static <T> T map2pojo(Map map, TypeReference<T> typeReference) {
        return objectMapper.convertValue(map, typeReference);
    }

}