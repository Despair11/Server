package org.jeecg.modules.bigscreen.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "视频预览区域树信息描述类")
@Data
public class VmsTreesVo {
    /**
     * config : {"appkey":"22297842","secret":"6ooFPK9xoQrO733xFSOQ","serverIp":"10.1.219.39"}
     * treeDataList : [{"label":"民爆仓库","treeData":[{"name":"一级仓库 1","icon":"h-icon-menu_f","children":[{"name":"二级 1-1","icon":"h-icon-liveview","children":[{"name":"三级 1-1-1","icon":"h-icon-liveview","cameraIndexCode":"25296e883dfd494fb894f4622891611b"}]},{"name":"二级 2-1","icon":"h-icon-liveview","children":[{"name":"三级 2-1-1","icon":"h-icon-liveview","cameraIndexCode":"25296e883dfd494fb894f4622891611b"}]},{"name":"二级 2-2","icon":"h-icon-liveview","children":[{"name":"三级 2-2-1","icon":"h-icon-liveview","cameraIndexCode":"25296e883dfd494fb894f4622891611b"}]}]}]},{"label":"民爆车辆","treeData":[{"name":"一级车辆 1","icon":"h-icon-menu_f","children":[{"name":"二级 1-1","icon":"h-icon-liveview","children":[{"name":"三级 1-1-1","icon":"h-icon-liveview","cameraIndexCode":"25296e883dfd494fb894f4622891611b"}]},{"name":"二级 2-1","icon":"h-icon-liveview","children":[{"name":"三级 2-1-1","icon":"h-icon-liveview","cameraIndexCode":"25296e883dfd494fb894f4622891611b"}]},{"name":"二级 2-2","icon":"h-icon-liveview","children":[{"name":"三级 2-2-1","icon":"h-icon-liveview","cameraIndexCode":"25296e883dfd494fb894f4622891611b"}]}]}]},{"label":"爆破现场","treeData":[{"name":"一级现场 1","icon":"h-icon-menu_f","children":[{"name":"二级 1-1","icon":"h-icon-liveview","children":[{"name":"三级 1-1-1","icon":"h-icon-liveview","cameraIndexCode":"25296e883dfd494fb894f4622891611b"}]},{"name":"二级 2-1","icon":"h-icon-liveview","children":[{"name":"三级 2-1-1","icon":"h-icon-liveview","cameraIndexCode":"25296e883dfd494fb894f4622891611b"}]},{"name":"二级 2-2","icon":"h-icon-liveview","children":[{"name":"三级 2-2-1","icon":"h-icon-liveview","cameraIndexCode":"25296e883dfd494fb894f4622891611b"}]}]}]},{"label":"爆破人员","treeData":[{"name":"一级人员 1","icon":"h-icon-menu_f","children":[{"name":"二级 1-1","icon":"h-icon-liveview","children":[{"name":"三级 1-1-1","icon":"h-icon-liveview","cameraIndexCode":"25296e883dfd494fb894f4622891611b"}]},{"name":"二级 2-1","icon":"h-icon-liveview","children":[{"name":"三级 2-1-1","icon":"h-icon-liveview","cameraIndexCode":"25296e883dfd494fb894f4622891611b"}]},{"name":"二级 2-2","icon":"h-icon-liveview","children":[{"name":"三级 2-2-1","icon":"h-icon-liveview","cameraIndexCode":"25296e883dfd494fb894f4622891611b"}]}]}]}]
     */
    @ApiModelProperty(value = "配置", position = 0,required = true)
    private ConfigBean config;
    @ApiModelProperty(value = "区域树节点", position = 1,required = true)
    private List<TreeDataBean> treeDataList;

    @ApiModel(description = "配置信息描述类")
    @Data
    public static class ConfigBean {
        /**
         * appkey : 22297842
         * secret : 6ooFPK9xoQrO733xFSOQ
         * serverIp : 10.1.219.39
         */

        private String appkey;
        private String secret;
        private String serverIp;
        private Integer port;
    }

    @ApiModel(description = "区域树节点描述类")
    @Data
    public static class TreeDataBean {
        /**
         * name : 一级仓库 1
         * icon : h-icon-menu_f
         * children : [{"name":"二级 1-1","icon":"h-icon-liveview","children":[{"name":"三级 1-1-1","icon":"h-icon-liveview","cameraIndexCode":"25296e883dfd494fb894f4622891611b"}]},{"name":"二级 2-1","icon":"h-icon-liveview","children":[{"name":"三级 2-1-1","icon":"h-icon-liveview","cameraIndexCode":"25296e883dfd494fb894f4622891611b"}]},{"name":"二级 2-2","icon":"h-icon-liveview","children":[{"name":"三级 2-2-1","icon":"h-icon-liveview","cameraIndexCode":"25296e883dfd494fb894f4622891611b"}]}]
         */
        @ApiModelProperty(value = "名称", position = 0, required = true)
        private String name;
        @ApiModelProperty(value = "icon名", position = 1,required = true)
        private String icon;
        @ApiModelProperty(value = "区域名", position = 2,required = true)
        private String regionIndexCode;
        @ApiModelProperty(value = "监控点名", position = 3,required = true)
        private String cameraIndexCode;
        @ApiModelProperty(value = "资源类型", position = 3,required = true)
        private String resourceType;
        @ApiModelProperty(value = "子节点", position = 4,required = true)
        private List<TreeDataBean> children;
        @ApiModelProperty(value = "hls地址", position = 5,required = true)
        private String hlsUrl;
    }
}
