package org.jeecg.modules.xjdatascreen.dataselfdrivingtour.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.xjdatascreen.dataselfdrivingtour.entity.DataSelfDrivingTour;
import org.jeecg.modules.xjdatascreen.dataselfdrivingtour.entity.DataSelfDrivingTourVO;
import org.jeecg.modules.xjdatascreen.dataselfdrivingtour.service.IDataSelfDrivingTourService;

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
 * @Description: data_self_driving_tour
 * @Author: jeecg-boot
 * @Date:   2020-11-26
 * @Version: V1.0
 */
@Api(tags="data_self_driving_tour_自驾游管理")
@RestController
@RequestMapping("/dataselfdrivingtour/dataSelfDrivingTour")
@Slf4j
public class DataSelfDrivingTourController extends JeecgController<DataSelfDrivingTour, IDataSelfDrivingTourService> {
	@Autowired
	private IDataSelfDrivingTourService dataSelfDrivingTourService;



	@ApiOperation(value = "获取大屏自驾游总数据", notes = "获取大屏自驾游总数据")
	@GetMapping("/getSelfDrivingAll")
	public Result<?> getSelfDrivingAll(String scenicName, String startTime, String endTime) {
		List<DataSelfDrivingTourVO> dataSelfDrivingTourVOS = dataSelfDrivingTourService.selectAll(scenicName, startTime, endTime);
		return Result.ok(dataSelfDrivingTourVOS);
	}
//	/**
//	 * 分页列表查询
//	 *
//	 * @param dataSelfDrivingTour
//	 * @param pageNo
//	 * @param pageSize
//	 * @param req
//	 * @return
//	 */
//	@AutoLog(value = "data_self_driving_tour-分页列表查询")
//	@ApiOperation(value="data_self_driving_tour-分页列表查询", notes="data_self_driving_tour-分页列表查询")
//	@GetMapping(value = "/list")
//	public Result<?> queryPageList(DataSelfDrivingTour dataSelfDrivingTour,
//								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//								   HttpServletRequest req) {
//		QueryWrapper<DataSelfDrivingTour> queryWrapper = QueryGenerator.initQueryWrapper(dataSelfDrivingTour, req.getParameterMap());
//		Page<DataSelfDrivingTour> page = new Page<DataSelfDrivingTour>(pageNo, pageSize);
//		IPage<DataSelfDrivingTour> pageList = dataSelfDrivingTourService.page(page, queryWrapper);
//		return Result.ok(pageList);
//	}
//
//	/**
//	 *   添加
//	 *
//	 * @param dataSelfDrivingTour
//	 * @return
//	 */
//	@AutoLog(value = "data_self_driving_tour-添加")
//	@ApiOperation(value="data_self_driving_tour-添加", notes="data_self_driving_tour-添加")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody DataSelfDrivingTour dataSelfDrivingTour) {
//		dataSelfDrivingTourService.save(dataSelfDrivingTour);
//		return Result.ok("添加成功！");
//	}
//
//	/**
//	 *  编辑
//	 *
//	 * @param dataSelfDrivingTour
//	 * @return
//	 */
//	@AutoLog(value = "data_self_driving_tour-编辑")
//	@ApiOperation(value="data_self_driving_tour-编辑", notes="data_self_driving_tour-编辑")
//	@PutMapping(value = "/edit")
//	public Result<?> edit(@RequestBody DataSelfDrivingTour dataSelfDrivingTour) {
//		dataSelfDrivingTourService.updateById(dataSelfDrivingTour);
//		return Result.ok("编辑成功!");
//	}
//
//	/**
//	 *   通过id删除
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_self_driving_tour-通过id删除")
//	@ApiOperation(value="data_self_driving_tour-通过id删除", notes="data_self_driving_tour-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
//		dataSelfDrivingTourService.removeById(id);
//		return Result.ok("删除成功!");
//	}
//
//	/**
//	 *  批量删除
//	 *
//	 * @param ids
//	 * @return
//	 */
//	@AutoLog(value = "data_self_driving_tour-批量删除")
//	@ApiOperation(value="data_self_driving_tour-批量删除", notes="data_self_driving_tour-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.dataSelfDrivingTourService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.ok("批量删除成功!");
//	}
//
//	/**
//	 * 通过id查询
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_self_driving_tour-通过id查询")
//	@ApiOperation(value="data_self_driving_tour-通过id查询", notes="data_self_driving_tour-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
//		DataSelfDrivingTour dataSelfDrivingTour = dataSelfDrivingTourService.getById(id);
//		if(dataSelfDrivingTour==null) {
//			return Result.error("未找到对应数据");
//		}
//		return Result.ok(dataSelfDrivingTour);
//	}
//
//    /**
//    * 导出excel
//    *
//    * @param request
//    * @param dataSelfDrivingTour
//    */
//    @RequestMapping(value = "/exportXls")
//    public ModelAndView exportXls(HttpServletRequest request, DataSelfDrivingTour dataSelfDrivingTour) {
//        return super.exportXls(request, dataSelfDrivingTour, DataSelfDrivingTour.class, "data_self_driving_tour");
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
//        return super.importExcel(request, response, DataSelfDrivingTour.class);
//    }

}
