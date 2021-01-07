package org.jeecg.modules.bigscreen.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.jeecg.modules.bigscreen.util.data.StarfishDateFormat;

import java.lang.annotation.Annotation;

/**
 *
 */
public class CustomObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;


    public CustomObjectMapper() {
    	super();
        // 排除值为空属性
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //转换成对象时，没有属性的处理，忽略掉
        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);


        // 进行缩进输出
        // configure(SerializationFeature.INDENT_OUTPUT, true);
        // 进行日期格式化
        setDateFormat(new StarfishDateFormat());
        //让SensitiveInfo注解失效，仅对LogObjectMapper生效
        setAnnotationIntrospector(new JacksonAnnotationIntrospector(){
            @Override
            public boolean isAnnotationBundle(Annotation ann) {
                return super.isAnnotationBundle(ann);
            }
        });

     // 提供其它默认设置
     /*	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
     		
     	objectMapper.setSerializationInclusion(Include.NON_NULL);
     		
     	objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));*/
    }
}