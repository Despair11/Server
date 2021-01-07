package org.jeecg.modules.bigscreen.configuration;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.bigscreen.constant.CustomErrorCode;
import org.jeecg.modules.bigscreen.dto.BaseResponse;
import org.jeecg.modules.bigscreen.exception.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 */
@ControllerAdvice // 可指定包前缀，比如：(basePackages = "com.pj.admin")
@Slf4j
public class GlobalExceptionHandle {

    // 全局异常拦截（拦截项目中的所有CustomException异常）
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse handlerException(Exception exception, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.error("error", exception);
        BaseResponse baseResponse = null;
        if (exception instanceof CustomException) {    // 如果是未登录异常
            baseResponse = new BaseResponse(((CustomException) exception).getErrorCode(),exception.getMessage());
        } else {
            baseResponse = new BaseResponse(CustomErrorCode.EXTERNAL_RREQUEST_SERVER_ERROR);
        }

        // 返回给前端
        return baseResponse;
    }
}
