package com.chengh.mvc.entity;

/**
 * 返回类
 */
public class Result {
    //状态码
    private String code;
    //描述
    private String message;

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    /**
     * 生成一个ApiResult对象, 并返回
     *
     * @param resultCode 错误码枚举
     */
    public static Result of(ResultCode resultCode) {
        return new Result(resultCode);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
