package org.jeecg.modules.bigscreen.controller.housecontroller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.bigscreen.dto.BaseResponse;
import org.jeecg.modules.bigscreen.dto.house.*;
import org.jeecg.modules.bigscreen.service.house.HouseEarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description:芦茨民宿Controller
 * author: Zhang Yajun
 * date: 2021/1/5 9:25
 * version: 1.0
 */
@RestController
@RequestMapping("/house")
@Api(tags = "民宿相关接口")
@Slf4j
public class HomeStayController {

    @Autowired
    HouseEarnService houseService;

    @ApiOperation(value = "民宿首页收益查询", notes = "民俗收益查询")
    @GetMapping("/mainEarning")
    public BaseResponse<LuciHouseEarn> earning() {
        BaseResponse<LuciHouseEarn> baseResponse = new BaseResponse<>();
        baseResponse.setData(houseService.earning());
        return baseResponse;
    }

    @ApiOperation(value = "民宿房间查询",notes = "民宿房间查询")
    @GetMapping("/houseCount")
    public BaseResponse<RoomSum> housing(){
        BaseResponse<RoomSum> baseResponse = new BaseResponse<>();
        baseResponse.setData(houseService.houseCount());
        return baseResponse;
    }

    @ApiOperation(value = "入住数据查询",notes = "入住数据查询")
    @GetMapping("/checkIn")
    public BaseResponse<CheckInfo> checkIn(){
        BaseResponse<CheckInfo> baseResponse = new BaseResponse<>();
        baseResponse.setData(houseService.checkIn());
        return baseResponse;
    }

    @ApiOperation(value = "入住数据变化",notes = "入住数据变化")
    @GetMapping("/checkInChange")
    public BaseResponse<List<CheckInfoChange>> checkInChange(){
        BaseResponse<List<CheckInfoChange>> baseResponse = new BaseResponse<>();
        baseResponse.setData(houseService.checkInChange());
        return baseResponse;
    }

    @ApiOperation(value = "民宿收益数据变化",notes = "民宿收益数据变化")
    @GetMapping("/houseEarnChange")
    public BaseResponse<List<HouseEarnChange>> houseEarn(){
        BaseResponse<List<HouseEarnChange>> baseResponse = new BaseResponse<>();
        baseResponse.setData(houseService.houseEarnChange());
        return baseResponse;
    }

    @ApiOperation(value = "游客模块查询",notes = "游客模块查询")
    @GetMapping("/tourist")
    public BaseResponse<Tourist> checkTourist(){
        BaseResponse<Tourist> baseResponse = new BaseResponse<>();
        baseResponse.setData(houseService.checkTourist());
        return baseResponse;
    }

    @ApiOperation(value = "行业对比查询-按人均",notes = "行业对比查询-按人均")
    @GetMapping("/industryByPrice")
    public BaseResponse<List<Agritainment>> checkAllHouseByPrice(){
        BaseResponse<List<Agritainment>> listBaseResponse = new BaseResponse<>();
        listBaseResponse.setData(houseService.findAllByPrice());
        return listBaseResponse;
    }

    @ApiOperation(value = "民俗名气榜-按入住率",notes = "民俗名气榜-按入住率")
    @GetMapping("/industryByCheckIn")
    public BaseResponse<List<Agritainment>> checkAllHouseByCheckIn(){
        BaseResponse<List<Agritainment>> listBaseResponse = new BaseResponse<>();
        listBaseResponse.setData(houseService.findAllByCheckIn());
        return listBaseResponse;
    }

}
