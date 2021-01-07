package org.jeecg.modules.bigscreen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.bigscreen.dto.BaseResponse;
import org.jeecg.modules.bigscreen.exception.CustomException;
import org.jeecg.modules.bigscreen.service.DeviceService;
import org.jeecg.modules.bigscreen.vo.MainCameraVo;
import org.jeecg.modules.bigscreen.vo.VmsTreesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/device")
@Api(tags = "视频设备监控点接口")
@Slf4j
public class ApiController {

    @Autowired
    private DeviceService deviceService;

    @ApiOperation(value = "监控点区域树查询", notes = "监控点区域树查询")
    @GetMapping("/queryVmsTrees")
    public BaseResponse<VmsTreesVo> queryVmsTrees() {
        BaseResponse<VmsTreesVo> baseResponse = new BaseResponse<>();
        try {
            baseResponse.setData(deviceService.queryVmsTrees());
        } catch (CustomException e) {
            log.error("error ", e);
            baseResponse.setCode(e.getErrorCode());
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    @ApiOperation(value = "获取主页展示的监控点信息", notes = "获取主页展示的监控点信息")
    @GetMapping("/queryMainCamera")
    public BaseResponse<MainCameraVo> queryMainCamera() {
        BaseResponse<MainCameraVo> baseResponse = new BaseResponse<>();
        try {
            baseResponse.setData(deviceService.queryMainCamera());
        } catch (CustomException e) {
            log.error("error ", e);
            baseResponse.setCode(e.getErrorCode());
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    @ApiOperation(value = "获取28个摄像点",notes = "获取28个摄像点")
    @GetMapping("/queryThirtyCamera")
    public BaseResponse<MainCameraVo> queryThirtyCamera() throws CustomException {
        BaseResponse<MainCameraVo> baseResponse = new BaseResponse<>();
        baseResponse.setData(deviceService.queryThirtyCamera());
        return baseResponse;
    }
}
