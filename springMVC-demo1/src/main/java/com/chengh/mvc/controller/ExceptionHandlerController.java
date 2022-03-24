package com.chengh.mvc.controller;

import com.chengh.mvc.entity.Result;
import com.chengh.mvc.entity.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;

/**
 * 全局异常的处理
 *     a.使用注解实现:ExceptionHandlerController
 *         1)定义一个全局的异常处理Handler
 *         2)类上面添加@ControllerAdvice
 *         3)方法上面添加@ExceptionHandler(value = 异常的种类.class)
 *             当出现当前异常或者当前异常的子类时，就会调用该方法
 *
 *     b.实现HandlerExceptionResolver接口:ExceptionHandler
 *         1)复写该接口中的方法，在这个方法里面还需要设置异常出现后的视图
 *         2)缺点：只能返回错误视图
 *         3)这个类需要用@Component来修饰
 */

//全局异常处理的注解
@RestControllerAdvice
public class ExceptionHandlerController {
    /**
     * 400 - Bad Request 参数绑定出错
     */
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e) {
        return Result.of(ResultCode.BIND_ERROR);
    }

    /**
     * 500 - Internal Server Error 服务器内部异常
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return Result.of(ResultCode.EXCEPTION_ERROR).toString();
    }

}
