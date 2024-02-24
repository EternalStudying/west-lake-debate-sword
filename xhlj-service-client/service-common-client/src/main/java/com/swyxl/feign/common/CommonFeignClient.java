package com.swyxl.feign.common;

import com.swyxl.model.vo.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "service-common")
public interface CommonFeignClient {

    @PostMapping("/service/common/auth/imageUpload")
    Result imageUpload(@RequestParam(value = "image") MultipartFile image, @RequestParam(value = "request") HttpServletRequest request);

    @GetMapping("/service/common/auth/download")
    String download(@RequestParam(value = "downloadUrl") String downloadUrl);
}
