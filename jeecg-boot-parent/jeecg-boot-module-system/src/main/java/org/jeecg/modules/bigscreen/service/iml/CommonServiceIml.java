package org.jeecg.modules.bigscreen.service.iml;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.bigscreen.bean.PropertyBean;
import org.jeecg.modules.bigscreen.service.CommonService;
import org.jeecg.modules.bigscreen.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
@Slf4j
public class CommonServiceIml implements CommonService {

    @Autowired
    private PropertyBean propertyBean;

    @Override
    public String getJsonFile() {
        String jsonPath = propertyBean.getJsonPath();
        File jsonFile = new File(jsonPath);
        if(jsonFile.exists()){
            return IOUtils.getStringByFile(jsonFile);
        }else {
            return null;
        }
    }
}
