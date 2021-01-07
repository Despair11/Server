package org.jeecg.modules.bigscreen.exception;

public class CustomException extends Exception{
    /**
     * 序列号
     */
    private static final long serialVersionUID = -5797425976293854035L;
    
    protected String errorCode;

    public CustomException() {
        super();
    }
    
    public CustomException(String message) {
        super(message);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public CustomException(IErrorCode defaultErrorCode) {
        super(defaultErrorCode.getMessage());
        this.errorCode = defaultErrorCode.getCode();
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}
