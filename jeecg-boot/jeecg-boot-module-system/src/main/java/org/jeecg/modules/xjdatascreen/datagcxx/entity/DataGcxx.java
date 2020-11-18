package org.jeecg.modules.xjdatascreen.datagcxx.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: data_gcxx
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Data
@TableName("data_gcxx")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="data_gcxx对象", description="data_gcxx")
public class DataGcxx implements Serializable {
    private static final long serialVersionUID = 1L;

	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
    private java.lang.String clxxbh;
	/**卡口编号*/
	@Excel(name = "卡口编号", width = 15)
    @ApiModelProperty(value = "卡口编号")
    private java.lang.String kkbh;
	/**卡口名称*/
	@Excel(name = "卡口名称", width = 15)
    @ApiModelProperty(value = "卡口名称")
    private java.lang.String kkmc;
	/**维度*/
	@Excel(name = "维度", width = 15)
    @ApiModelProperty(value = "维度")
    private java.lang.String wd;
	/**经度*/
	@Excel(name = "经度", width = 15)
    @ApiModelProperty(value = "经度")
    private java.lang.String jd;
	/**车道编号*/
	@Excel(name = "车道编号", width = 15)
    @ApiModelProperty(value = "车道编号")
    private java.lang.String cdbh;
	/**号牌号码*/
	@Excel(name = "号牌号码", width = 15)
    @ApiModelProperty(value = "号牌号码")
    private java.lang.String hphm;
	/**号牌颜色0.白的,1.黄色,2.蓝色,3.黑色,4,其他*/
	@Excel(name = "号牌颜色0.白的,1.黄色,2.蓝色,3.黑色,4,其他", width = 15)
    @ApiModelProperty(value = "号牌颜色0.白的,1.黄色,2.蓝色,3.黑色,4,其他")
    private java.lang.String hpys;
	/**行驶方向代码*/
	@Excel(name = "行驶方向代码", width = 15)
    @ApiModelProperty(value = "行驶方向代码")
    private java.lang.String xsfxdm;
	/**行驶方向*/
	@Excel(name = "行驶方向", width = 15)
    @ApiModelProperty(value = "行驶方向")
    private java.lang.String xsfx;
	/**车辆类型
	0-未识别
	1-大客车
	2-货车
	3-轿车
	4-面包车
	5-小货车
	6-行人
	7-二轮车
	9-SUV/MVP
	10-中型客车*/
	@Excel(name = "车辆类型", width = 15)
	// 0-未识别 1-大客车 2-货车 3-轿车 4-面包车 5-小货车 6-行人 7-二轮车 9-SUV/MVP 10-中型客车
    @ApiModelProperty(value = "车辆类型 0-未识别 1-大客车 2-货车 3-轿车 4-面包车 5-小货车 6-行人 7-二轮车 9-SUV/MVP 10-中型客车")
    private java.lang.String cllx;
	/**车身颜色*/
	@Excel(name = "车身颜色", width = 15)
    @ApiModelProperty(value = "车身颜色")
    private java.lang.String csys;
	/**号牌种类
	0-未识别
	1- 92式民用车
	2-警用车
	5-左右军车车牌类型(一行结构)
	7- 02式个性化车
	8-黄色双行尾牌
	10-使馆车
	11-武警车
	13-黄色1225农用车
	16-摩托车
	17-新能源车*/
	@Excel(name = "号牌种类", width = 15)
    // 0-未识别 1- 92式民用车 2-警用车 5-左右军车车牌类型(一行结构) 7- 02式个性化车 8-黄色双行尾牌 10-使馆车 11-武警车 13-黄色1225农用车 16-摩托车 17-新能源车"
	@ApiModelProperty(value = "号牌种类 0-未识别 1- 92式民用车 2-警用车 5-左右军车车牌类型(一行结构) 7- 02式个性化车 8-黄色双行尾牌 10-使馆车 11-武警车 13-黄色1225农用车 16-摩托车 17-新能源车")
    private java.lang.String hpzl;
	/**车辆速度*/
	@Excel(name = "车辆速度", width = 15)
    @ApiModelProperty(value = "车辆速度")
    private java.lang.String clsd;
	/**车牌图片*/
	@Excel(name = "车牌图片", width = 15)
    @ApiModelProperty(value = "车牌图片")
    private java.lang.String hptp;
	/**过车图片*/
	@Excel(name = "过车图片", width = 15)
    @ApiModelProperty(value = "过车图片")
    private java.lang.String qjtp;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
    private java.util.Date createTime;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date updateTime;
}
