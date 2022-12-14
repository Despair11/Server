package org.jeecg.modules.bigscreen.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import org.jeecg.modules.bigscreen.util.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ArtemisPostUtil {
    private static ArtemisPostUtil artemisPostUtil;

    private static final Logger log = LoggerFactory.getLogger(ArtemisPostUtil.class);
    /**
     * 能力开放平台的网站路径
     * TODO 路径不用修改，就是/artemis
     */
    private static final String ARTEMIS_PATH = "/artemis";

    /**
     * 请根据自己的appKey和appSecret更换static静态块中的三个参数. [1 host]
     * 如果你选择的是和现场环境对接,host要修改为现场环境的ip,https端口默认为443，http端口默认为80.例如10.33.25.22:443 或者10.33.25.22:80
     * appKey和appSecret请按照或得到的appKey和appSecret更改.
     * TODO 调用前先要清楚接口传入的是什么，是传入json就用doPostStringArtemis方法，下载图片doPostStringImgArtemis方法
     */

    public static ArtemisPostUtil getInstance() {
        synchronized (ArtemisPostUtil.class) {
            if (artemisPostUtil == null) {
                artemisPostUtil = new ArtemisPostUtil();
            }
            return artemisPostUtil;
        }

    }

    public static void initHostAndKey(String host, String appKey, String appSecret) {
        ArtemisConfig.host = host;// 代理API网关nginx服务器ip端口
        ArtemisConfig.appKey = appKey;// 秘钥appkey
        ArtemisConfig.appSecret = appSecret;// 秘钥appSecret
    }


    /**
     * 调用POST请求类型(application/json)接口，这里以入侵报警事件日志为例
     * https://open.hikvision.com/docs/918519baf9904844a2b608e558b21bb6#e6798840
     *
     * @return
     */
    public <T> T callPostStringApi(String relativePath, String bodyJson, Map<String, String> header, TypeReference<T> type) {
        /**
         * http://10.33.47.50/artemis/api/scpms/v1/eventLogs/searches
         * 根据API文档可以看出来，这是一个POST请求的Rest接口，而且传入的参数值为一个json
         * ArtemisHttpUtil工具类提供了doPostStringArtemis这个函数，一共六个参数在文档里写明其中的意思，因为接口是https，
         * 所以第一个参数path是一个hashmap类型，请put一个key-value，query为传入的参数，body为传入的json数据
         * 传入的contentType为application/json，accept不指定为null
         * header没有额外参数可不传,指定为null
         *
         */

        final String getCamsApi = ARTEMIS_PATH + relativePath;
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                put("https://", getCamsApi);//根据现场环境部署确认是http还是https
            }
        };

        String result = ArtemisHttpUtil.doPostStringArtemis(path, bodyJson, null, null, "application/json", header);// post请求application/json类型参数

        T resultTemplate = null;
        resultTemplate = JsonUtil.toPojo(result, type);

        return resultTemplate;
    }

    /**
     * 调用POST请求下载图片类型接口，这里以获取访客记录中的图片接口为例
     * https://open.hikvision.com/docs/a0a1a0a24701a00aa904f7b151f97410#f11f3208
     *
     * @return
     */
//    public static void callPostImgStringApi() {
//        /**
//         * http://10.33.47.50/api/visitor/v1/record/pictures
//         * 根据API文档可以看出来，这是一个POST请求的Rest接口，而且传入的参数值为一个json
//         * ArtemisHttpUtil工具类提供了doPostStringImgArtemis这个函数，一共六个参数在文档里写明其中的意思，因为接口是https，
//         * 所以第一个参数path是一个hashmap类型，请put一个key-value，query为传入的参数，body为传入的json数据
//         * 传入的contentType为application/json，accept不指定为null
//         * header没有额外参数可不传,指定为null
//         *
//         */
//        final String VechicleDataApi = ARTEMIS_PATH + "/api/visitor/v1/record/pictures";
//        Map<String, String> path = new HashMap<String, String>(2) {
//            {
//                put("http://", VechicleDataApi);
//            }
//        };
//
//        JSONObject jsonBody = new JSONObject();
//        jsonBody.put("svrIndexCode", "8907fd9d-d090-43d3-bb3a-3a4b10dd7219");
//        jsonBody.put("picUri", "/pic?0dd453i3c-e*046456451175m6ep=t=i2p*i=d1s*i3d0d*=*1b8i81f4747059503--bdf90a-855s5721z3b9i=1=");
//        String body = jsonBody.toJSONString();
//        System.out.println("body: " + body);
//        HttpResponse result = ArtemisHttpUtil.doPostStringImgArtemis(path, body, null, null, "application/json", null);
//        try {
//            HttpResponse resp = result;
//            if (200 == resp.getStatusLine().getStatusCode()) {
//                HttpEntity entity = resp.getEntity();
//                InputStream in = entity.getContent();
//                Tools.savePicToDisk(in, "d:/", "test4.jpg");
//                System.out.println("下载成功");
//            } else {
//                System.out.println("下载出错");
//            }
//
//        } catch (Exception e) {
//            log.info(HikLog.toLog("download error"), e);
//        }
//    }
}
