package org.jeecg.modules.bigscreen.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.bigscreen.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Component
@Data
@Slf4j
public class PropertyBean {

    @Value("${custom.hikIp}")
    private String hikIp;
    @Value("${custom.appKey}")
    private String appkey;
    @Value("${custom.secretKey}")
    private String appSecret;
    @Value("${custom.cronExpression:}")
    private String cronExpression;

    @Value("#{'${custom.mainCameraIndexCodes}'.split(',')}")
    private List<String> mainCameraIndexCodes;

    @Value("#{'${custom.thirtyCamera}'.split(',')}")
    private List<String> thirtyCamera;

    @Value("${custom.json.path:}")
    private String jsonPath;
    //=====================


    @Autowired
    private Environment environment;

    @Autowired
    private ResourceLoader resourceLoader;

    private Integer threadNum = 1;

    public void initConfig() {
        String fileSeparator = System.getProperty("file.separator");
        String confComponentPath = null;
        String configPath = confComponentPath + fileSeparator + "config.properties";
        configPath = CommonUtil.compile(configPath);
        log.info("configPath:{}", configPath);
        FileInputStream configFileInputStream = null;
        Properties props = null;
        try {
            configFileInputStream = new FileInputStream(configPath);
            props = new Properties();
            props.load(configFileInputStream);
        } catch (FileNotFoundException e) {
            log.error("configPath not exist", e);
        } catch (IOException e) {
            log.error("FileInputStream error", e);
        } finally {
            if (configFileInputStream != null) {
                try {
                    configFileInputStream.close();
                } catch (IOException e) {
                    log.error("error", e);
                }
            }
        }
    }
}
