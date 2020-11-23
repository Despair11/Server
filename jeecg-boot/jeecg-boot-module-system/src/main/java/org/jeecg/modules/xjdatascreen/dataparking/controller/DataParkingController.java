package org.jeecg.modules.xjdatascreen.dataparking.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.xjdatascreen.dataparking.entity.DataParking;
import org.jeecg.modules.xjdatascreen.dataparking.service.IDataParkingService;

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
 * @Description: data_parking
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Api(tags="data_parking_停车场管理")
@RestController
@RequestMapping("/dataparking/dataParking")
@Slf4j
public class DataParkingController extends JeecgController<DataParking, IDataParkingService> {
	@Autowired
	private IDataParkingService dataParkingService;
	
//	/**
//	 * 分页列表查询
//	 *
//	 * @param dataParking
//	 * @param pageNo
//	 * @param pageSize
//	 * @param req
//	 * @return
//	 */
//	@AutoLog(value = "data_parking-分页列表查询")
//	@ApiOperation(value="data_parking-分页列表查询", notes="data_parking-分页列表查询")
//	@GetMapping(value = "/list")
//	public Result<?> queryPageList(DataParking dataParking,
//								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//								   HttpServletRequest req) {
//		QueryWrapper<DataParking> queryWrapper = QueryGenerator.initQueryWrapper(dataParking, req.getParameterMap());
//		Page<DataParking> page = new Page<DataParking>(pageNo, pageSize);
//		IPage<DataParking> pageList = dataParkingService.page(page, queryWrapper);
//		return Result.ok(pageList);
//	}
//
//	/**
//	 *   添加
//	 *
//	 * @param dataParking
//	 * @return
//	 */
//	@AutoLog(value = "data_parking-添加")
//	@ApiOperation(value="data_parking-添加", notes="data_parking-添加")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody DataParking dataParking) {
//		dataParkingService.save(dataParking);
//		return Result.ok("添加成功！");
//	}
//
//	/**
//	 *  编辑
//	 *
//	 * @param dataParking
//	 * @return
//	 */
//	@AutoLog(value = "data_parking-编辑")
//	@ApiOperation(value="data_parking-编辑", notes="data_parking-编辑")
//	@PutMapping(value = "/edit")
//	public Result<?> edit(@RequestBody DataParking dataParking) {
//		dataParkingService.updateById(dataParking);
//		return Result.ok("编辑成功!");
//	}
//
//	/**
//	 *   通过id删除
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_parking-通过id删除")
//	@ApiOperation(value="data_parking-通过id删除", notes="data_parking-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
//		dataParkingService.removeById(id);
//		return Result.ok("删除成功!");
//	}
//
//	/**
//	 *  批量删除
//	 *
//	 * @param ids
//	 * @return
//	 */
//	@AutoLog(value = "data_parking-批量删除")
//	@ApiOperation(value="data_parking-批量删除", notes="data_parking-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.dataParkingService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.ok("批量删除成功!");
//	}
//
//	/**
//	 * 通过id查询
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_parking-通过id查询")
//	@ApiOperation(value="data_parking-通过id查询", notes="data_parking-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
//		DataParking dataParking = dataParkingService.getById(id);
//		if(dataParking==null) {
//			return Result.error("未找到对应数据");
//		}
//		return Result.ok(dataParking);
//	}
//
//    /**
//    * 导出excel
//    *
//    * @param request
//    * @param dataParking
//    */
//    @RequestMapping(value = "/exportXls")
//    public ModelAndView exportXls(HttpServletRequest request, DataParking dataParking) {
//        return super.exportXls(request, dataParking, DataParking.class, "data_parking");
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
//        return super.importExcel(request, response, DataParking.class);
//    }

}
