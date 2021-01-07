package org.jeecg.modules.bigscreen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.bigscreen.dto.BaseResponse;
import org.jeecg.modules.bigscreen.exception.CustomException;
import org.jeecg.modules.bigscreen.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonnController {
    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "获取天气json", notes = "获取天气json")
    @GetMapping("/queryWeatherJson")
    public BaseResponse<String> queryWeatherJson() {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        try {
            baseResponse.setData(commonService.getJsonFile());
        } catch (CustomException e) {
            log.error("error ", e);
            baseResponse.setCode(e.getErrorCode());
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }
}
