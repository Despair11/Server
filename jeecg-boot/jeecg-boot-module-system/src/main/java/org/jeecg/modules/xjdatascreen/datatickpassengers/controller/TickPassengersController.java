package org.jeecg.modules.xjdatascreen.datatickpassengers.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.xjdatascreen.datatickpassengers.entity.TickPassengers;
import org.jeecg.modules.xjdatascreen.datatickpassengers.entity.TickPassengersVO;
import org.jeecg.modules.xjdatascreen.datatickpassengers.service.ITickPassengersService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: tick_passengers
 * @Author: jeecg-boot
 * @Date:   2020-11-24
 * @Version: V1.0
 */
@Api(tags="tick_passengers")
@RestController
@RequestMapping("/datatickpassengers/tickPassengers")
@Slf4j
public class TickPassengersController extends JeecgController<TickPassengers, ITickPassengersService> {
	@Autowired
	private ITickPassengersService tickPassengersService;

	@ApiOperation(value = "获取各景区游客年龄和性别", notes = "获取各景区游客年龄和性别")
	 @GetMapping("/getAgeAndSexData")
	 public Result<?> getAgeAndSexData(String scenicName, String startTime, String endTim) {
		List<TickPassengersVO> tickPassengersVOS = tickPassengersService.selectAll(scenicName, startTime, endTim);
		return Result.ok(tickPassengersVOS);
	}
//
//	/**
//	 * 分页列表查询
//	 *
//	 * @param tickPassengers
//	 * @param pageNo
//	 * @param pageSize
//	 * @param req
//	 * @return
//	 */
//	@AutoLog(value = "tick_passengers-分页列表查询")
//	@ApiOperation(value="tick_passengers-分页列表查询", notes="tick_passengers-分页列表查询")
//	@GetMapping(value = "/list")
//	public Result<?> queryPageList(TickPassengers tickPassengers,
//								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//								   HttpServletRequest req) {
//		QueryWrapper<TickPassengers> queryWrapper = QueryGenerator.initQueryWrapper(tickPassengers, req.getParameterMap());
//		Page<TickPassengers> page = new Page<TickPassengers>(pageNo, pageSize);
//		IPage<TickPassengers> pageList = tickPassengersService.page(page, queryWrapper);
//		return Result.ok(pageList);
//	}
//
//	/**
//	 *   添加
//	 *
//	 * @param tickPassengers
//	 * @return
//	 */
//	@AutoLog(value = "tick_passengers-添加")
//	@ApiOperation(value="tick_passengers-添加", notes="tick_passengers-添加")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody TickPassengers tickPassengers) {
//		tickPassengersService.save(tickPassengers);
//		return Result.ok("添加成功！");
//	}
//
//	/**
//	 *  编辑
//	 *
//	 * @param tickPassengers
//	 * @return
//	 */
//	@AutoLog(value = "tick_passengers-编辑")
//	@ApiOperation(value="tick_passengers-编辑", notes="tick_passengers-编辑")
//	@PutMapping(value = "/edit")
//	public Result<?> edit(@RequestBody TickPassengers tickPassengers) {
//		tickPassengersService.updateById(tickPassengers);
//		return Result.ok("编辑成功!");
//	}
//
//	/**
//	 *   通过id删除
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "tick_passengers-通过id删除")
//	@ApiOperation(value="tick_passengers-通过id删除", notes="tick_passengers-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
//		tickPassengersService.removeById(id);
//		return Result.ok("删除成功!");
//	}
//
//	/**
//	 *  批量删除
//	 *
//	 * @param ids
//	 * @return
//	 */
//	@AutoLog(value = "tick_passengers-批量删除")
//	@ApiOperation(value="tick_passengers-批量删除", notes="tick_passengers-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.tickPassengersService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.ok("批量删除成功!");
//	}
//
//	/**
//	 * 通过id查询
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "tick_passengers-通过id查询")
//	@ApiOperation(value="tick_passengers-通过id查询", notes="tick_passengers-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
//		TickPassengers tickPassengers = tickPassengersService.getById(id);
//		if(tickPassengers==null) {
//			return Result.error("未找到对应数据");
//		}
//		return Result.ok(tickPassengers);
//	}
//
//    /**
//    * 导出excel
//    *
//    * @param request
//    * @param tickPassengers
//    */
//    @RequestMapping(value = "/exportXls")
//    public ModelAndView exportXls(HttpServletRequest request, TickPassengers tickPassengers) {
//        return super.exportXls(request, tickPassengers, TickPassengers.class, "tick_passengers");
//    }
//
//    /**
//      * 通过excel导入数据
//    *
//    * @param request
//    * @param response
//    * @return
//    */
//    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
//    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//        return super.importExcel(request, response, TickPassengers.class);
//    }

}
