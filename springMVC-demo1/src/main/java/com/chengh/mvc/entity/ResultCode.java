package com.chengh.mvc.entity;

/**
 * 错误码枚举
 */
public enum ResultCode {
    SUCCESS("0", "success"),
    BIND_ERROR("1", "BindException错误"),
    NOTREADABLE_ERROR("2", "HttpMessageNotReadableException错误"),
    ARGUMENT_ERROR("3", "MethodArgumentNotValidException错误"),
    EXCEPTION_ERROR("4", "Exception错误");

    private String code;

    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
