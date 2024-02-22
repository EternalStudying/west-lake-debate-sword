package com.swyxl.common.client;

import com.swyxl.model.vo.common.Result;
import com.swyxl.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Service
@FeignClient(value = "service-common")
public interface CommonFeignClient {


    @PostMapping("/service/common/auth/imageUpload")
    public Result imageUpload(MultipartFile image, HttpServletRequest request);


}
