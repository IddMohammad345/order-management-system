package com.idd.orderservice.client;

import com.idd.orderservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-Service",
        url = "${user.service.url}")
public interface UserClient {
    @GetMapping("/api/v1/users/{id}")
    UserResponse getUser(
            @PathVariable("id") Long id
    );
}
