package org.jeecg.modules.xjdatascreen.datapackageticketorder.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.xjdatascreen.datapackageticketorder.entity.PackageTicketOrder;
import org.jeecg.modules.xjdatascreen.datapackageticketorder.entity.PackageTicketOrderVO;
import org.jeecg.modules.xjdatascreen.datapackageticketorder.service.IPackageTicketOrderService;

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
 * @Description: package_ticket_order
 * @Author: jeecg-boot
 * @Date:   2020-11-24
 * @Version: V1.0
 */
@Api(tags="package_ticket_order_新增套票订单管理")
@RestController
@RequestMapping("/datapackageticketorder/packageTicketOrder")
@Slf4j
public class PackageTicketOrderController extends JeecgController<PackageTicketOrder, IPackageTicketOrderService> {
	@Autowired
	private IPackageTicketOrderService packageTicketOrderService;
	
//	/**
//	 * 分页列表查询
//	 *
//	 * @param packageTicketOrder
//	 * @param pageNo
//	 * @param pageSize
//	 * @param req
//	 * @return
//	 */
//	@AutoLog(value = "package_ticket_order-分页列表查询")
//	@ApiOperation(value="package_ticket_order-分页列表查询", notes="package_ticket_order-分页列表查询")
//	@GetMapping(value = "/list")
//	public Result<?> queryPageList(PackageTicketOrder packageTicketOrder,
//								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//								   HttpServletRequest req) {
//		QueryWrapper<PackageTicketOrder> queryWrapper = QueryGenerator.initQueryWrapper(packageTicketOrder, req.getParameterMap());
//		Page<PackageTicketOrder> page = new Page<PackageTicketOrder>(pageNo, pageSize);
//		IPage<PackageTicketOrder> pageList = packageTicketOrderService.page(page, queryWrapper);
//		return Result.ok(pageList);
//	}
//
//	/**
//	 *   添加
//	 *
//	 * @param packageTicketOrder
//	 * @return
//	 */
//	@AutoLog(value = "package_ticket_order-添加")
//	@ApiOperation(value="package_ticket_order-添加", notes="package_ticket_order-添加")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody PackageTicketOrder packageTicketOrder) {
//		packageTicketOrderService.save(packageTicketOrder);
//		return Result.ok("添加成功！");
//	}
//
//	/**
//	 *  编辑
//	 *
//	 * @param packageTicketOrder
//	 * @return
//	 */
//	@AutoLog(value = "package_ticket_order-编辑")
//	@ApiOperation(value="package_ticket_order-编辑", notes="package_ticket_order-编辑")
//	@PutMapping(value = "/edit")
//	public Result<?> edit(@RequestBody PackageTicketOrder packageTicketOrder) {
//		packageTicketOrderService.updateById(packageTicketOrder);
//		return Result.ok("编辑成功!");
//	}
//
//	/**
//	 *   通过id删除
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "package_ticket_order-通过id删除")
//	@ApiOperation(value="package_ticket_order-通过id删除", notes="package_ticket_order-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
//		packageTicketOrderService.removeById(id);
//		return Result.ok("删除成功!");
//	}
//
//	/**
//	 *  批量删除
//	 *
//	 * @param ids
//	 * @return
//	 */
//	@AutoLog(value = "package_ticket_order-批量删除")
//	@ApiOperation(value="package_ticket_order-批量删除", notes="package_ticket_order-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.packageTicketOrderService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.ok("批量删除成功!");
//	}
//
//	/**
//	 * 通过id查询
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "package_ticket_order-通过id查询")
//	@ApiOperation(value="package_ticket_order-通过id查询", notes="package_ticket_order-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
//		PackageTicketOrder packageTicketOrder = packageTicketOrderService.getById(id);
//		if(packageTicketOrder==null) {
//			return Result.error("未找到对应数据");
//		}
//		return Result.ok(packageTicketOrder);
//	}
//
//    /**
//    * 导出excel
//    *
//    * @param request
//    * @param packageTicketOrder
//    */
//    @RequestMapping(value = "/exportXls")
//    public ModelAndView exportXls(HttpServletRequest request, PackageTicketOrder packageTicketOrder) {
//        return super.exportXls(request, packageTicketOrder, PackageTicketOrder.class, "package_ticket_order");
//    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @ApiOperation(value = "套票的导入", notes = "套票的导入")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, PackageTicketOrder.class);
    }

    @ApiOperation(value = "各个景区的数据查询", notes = "各个景区的数据查询")
	@GetMapping("/getScenicData")
	public Result<?> getScenicData() {
		List<PackageTicketOrderVO> packageTicketOrderVOS = packageTicketOrderService.selectAll();
		return Result.ok(packageTicketOrderVOS);
	}

}
