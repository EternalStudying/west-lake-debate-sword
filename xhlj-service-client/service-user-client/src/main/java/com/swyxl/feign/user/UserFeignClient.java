package com.swyxl.feign.user;

import com.swyxl.model.entity.service.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-user")
public interface UserFeignClient {

    @GetMapping("/service/user/userInfo/auth/getById/{id}")
    UserInfo getById(@PathVariable(value = "id") Long id);

}
