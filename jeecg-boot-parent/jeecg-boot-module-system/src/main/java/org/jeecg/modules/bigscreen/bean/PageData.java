package org.jeecg.modules.bigscreen.bean;

import lombok.Data;

@Data
public class PageData<T> extends ListData<T> {

    private Long pageNo;
    private Long pageSize;
    private Long totalPage;

}