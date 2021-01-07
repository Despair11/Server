package org.jeecg.modules.bigscreen.constant;

public interface CommonConstant {

    String ROOT_ORG_ID = "0";

    /**
     * 分页获取全部的监控点信息
     * */
    String GET_CAMERA_LIST = "/api/common/v1/remoteCameraInfoRestService/findCameraInfoPage";

    /**
     * 分页获取全部的组织树
     * */
    String GET_ALL_ORG_LIST = "/api/common/v1/remoteControlUnitRestService/findControlUnitPage";

    /**
     * 根据监控点编号获取视频预览url
     * */
    String GET_CAMERA_PREVIEW_URL = "/api/video/v1/preview";

    interface ResourceType {
        /**
         * 编码设备
         */
        String DEVICE_ENCODE = "encodeDevice";
        String DEVICE_ACS = "acsDevice";

        /**
         * 可视对讲门口机
         */
        String DEVICE_VIS_DEVICE = "visDevice";

        /**
         * 可视对讲门口机
         */
        String ReourceType_VisDeviceOutDoor = "visDeviceOutDoor";

        /**
         * 可视对讲围墙机
         */
        String ReourceType_VisDeviceWallDoor = "visDeviceWallDoor";

        /**
         * 监控点
         */
        String ReourceType_Camera = "camera";
    }

    /**
     * 事件类别
     */
    interface EventType {
        long FACE_CAPTURE = 131614L;
        long IMPORTANT_EVENT = 1644175361L;
        long STRANGER_EVENT = 1644171265L;
    }

    interface SrcType {
        //编码设备
        String ENCODE_DEVICE = "encodeDevice";
        //监控点
        String CAMERA = "camera";
        //人脸分组
        String FACE_GROUP = "facegroup";
    }

    /**
     * 0-瞬时
     * 1-开始
     * 2-停止
     * 3-事件脉冲
     * 4-联动结果更新
     * 8-注释
     */
    interface EventStatus {
        int Status_0 = 0;
        int Status_1 = 1;
        int Status_2 = 2;
        int Status_3 = 3;
        int Status_4 = 4;
        int Status_8 = 8;
    }

    interface SysDictName {
        /**
         * asw存储图片资源池id
         */
        String PoolId = "PoolId";

        /**
         * 人脸照片存储位置
         */
        String FaceStaticPoolId = "faceStaticPoolId";

        /**
         * 重点人记录保存时间
         */
        String ImportantSaveTime = "importantSaveTime";

        /**
         * 陌生人记录保存时间
         */
        String StrangerSaveTime = "strangerSaveTime";

        /**
         * 抓拍记录保存时间
         */
        String CaptureSaveTime = "captureSaveTime";

        /**
         * 地图瓦片url
         */
        String MapUrl = "mapUrl";
        /**
         * 地图瓦片url
         */
        String CenterLongitude = "centerLongitude";
        /**
         * 地图瓦片url
         */
        String CenterLatitude = "centerLatitude";
        /**
         * 地图瓦片url
         */
        String MapType = "mapType";
        /**
         * 地图瓦片url
         */
        String minLongitude = "minLongitude";
        /**
         * 地图瓦片url
         */
        String minLatitude = "minLatitude";
        /**
         * 地图瓦片url
         */
        String maxLongitude = "maxLongitude";
        /**
         * 地图瓦片url
         */
        String maxLatitude = "maxLatitude";
        /**
         * 地图瓦片url
         */
        String zoom = "zoom";
        /**
         * 地图瓦片url
         */
        String minLevel = "minLevel";
        /**
         * 地图瓦片url
         */
        String maxLevel = "maxLevel";
        /**
         * 地图瓦片url
         */
        String resolutions = "resolutions";
    }
}
