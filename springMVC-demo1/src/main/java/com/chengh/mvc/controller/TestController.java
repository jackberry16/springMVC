package com.chengh.mvc.controller;

import com.chengh.mvc.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
        File file = new File("D:\\TEST\\8536009.JPG");
        byte[] body = null;
        InputStream is = new FileInputStream(file);
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;
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

    @PostMapping("/user")
    @ResponseBody
    public String postUser(@RequestBody @Valid User user){
        return "success";
    }

}
