package org.jeecg.modules.xjdatascreen.datawineshop.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.xjdatascreen.datawineshop.entity.DataWineShop;
import org.jeecg.modules.xjdatascreen.datawineshop.service.IDataWineShopService;

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
 * @Description: data_wine_shop
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Api(tags="data_wine_shop_酒店订单管理")
@RestController
@RequestMapping("/datawineshop/dataWineShop")
@Slf4j
public class DataWineShopController extends JeecgController<DataWineShop, IDataWineShopService> {
	@Autowired
	private IDataWineShopService dataWineShopService;
	
//	/**
//	 * 分页列表查询
//	 *
//	 * @param dataWineShop
//	 * @param pageNo
//	 * @param pageSize
//	 * @param req
//	 * @return
//	 */
//	@AutoLog(value = "data_wine_shop-分页列表查询")
//	@ApiOperation(value="data_wine_shop-分页列表查询", notes="data_wine_shop-分页列表查询")
//	@GetMapping(value = "/list")
//	public Result<?> queryPageList(DataWineShop dataWineShop,
//								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//								   HttpServletRequest req) {
//		QueryWrapper<DataWineShop> queryWrapper = QueryGenerator.initQueryWrapper(dataWineShop, req.getParameterMap());
//		Page<DataWineShop> page = new Page<DataWineShop>(pageNo, pageSize);
//		IPage<DataWineShop> pageList = dataWineShopService.page(page, queryWrapper);
//		return Result.ok(pageList);
//	}
//
//	/**
//	 *   添加
//	 *
//	 * @param dataWineShop
//	 * @return
//	 */
//	@AutoLog(value = "data_wine_shop-添加")
//	@ApiOperation(value="data_wine_shop-添加", notes="data_wine_shop-添加")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody DataWineShop dataWineShop) {
//		dataWineShopService.save(dataWineShop);
//		return Result.ok("添加成功！");
//	}
//
//	/**
//	 *  编辑
//	 *
//	 * @param dataWineShop
//	 * @return
//	 */
//	@AutoLog(value = "data_wine_shop-编辑")
//	@ApiOperation(value="data_wine_shop-编辑", notes="data_wine_shop-编辑")
//	@PutMapping(value = "/edit")
//	public Result<?> edit(@RequestBody DataWineShop dataWineShop) {
//		dataWineShopService.updateById(dataWineShop);
//		return Result.ok("编辑成功!");
//	}
//
//	/**
//	 *   通过id删除
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_wine_shop-通过id删除")
//	@ApiOperation(value="data_wine_shop-通过id删除", notes="data_wine_shop-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
//		dataWineShopService.removeById(id);
//		return Result.ok("删除成功!");
//	}
//
//	/**
//	 *  批量删除
//	 *
//	 * @param ids
//	 * @return
//	 */
//	@AutoLog(value = "data_wine_shop-批量删除")
//	@ApiOperation(value="data_wine_shop-批量删除", notes="data_wine_shop-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.dataWineShopService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.ok("批量删除成功!");
//	}
//
//	/**
//	 * 通过id查询
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_wine_shop-通过id查询")
//	@ApiOperation(value="data_wine_shop-通过id查询", notes="data_wine_shop-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
//		DataWineShop dataWineShop = dataWineShopService.getById(id);
//		if(dataWineShop==null) {
//			return Result.error("未找到对应数据");
//		}
//		return Result.ok(dataWineShop);
//	}
//
//    /**
//    * 导出excel
//    *
//    * @param request
//    * @param dataWineShop
//    */
//    @RequestMapping(value = "/exportXls")
//    public ModelAndView exportXls(HttpServletRequest request, DataWineShop dataWineShop) {
//        return super.exportXls(request, dataWineShop, DataWineShop.class, "data_wine_shop");
//    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @ApiOperation(value = "酒店数据导入", notes = "酒店数据导入")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, DataWineShop.class);
    }

}
