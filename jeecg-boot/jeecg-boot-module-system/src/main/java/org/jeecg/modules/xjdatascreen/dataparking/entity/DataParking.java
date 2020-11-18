package org.jeecg.modules.xjdatascreen.dataparking.entity;

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
 * @Description: data_parking
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Data
@TableName("data_parking")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="data_parking对象", description="data_parking")
public class DataParking implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**车牌号*/
	@Excel(name = "车牌号", width = 15)
    @ApiModelProperty(value = "车牌号")
    private java.lang.String vplNumber;
	/**进车时间*/
	@Excel(name = "进车时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "进车时间")
    private java.util.Date inTime;
	/**出车时间*/
	@Excel(name = "出车时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出车时间")
    private java.util.Date outTime;
	/**本次支付金额,单位分*/
	@Excel(name = "本次支付金额,单位分", width = 15)
    @ApiModelProperty(value = "本次支付金额,单位分")
    private java.math.BigDecimal fee;
	/**车牌颜色 0：蓝色，1：黄色，2：白色，3：黑色，4：绿色*/
	@Excel(name = "车牌颜色 0：蓝色，1：黄色，2：白色，3：黑色，4：绿色", width = 15)
    @ApiModelProperty(value = "车牌颜色 0：蓝色，1：黄色，2：白色，3：黑色，4：绿色")
    private java.lang.Integer vplColor;
	/**停留时间*/
	@Excel(name = "停留时间", width = 15)
    @ApiModelProperty(value = "停留时间")
    private java.lang.Integer stayTime;
	/**省份*/
	@Excel(name = "省份", width = 15)
    @ApiModelProperty(value = "省份")
    private java.lang.String proviceCode;
	/**入口编号*/
	@Excel(name = "入口编号", width = 15)
    @ApiModelProperty(value = "入口编号")
    private java.lang.String armCode;
	/**车场名称*/
	@Excel(name = "车场名称", width = 15)
    @ApiModelProperty(value = "车场名称")
    private java.lang.String parkName;
	/**停车记录id*/
	@Excel(name = "停车记录id", width = 15)
    @ApiModelProperty(value = "停车记录id")
    private java.lang.String recordId;
	/**支付时间*/
	@Excel(name = "支付时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "支付时间")
    private java.util.Date payTime;
	/**支付方式 1微信  2支付宝*/
	@Excel(name = "支付方式 1微信  2支付宝", width = 15)
    @ApiModelProperty(value = "支付方式 1微信  2支付宝")
    private java.lang.Integer payWay;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
    private java.util.Date updateTime;
}
