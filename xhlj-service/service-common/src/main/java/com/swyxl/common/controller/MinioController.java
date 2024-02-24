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

    @PostMapping("/auth/imageUpload")
    public Result imageUpload(MultipartFile image, HttpServletRequest request){
        String type = request.getHeader("type");
        String url = minioService.upload(image, type);
        return Result.build(url,ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/download")
    public String download(String downloadUrl){
        return minioService.download(downloadUrl);
    }
}
