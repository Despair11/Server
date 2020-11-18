package org.jeecg.modules.xjdatascreen.datawineshop.entity;

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
 * @Description: data_wine_shop
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Data
@TableName("data_wine_shop")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="data_wine_shop对象", description="data_wine_shop")
public class DataWineShop implements Serializable {
    private static final long serialVersionUID = 1L;

	/**订单id*/
	@Excel(name = "订单编号", width = 15)
    @ApiModelProperty(value = "订单编号")
    private java.lang.String orderId;
	/**订单渠道*/
	@Excel(name = "订单渠道", width = 15)
    @ApiModelProperty(value = "订单渠道")
    private java.lang.String orderChannel;
	/**产品名称*/
	@Excel(name = "产品名称", width = 15)
    @ApiModelProperty(value = "产品名称")
    private java.lang.String productName;
	/**数量*/
	@Excel(name = "数量", width = 15)
    @ApiModelProperty(value = "数量")
    private java.lang.Integer buyNumber;
	/**联系人*/
	@Excel(name = "联系人", width = 15)
    @ApiModelProperty(value = "联系人")
    private java.lang.String contactPerson;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
    private java.lang.String mobile;
	/**使用时间*/
	@Excel(name = "使用时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "使用时间")
    private java.util.Date useTime;
	/**订单金额*/
	@Excel(name = "订单金额", width = 15)
    @ApiModelProperty(value = "订单金额")
    private java.math.BigDecimal orderPrice;
	/**支付方式*/
	@Excel(name = "支付方式", width = 15)
    @ApiModelProperty(value = "支付方式")
    private java.lang.String payType;
	/**订单状态*/
	@Excel(name = "订单状态", width = 15)
    @ApiModelProperty(value = "订单状态")
    private java.lang.String orderStatus;
	/**支付单号*/
	@Excel(name = "支付单号", width = 15)
    @ApiModelProperty(value = "支付单号")
    private java.lang.String payOrderId;
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
