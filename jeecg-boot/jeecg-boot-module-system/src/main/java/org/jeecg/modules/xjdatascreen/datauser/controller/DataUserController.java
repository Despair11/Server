package org.jeecg.modules.xjdatascreen.datauser.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.xjdatascreen.datauser.entity.DataUser;
import org.jeecg.modules.xjdatascreen.datauser.service.IDataUserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.xjdatascreen.utils.WeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: data_user
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Api(tags="data_user_用户管理")
@RestController
@RequestMapping("/datauser/dataUser")
@Slf4j
public class DataUserController extends JeecgController<DataUser, IDataUserService> {
	@Autowired
	private IDataUserService dataUserService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dataUser
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "后台用户管理分页列表查询")
	@ApiOperation(value="后台用户管理分页列表查询", notes="后台用户管理分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DataUser dataUser,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
//		QueryWrapper<DataUser> queryWrapper = QueryGenerator.initQueryWrapper(dataUser, req.getParameterMap());
		QueryWrapper<DataUser> dataUserQueryWrapper = new QueryWrapper<>();
		dataUserQueryWrapper.like("name", dataUser.getName());
		Page<DataUser> page = new Page<DataUser>(pageNo, pageSize);
		IPage<DataUser> pageList = dataUserService.page(page, dataUserQueryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param dataUser
	 * @return
	 */
	@AutoLog(value = "后台用户添加")
	@ApiOperation(value="后台用户添加", notes="后台用户添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DataUser dataUser) {
		dataUserService.save(dataUser);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param dataUser
	 * @return
	 */
	@AutoLog(value = "后台用户编辑")
	@ApiOperation(value="后台用户编辑", notes="后台用户编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DataUser dataUser) {
		dataUserService.updateById(dataUser);
		return Result.ok("编辑成功!");
	}


	 @ApiOperation(value = "数据大屏获取天气", notes = "数据大屏获取天气")
	 @GetMapping("/getWeather")
	 public Result<?> getWeather() throws IOException {
		 Map<String, Object> weather = WeatherUtil.getTodayWeather1("101131201");
		 return Result.ok(weather);
	 }



//	/**
//	 *   通过id删除
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_user-通过id删除")
//	@ApiOperation(value="data_user-通过id删除", notes="data_user-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
//		dataUserService.removeById(id);
//		return Result.ok("删除成功!");
//	}
//
//	/**
//	 *  批量删除
//	 *
//	 * @param ids
//	 * @return
//	 */
//	@AutoLog(value = "data_user-批量删除")
//	@ApiOperation(value="data_user-批量删除", notes="data_user-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.dataUserService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.ok("批量删除成功!");
//	}
//
//	/**
//	 * 通过id查询
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_user-通过id查询")
//	@ApiOperation(value="data_user-通过id查询", notes="data_user-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
//		DataUser dataUser = dataUserService.getById(id);
//		if(dataUser==null) {
//			return Result.error("未找到对应数据");
//		}
//		return Result.ok(dataUser);
//	}
//
//    /**
//    * 导出excel
//    *
//    * @param request
//    * @param dataUser
//    */
//    @RequestMapping(value = "/exportXls")
//    public ModelAndView exportXls(HttpServletRequest request, DataUser dataUser) {
//        return super.exportXls(request, dataUser, DataUser.class, "data_user");
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
//        return super.importExcel(request, response, DataUser.class);
//    }

}
