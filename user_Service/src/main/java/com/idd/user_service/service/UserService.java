package com.idd.user_service.service;

import com.idd.user_service.dto.UserCreateRequest;
import com.idd.user_service.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserCreateRequest request);
    UserResponse getUser(Long id);
    UserResponse getUserByEmail(String email);
}
