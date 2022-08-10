package org.jeecg.modules.bigscreen.dto;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.jeecg.modules.bigscreen.constant.CustomErrorCode;
import org.jeecg.modules.bigscreen.exception.IErrorCode;

/**
 * 通用返回对象
 * @author gonghao
 */
@ApiModel(description = "返回数据对象")
public class BaseResponse<T> {

    //返回编码
    @ApiModelProperty(value = "错误码", example = "0")
    private String code = "0";
    //返回消息
    @ApiModelProperty(value = "错误描述", example = "success")
    private String msg = "success";
    //返回数据
    @ApiModelProperty(value = "具体数据")
    private T data;

    public BaseResponse(){
        this.code = CustomErrorCode.ERR_SUCCESS.getCode();
    }

    public BaseResponse(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public BaseResponse(T data) {
        this.code = CustomErrorCode.ERR_SUCCESS.getCode();
        this.data = data;
    }

    public BaseResponse(IErrorCode errorCode){
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
    }

    public BaseResponse(IErrorCode errorCode, String appendMsg){
        if (errorCode != null) {
            this.code = errorCode.getCode();
            String msg = errorCode.getMessage();
            if (!Strings.isNullOrEmpty(appendMsg)) {
                msg = msg + ": " + appendMsg;
            }
            this.msg = msg;
        }
    }

    public String getCode() {
        return code;
    }

    public BaseResponse setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BaseResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseResponse setData(T data) {
        this.data = data;
        return this;
    }

}
