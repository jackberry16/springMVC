package com.chengh.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/test1")
    public String test1() {
        return "test1";
    }

    @GetMapping("/exception")
    public String testException()  {
        if (1 != 2) {
            throw new RuntimeException();
        }
        return "success";
    }
}
