package org.jeecg.modules.bigscreen.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhaoSheng
 * @version 1.0
 * @date 2020-06-05 10:07
 */
@Slf4j
public class OpenApiUtil {

    public static String getOpenApiResult(String apiUrl,Map<String, String> querys,CustomArtemisHttpUtil customArtemisHttpUtil) {

        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        final String ARTEMIS_PATH = "/artemis";

        /**
         * STEP3：设置接口的URI地址 /artemis/api/common/v1/remoteCameraInfoRestService/findCameraInfoPage
         */
        final String httpUrl = ARTEMIS_PATH + apiUrl;
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("https://", httpUrl);//根据现场环境部署确认是http还是https
            }
        };

        /**
         * STEP4：设置参数提交方式
         */
        String contentType = "application/x-www-form-urlencoded";

        /**
         * STEP6：调用接口
         */
        String result = customArtemisHttpUtil.doGetArtemis(path, querys, null, contentType , null);// post请求application/json类型参数
        return result;
    }
    
    public static String postOpenApiResult(String apiUrl,String json,CustomArtemisHttpUtil customArtemisHttpUtil) {

        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        final String ARTEMIS_PATH = "/artemis";

        /**
         * STEP3：设置接口的URI地址
         */
        final String httpUrl = ARTEMIS_PATH + apiUrl;
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("https://", httpUrl);//根据现场环境部署确认是http还是https
            }
        };

        /**
         * STEP4：设置参数提交方式
         */
        String contentType = "application/json";

        /**
         * STEP6：调用接口
         */
        String result = customArtemisHttpUtil.doPostStringArtemis(path, json, null, null, contentType , null);// post请求application/json类型参数
        return result;
    }

    public static String getImageUrl(String apiUrl,String json,CustomArtemisHttpUtil customArtemisHttpUtil) {

        /**
         * STEP2：设置OpenAPI接口的上下文
         */
        final String ARTEMIS_PATH = "/artemis";

        /**
         * STEP3：设置接口的URI地址
         */
        final String httpUrl = ARTEMIS_PATH + apiUrl;
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("https://", httpUrl);//根据现场环境部署确认是http还是https
            }
        };

        /**
         * STEP4：设置参数提交方式
         */
        String contentType = "application/json";

        /**
         * STEP6：调用接口
         */
        HttpResponse httpResponse = customArtemisHttpUtil.doPostStringImgArtemis(path, json, null, null, contentType , null);// post请求application/json类型参数
        if(httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 302){
            String locationUrl = httpResponse.getFirstHeader("Location").getValue();
            return locationUrl;
        }
        return null;
    }

}
