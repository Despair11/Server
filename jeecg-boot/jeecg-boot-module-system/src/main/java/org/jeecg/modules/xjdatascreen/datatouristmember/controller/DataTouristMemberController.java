package org.jeecg.modules.xjdatascreen.datatouristmember.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.xjdatascreen.datatouristmember.entity.DataTouristMember;
import org.jeecg.modules.xjdatascreen.datatouristmember.entity.DataTouristMemberVO;
import org.jeecg.modules.xjdatascreen.datatouristmember.service.IDataTouristMemberService;

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
 * @Description: data_tourist_member
 * @Author: jeecg-boot
 * @Date:   2020-11-19
 * @Version: V1.0
 */
@Api(tags="data_tourist_member_游客管理")
@RestController
@RequestMapping("/datatouristmember/dataTouristMember")
@Slf4j
public class DataTouristMemberController extends JeecgController<DataTouristMember, IDataTouristMemberService> {
	@Autowired
	private IDataTouristMemberService dataTouristMemberService;
	
//	/**
//	 * 分页列表查询
//	 *
//	 * @param dataTouristMember
//	 * @param pageNo
//	 * @param pageSize
//	 * @param req
//	 * @return
//	 */
//	@AutoLog(value = "data_tourist_member-分页列表查询")
//	@ApiOperation(value="data_tourist_member-分页列表查询", notes="data_tourist_member-分页列表查询")
//	@GetMapping(value = "/list")
//	public Result<?> queryPageList(DataTouristMember dataTouristMember,
//								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//								   HttpServletRequest req) {
//		QueryWrapper<DataTouristMember> queryWrapper = QueryGenerator.initQueryWrapper(dataTouristMember, req.getParameterMap());
//		Page<DataTouristMember> page = new Page<DataTouristMember>(pageNo, pageSize);
//		IPage<DataTouristMember> pageList = dataTouristMemberService.page(page, queryWrapper);
//		return Result.ok(pageList);
//	}
//
//	/**
//	 *   添加
//	 *
//	 * @param dataTouristMember
//	 * @return
//	 */
//	@AutoLog(value = "data_tourist_member-添加")
//	@ApiOperation(value="data_tourist_member-添加", notes="data_tourist_member-添加")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody DataTouristMember dataTouristMember) {
//		dataTouristMemberService.save(dataTouristMember);
//		return Result.ok("添加成功！");
//	}
//
//	/**
//	 *  编辑
//	 *
//	 * @param dataTouristMember
//	 * @return
//	 */
//	@AutoLog(value = "data_tourist_member-编辑")
//	@ApiOperation(value="data_tourist_member-编辑", notes="data_tourist_member-编辑")
//	@PutMapping(value = "/edit")
//	public Result<?> edit(@RequestBody DataTouristMember dataTouristMember) {
//		dataTouristMemberService.updateById(dataTouristMember);
//		return Result.ok("编辑成功!");
//	}
//
//	/**
//	 *   通过id删除
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_tourist_member-通过id删除")
//	@ApiOperation(value="data_tourist_member-通过id删除", notes="data_tourist_member-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
//		dataTouristMemberService.removeById(id);
//		return Result.ok("删除成功!");
//	}
//
//	/**
//	 *  批量删除
//	 *
//	 * @param ids
//	 * @return
//	 */
//	@AutoLog(value = "data_tourist_member-批量删除")
//	@ApiOperation(value="data_tourist_member-批量删除", notes="data_tourist_member-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.dataTouristMemberService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.ok("批量删除成功!");
//	}
//
//	/**
//	 * 通过id查询
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_tourist_member-通过id查询")
//	@ApiOperation(value="data_tourist_member-通过id查询", notes="data_tourist_member-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
//		DataTouristMember dataTouristMember = dataTouristMemberService.getById(id);
//		if(dataTouristMember==null) {
//			return Result.error("未找到对应数据");
//		}
//		return Result.ok(dataTouristMember);
//	}
//
//    /**
//    * 导出excel
//    *
//    * @param request
//    * @param dataTouristMember
//    */
//    @RequestMapping(value = "/exportXls")
//    public ModelAndView exportXls(HttpServletRequest request, DataTouristMember dataTouristMember) {
//        return super.exportXls(request, dataTouristMember, DataTouristMember.class, "data_tourist_member");
//    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @ApiOperation(value = "游客数据导入", notes = "游客数据导入")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, DataTouristMember.class);
    }

    @ApiOperation(value = "统计用户数据", notes = "统计用户数据")
    @GetMapping("/getPeopleData")
    public Result<?> getPeopleData() {
        List<DataTouristMemberVO> dataTouristMemberVOS = dataTouristMemberService.selectAll();
        return Result.ok(dataTouristMemberVOS);
    }


    @ApiOperation(value = "获取总的国庆节数据", notes = "获取总的国庆节数据")
     @GetMapping("/getNationalDayData")
     public Result<?> getNationalDayData() {
        List<DataTouristMemberVO> dataTouristMemberVOS = dataTouristMemberService.selectNationalDayAll();
        return Result.ok(dataTouristMemberVOS);
    }

}
