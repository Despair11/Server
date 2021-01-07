package org.jeecg.modules.bigscreen.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "主页展示的监控点信息")
@Data
public class MainCameraVo {
    @ApiModelProperty(value = "配置", position = 0,required = true)
    private VmsTreesVo.ConfigBean config;
    @ApiModelProperty(value = "监控点列表", position = 1,required = true)
    private List<VmsTreesVo.TreeDataBean> treeDataList;
}
