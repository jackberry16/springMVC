package com.chengh.mvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class TestController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/test1")
    public String test1() {
        return "upload";
    }

    @GetMapping("/exception")
    public String testException()  {
        if (1 != 2) {
            throw new RuntimeException();
        }
        return "success";
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request) throws Exception {
        // 1.得到要下载文件的真实路径
        ServletContext servletContext = request.getServletContext();
        String realPath = servletContext.getRealPath("/webapp/WEB-INF/templates/error.html");
        // 2.得到要下载的文件的流
        FileInputStream is = new FileInputStream(realPath);
        byte[] temp = new byte[is.available()];// 使用available获取的大小和文件大小相同
        is.read(temp);
        is.close();
        // 3.将要下载的文件流返回
        HttpHeaders httpHeaders = new HttpHeaders();// 自定义响应头
        httpHeaders.set("Content-Disposition","attachment;filename=error.html");
        return new ResponseEntity<byte[]>(temp, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam("headerimg") MultipartFile file, Model model) {
        System.out.println("上传的文件的信息:");
        System.out.println("文件项的名字:" + file.getName());
        System.out.println("文件名:" + file.getOriginalFilename());
        // 文件保存
        try {
            file.transferTo(new File("E:\\TEST\\" + file.getOriginalFilename()));
            model.addAttribute("msg", "文件上传成功了");
        } catch (IllegalStateException | IOException e) {
            model.addAttribute("msg", "文件上传失败了" + e.getMessage());
        }
        return "upload";
    }

}
