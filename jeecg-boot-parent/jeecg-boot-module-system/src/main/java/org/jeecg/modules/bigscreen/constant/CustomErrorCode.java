// 枚举错误码常量，错误码须符合错误码规范
package org.jeecg.modules.bigscreen.constant;


import org.jeecg.modules.bigscreen.exception.IErrorCode;

/**
 * 通用错误码：errorCode可只写后五位，前面的组件错误码标示将统一添加处理
 *
 * @author liuhanjun
 */
public enum CustomErrorCode implements IErrorCode {

    ERR_SUCCESS("0", "success"),

    FAILED("-1", "params should not be null"),

    /**
     * 未归类错误码 ,错误码中 前16位的后四位，错误标识，在这里直接定义
     */
    ERROR("10001", "error un classify"),

    /**
     * 参数校验错误
     */
    VALIDATE_ERROR("10002", "validate error"),

    /**
     * 请求参数缺失
     */
    REQUEST_MISSING_REQUEST_PARAMETER("10003", "missing request parameter"),

    /**
     * 调用外部请求失败，服务端错误
     */
    EXTERNAL_RREQUEST_SERVER_ERROR("10004", "服务异常"),

    /**
     * 请求token验证失败
     */
    REQUEST_TOKEN_CHECK_ERROR("10006", "token check error,forbidden"),

    /**
     * 找不到服务信息
     */
    SERVICE_INFO_UNFOUND("10007", "service info not found"),

    /**
     * 配置参数查询失败
     */
    PARAM_NOT_FOUND("10008", "config param not found"),

    /**
     * 数据解密失败
     */
    DATA_DECRYPT_ERROR("10009", "decrypt error"),

    /**
     * 数据库启动失败
     */
    DB_START_ERROR("1000a", "db start error"),

    /**
     * 登陆过期，请重新登陆
     */
    AUTH_TOKEN_ERROR("10011", "登陆过期，请重新登陆")
    ;

    private String code;

    private String message;

    CustomErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


