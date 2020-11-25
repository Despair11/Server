package org.jeecg.modules.xjdatascreen.datatickpassengers.entity;

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
 * @Description: tick_passengers
 * @Author: jeecg-boot
 * @Date:   2020-11-24
 * @Version: V1.0
 */
@Data
@TableName("tick_passengers")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tick_passengers对象", description="tick_passengers")
public class TickPassengers implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**订单详情编号*/
	@Excel(name = "订单详情编号", width = 15)
    @ApiModelProperty(value = "订单详情编号")
    private java.lang.String orderDetailId;
	/**票号*/
	@Excel(name = "票号", width = 15)
    @ApiModelProperty(value = "票号")
    private java.lang.String ticketNo;
	/**名字*/
	@Excel(name = "名字", width = 15)
    @ApiModelProperty(value = "名字")
    private java.lang.String name;
	/**电话*/
	@Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
    private java.lang.String mobile;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
    private java.lang.Integer cardType;
	/**证件编号*/
	@Excel(name = "证件编号", width = 15)
    @ApiModelProperty(value = "证件编号")
    private java.lang.String cardNo;
	/**状态；1待使用 2已核验 3.已退*/
	@Excel(name = "状态；1待使用 2已核验 3.已退", width = 15)
    @ApiModelProperty(value = "状态；1待使用 2已核验 3.已退")
    private java.lang.Integer status;
	/**票号生成的二维码地址*/
	@Excel(name = "票号生成的二维码地址", width = 15)
    @ApiModelProperty(value = "票号生成的二维码地址")
    private java.lang.String codeUrl;
	/**非实名制时，用他来区别出行人*/
	@Excel(name = "非实名制时，用他来区别出行人", width = 15)
    @ApiModelProperty(value = "非实名制时，用他来区别出行人")
    private java.lang.String replaceCardNo;
	/**二次核验状态 0-未二次核验 1-已二次核验*/
	@Excel(name = "二次核验状态 0-未二次核验 1-已二次核验", width = 15)
    @ApiModelProperty(value = "二次核验状态 0-未二次核验 1-已二次核验")
    private java.lang.Integer doubleCheckStatus;
	/**一次核验时间*/
	@Excel(name = "一次核验时间", width = 15)
    @ApiModelProperty(value = "一次核验时间")
    private java.lang.String checkTime;
	/**取消/退款时间*/
	@Excel(name = "取消/退款时间", width = 15)
    @ApiModelProperty(value = "取消/退款时间")
    private java.lang.String cancelTime;
	/**二次核验时间*/
	@Excel(name = "二次核验时间", width = 15)
    @ApiModelProperty(value = "二次核验时间")
    private java.lang.String doubleCheckTime;


}
