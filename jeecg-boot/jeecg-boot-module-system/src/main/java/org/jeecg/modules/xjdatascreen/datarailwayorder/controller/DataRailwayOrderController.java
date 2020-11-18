package org.jeecg.modules.xjdatascreen.datarailwayorder.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.xjdatascreen.datarailwayorder.entity.DataRailwayOrder;
import org.jeecg.modules.xjdatascreen.datarailwayorder.service.IDataRailwayOrderService;

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
 * @Description: data_railway_order
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Api(tags="data_railway_order_线路订单管理")
@RestController
@RequestMapping("/datarailwayorder/dataRailwayOrder")
@Slf4j
public class DataRailwayOrderController extends JeecgController<DataRailwayOrder, IDataRailwayOrderService> {
	@Autowired
	private IDataRailwayOrderService dataRailwayOrderService;
	
//	/**
//	 * 分页列表查询
//	 *
//	 * @param dataRailwayOrder
//	 * @param pageNo
//	 * @param pageSize
//	 * @param req
//	 * @return
//	 */
//	@AutoLog(value = "data_railway_order-分页列表查询")
//	@ApiOperation(value="data_railway_order-分页列表查询", notes="data_railway_order-分页列表查询")
//	@GetMapping(value = "/list")
//	public Result<?> queryPageList(DataRailwayOrder dataRailwayOrder,
//								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//								   HttpServletRequest req) {
//		QueryWrapper<DataRailwayOrder> queryWrapper = QueryGenerator.initQueryWrapper(dataRailwayOrder, req.getParameterMap());
//		Page<DataRailwayOrder> page = new Page<DataRailwayOrder>(pageNo, pageSize);
//		IPage<DataRailwayOrder> pageList = dataRailwayOrderService.page(page, queryWrapper);
//		return Result.ok(pageList);
//	}
//
//	/**
//	 *   添加
//	 *
//	 * @param dataRailwayOrder
//	 * @return
//	 */
//	@AutoLog(value = "data_railway_order-添加")
//	@ApiOperation(value="data_railway_order-添加", notes="data_railway_order-添加")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody DataRailwayOrder dataRailwayOrder) {
//		dataRailwayOrderService.save(dataRailwayOrder);
//		return Result.ok("添加成功！");
//	}
//
//	/**
//	 *  编辑
//	 *
//	 * @param dataRailwayOrder
//	 * @return
//	 */
//	@AutoLog(value = "data_railway_order-编辑")
//	@ApiOperation(value="data_railway_order-编辑", notes="data_railway_order-编辑")
//	@PutMapping(value = "/edit")
//	public Result<?> edit(@RequestBody DataRailwayOrder dataRailwayOrder) {
//		dataRailwayOrderService.updateById(dataRailwayOrder);
//		return Result.ok("编辑成功!");
//	}
//
//	/**
//	 *   通过id删除
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_railway_order-通过id删除")
//	@ApiOperation(value="data_railway_order-通过id删除", notes="data_railway_order-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
//		dataRailwayOrderService.removeById(id);
//		return Result.ok("删除成功!");
//	}
//
//	/**
//	 *  批量删除
//	 *
//	 * @param ids
//	 * @return
//	 */
//	@AutoLog(value = "data_railway_order-批量删除")
//	@ApiOperation(value="data_railway_order-批量删除", notes="data_railway_order-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.dataRailwayOrderService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.ok("批量删除成功!");
//	}
//
//	/**
//	 * 通过id查询
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_railway_order-通过id查询")
//	@ApiOperation(value="data_railway_order-通过id查询", notes="data_railway_order-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
//		DataRailwayOrder dataRailwayOrder = dataRailwayOrderService.getById(id);
//		if(dataRailwayOrder==null) {
//			return Result.error("未找到对应数据");
//		}
//		return Result.ok(dataRailwayOrder);
//	}
//
//    /**
//    * 导出excel
//    *
//    * @param request
//    * @param dataRailwayOrder
//    */
//    @RequestMapping(value = "/exportXls")
//    public ModelAndView exportXls(HttpServletRequest request, DataRailwayOrder dataRailwayOrder) {
//        return super.exportXls(request, dataRailwayOrder, DataRailwayOrder.class, "data_railway_order");
//    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @ApiOperation(value = "线路数据导入", notes = "线路数据导入")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, DataRailwayOrder.class);
    }

}
