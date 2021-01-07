package org.jeecg.modules.bigscreen.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "列表数据返回内容")
public class ListData<T> {

    @ApiModelProperty(value = "列表数据")
    private List<T> list;
    @ApiModelProperty(value = "列表数据总数")
    private Long total;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}