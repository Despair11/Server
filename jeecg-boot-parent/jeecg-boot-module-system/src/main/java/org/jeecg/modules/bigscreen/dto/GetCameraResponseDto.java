package org.jeecg.modules.bigscreen.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCameraResponseDto<T>{
    /**
     * cameraId : 1
     * indexcode :  16082611254372195927
     * name : test01
     * parentIndexCode :  0
     * cameraType : 0
     * pixel : 1
     * latitude : 11.2356
     * longitude : 14.4356
     * description : 测试01
     * isOnline : 1
     * controlUnitName : 杭州
     * decodeTag : hikvision
     * createTime : 1458715074229
     * updateTime : 1458715074229
     * extraField : extraFild
     */

    private String cameraId;
    private String indexcode;
    private String name;
    private String parentIndexCode;
    private String cameraType;
    private String pixel;
    private String latitude;
    private String longitude;
    private String description;
    private String isOnline;
    private String controlUnitName;
    private String decodeTag;
    private long createTime;
    private long updateTime;
    private String extraField;
}
