package com.chengh.mvc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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
    //出现Exception异常，或者该异常的子类都会调用本方法
    @ExceptionHandler(Exception.class)
    public String handlerException(Exception e) {
        return "error";
    }
}
