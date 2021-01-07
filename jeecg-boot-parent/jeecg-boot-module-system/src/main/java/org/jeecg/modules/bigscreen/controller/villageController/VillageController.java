package org.jeecg.modules.bigscreen.controller.villageController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.bigscreen.dto.BaseResponse;
import org.jeecg.modules.bigscreen.dto.villageTask.VillageGlory;
import org.jeecg.modules.bigscreen.dto.villageTask.VillagePopulation;
import org.jeecg.modules.bigscreen.dto.villageTask.VillagePopulationChange;
import org.jeecg.modules.bigscreen.service.villageTask.VillageTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description:村务管家
 * author: Zhang Yajun
 * date: 2021/1/6 14:48
 * version: 1.0
 */
@RestController
@RequestMapping("/village")
@Api(tags = "村务相关接口")
@Slf4j
public class VillageController {

    @Autowired
    VillageTaskService villageTaskService;

    @ApiOperation(value = "村务-人口查询", notes = "村务-人口查询")
    @GetMapping("/population")
    public BaseResponse<VillagePopulation> checkPopulation() {
        BaseResponse<VillagePopulation> baseResponse = new BaseResponse<>();
        baseResponse.setData(villageTaskService.checkPopulation());
        return baseResponse;
    }

    @ApiOperation(value = "村务-人口变化", notes = "村务-人口变化")
    @GetMapping("/populationChange")
    public BaseResponse<List<VillagePopulationChange>> checkPopulationChange() {
        BaseResponse<List<VillagePopulationChange>> baseResponse = new BaseResponse<>();
        baseResponse.setData(villageTaskService.checkPopulationChange());
        return baseResponse;
    }

    @ApiOperation(value = "村务-光荣榜", notes = "村务-光荣榜")
    @GetMapping("/gloryList")
    public BaseResponse<List<VillageGlory>> checkGlory() {
        BaseResponse<List<VillageGlory>> baseResponse = new BaseResponse<>();
        baseResponse.setData(villageTaskService.checkGlory());
        return baseResponse;
    }

}
