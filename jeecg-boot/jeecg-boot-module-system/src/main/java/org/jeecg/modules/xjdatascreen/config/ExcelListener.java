package org.jeecg.modules.xjdatascreen.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/18 15:55
 */
public class ExcelListener extends AnalysisEventListener {
    private List<Object> datas = new ArrayList<>();

    @Override
    public void invoke(Object obj, AnalysisContext context) {
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        datas.add(obj);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //解析结束销毁不用的资源
        //   datas.clear();
    }
    public List<Object> getDatas() {
        return datas;
    }
    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }
}
