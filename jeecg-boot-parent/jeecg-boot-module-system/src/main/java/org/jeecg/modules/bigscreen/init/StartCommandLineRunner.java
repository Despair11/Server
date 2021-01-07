package org.jeecg.modules.bigscreen.init;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.bigscreen.bean.PropertyBean;
import org.jeecg.modules.bigscreen.util.ThreadPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class StartCommandLineRunner implements CommandLineRunner {
    @Autowired
    private PropertyBean propertyBean;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("StartCommandLineRunner...run..." + Arrays.asList(args));
        try {
//            propertyBean.initConfig();
            ThreadPoolUtil.getInstance().init(propertyBean.getThreadNum());
        } catch (Exception e) {
            log.error("error", e);
        }
    }
}
