package org.jeecg.modules.xjdatascreen.dataordercheckrecord.entity;

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
 * @Description: data_order_check_record
 * @Author: jeecg-boot
 * @Date:   2020-11-21
 * @Version: V1.0
 */
@Data
@TableName("data_order_check_record")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="data_order_check_record对象", description="data_order_check_record")
public class DataOrderCheckRecord implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**订单编号*/
	@Excel(name = "订单编号", width = 15)
    @ApiModelProperty(value = "订单编号")
    private java.lang.String orderNum;
	/**账号登录名称*/
	@Excel(name = "账号登录名称", width = 15)
    @ApiModelProperty(value = "账号登录名称")
    private java.lang.String loginName;
	/**账号名称*/
	@Excel(name = "账号名称", width = 15)
    @ApiModelProperty(value = "账号名称")
    private java.lang.String accountName;
	/**核验时间*/
	@Excel(name = "核验时间", width = 15)
    @ApiModelProperty(value = "核验时间")
    private java.lang.String checkTime;
	/**设备描述*/
	@Excel(name = "设备描述", width = 15)
    @ApiModelProperty(value = "设备描述")
    private java.lang.String deviceDescription;
	/**核验数量*/
	@Excel(name = "核验数量", width = 15)
    @ApiModelProperty(value = "核验数量")
    private java.lang.Integer count;
	/**核验方式：1-手持/闸机核验 2-后台核验 3-到期自动核验*/
	@Excel(name = "核验方式：1-手持/闸机核验 2-后台核验 3-到期自动核验", width = 15)
    @ApiModelProperty(value = "核验方式：1-手持/闸机核验 2-后台核验 3-到期自动核验")
    private java.lang.Integer checkStyle;
	/**核验计算方式：1-一次核验 2-二次核验*/
	@Excel(name = "核验计算方式：1-一次核验 2-二次核验", width = 15)
    @ApiModelProperty(value = "核验计算方式：1-一次核验 2-二次核验")
    private java.lang.Integer checkCalculateMethod;
	/**本次核验票的金额*/
	@Excel(name = "本次核验票的金额", width = 15)
    @ApiModelProperty(value = "本次核验票的金额")
    private java.lang.String checkAmount;
}
