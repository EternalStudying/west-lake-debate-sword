package com.swyxl.feign.common;

import com.swyxl.model.vo.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "service-common")
public interface CommonFeignClient {

    @PostMapping(value = "/service/common/fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String fileUpload(@RequestPart(value = "file") MultipartFile file, @RequestParam(value = "type") String type);

    @GetMapping("/service/common/download")
    String download(@RequestParam(value = "downloadUrl") String downloadUrl);
}
