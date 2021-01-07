package org.jeecg.modules.bigscreen.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "分页描述对象")
@Data
public class BasePageVo {
    @ApiModelProperty(value = "分页号", position = 0,required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "分页大小", position = 1,required = true)
    private Integer pageSize;
}
