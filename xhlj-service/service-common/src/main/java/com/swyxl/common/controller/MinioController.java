package com.swyxl.common.controller;

import com.swyxl.common.service.MinioService;
import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/service/common")
public class MinioController {

    @Autowired
    private MinioService minioService;

    @PostMapping("/fileUpload")
    public String fileUpload(MultipartFile file, String type){
        return minioService.upload(file, type);
    }

    @GetMapping("/download")
    public String download(String downloadUrl){
        return minioService.download(downloadUrl);
    }
}
