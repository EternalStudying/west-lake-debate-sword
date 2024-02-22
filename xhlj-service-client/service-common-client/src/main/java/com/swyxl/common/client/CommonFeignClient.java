package com.swyxl.common.client;

import com.swyxl.model.vo.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Repository
@FeignClient(value = "service-common")
public interface CommonFeignClient {


    @PostMapping("/service/common/auth/imageUpload")
    public Result imageUpload(@RequestParam MultipartFile image,@RequestParam HttpServletRequest request);


}
