package org.jeecg.modules.xjdatascreen.datagoods.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.xjdatascreen.datagoods.entity.DataGoods;
import org.jeecg.modules.xjdatascreen.datagoods.service.IDataGoodsService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: data_goods
 * @Author: jeecg-boot
 * @Date:   2020-11-17
 * @Version: V1.0
 */
@Api(tags="data_goods_商品订单管理")
@RestController
@RequestMapping("/datagoods/dataGoods")
@Slf4j
public class DataGoodsController extends JeecgController<DataGoods, IDataGoodsService> {
	@Autowired
	private IDataGoodsService dataGoodsService;
	
//	/**
//	 * 分页列表查询
//	 *
//	 * @param dataGoods
//	 * @param pageNo
//	 * @param pageSize
//	 * @param req
//	 * @return
//	 */
//	@AutoLog(value = "data_goods-分页列表查询")
//	@ApiOperation(value="data_goods-分页列表查询", notes="data_goods-分页列表查询")
//	@GetMapping(value = "/list")
//	public Result<?> queryPageList(DataGoods dataGoods,
//								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//								   HttpServletRequest req) {
//		QueryWrapper<DataGoods> queryWrapper = QueryGenerator.initQueryWrapper(dataGoods, req.getParameterMap());
//		Page<DataGoods> page = new Page<DataGoods>(pageNo, pageSize);
//		IPage<DataGoods> pageList = dataGoodsService.page(page, queryWrapper);
//		return Result.ok(pageList);
//	}
//
//	/**
//	 *   添加
//	 *
//	 * @param dataGoods
//	 * @return
//	 */
//	@AutoLog(value = "data_goods-添加")
//	@ApiOperation(value="data_goods-添加", notes="data_goods-添加")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody DataGoods dataGoods) {
//		dataGoodsService.save(dataGoods);
//		return Result.ok("添加成功！");
//	}
//
//	/**
//	 *  编辑
//	 *
//	 * @param dataGoods
//	 * @return
//	 */
//	@AutoLog(value = "data_goods-编辑")
//	@ApiOperation(value="data_goods-编辑", notes="data_goods-编辑")
//	@PutMapping(value = "/edit")
//	public Result<?> edit(@RequestBody DataGoods dataGoods) {
//		dataGoodsService.updateById(dataGoods);
//		return Result.ok("编辑成功!");
//	}
//
//	/**
//	 *   通过id删除
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_goods-通过id删除")
//	@ApiOperation(value="data_goods-通过id删除", notes="data_goods-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
//		dataGoodsService.removeById(id);
//		return Result.ok("删除成功!");
//	}
//
//	/**
//	 *  批量删除
//	 *
//	 * @param ids
//	 * @return
//	 */
//	@AutoLog(value = "data_goods-批量删除")
//	@ApiOperation(value="data_goods-批量删除", notes="data_goods-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.dataGoodsService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.ok("批量删除成功!");
//	}
//
//	/**
//	 * 通过id查询
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_goods-通过id查询")
//	@ApiOperation(value="data_goods-通过id查询", notes="data_goods-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
//		DataGoods dataGoods = dataGoodsService.getById(id);
//		if(dataGoods==null) {
//			return Result.error("未找到对应数据");
//		}
//		return Result.ok(dataGoods);
//	}
//
//    /**
//    * 导出excel
//    *
//    * @param request
//    * @param dataGoods
//    */
//    @RequestMapping(value = "/exportXls")
//    public ModelAndView exportXls(HttpServletRequest request, DataGoods dataGoods) {
//        return super.exportXls(request, dataGoods, DataGoods.class, "data_goods");
//    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @ApiOperation(value = "商品数据导入", notes = "商品数据导入")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, DataGoods.class);
    }



}
