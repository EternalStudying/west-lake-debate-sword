package com.swyxl.feign.common;

import com.swyxl.model.vo.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

@FeignClient(value = "service-common")
public interface CommonFeignClient {

    @PostMapping("/service/common/auth/imageUpload")
    Result imageUpload(@RequestParam(value = "image") MultipartFile image, @RequestParam(value = "request") HttpServletRequest request);
}
