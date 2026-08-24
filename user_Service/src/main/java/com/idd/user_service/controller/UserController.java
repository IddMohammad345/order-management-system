package com.idd.user_service.controller;

import com.idd.user_service.dto.UserCreateRequest;
import com.idd.user_service.dto.UserResponse;
import com.idd.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(
            @Valid @RequestBody UserCreateRequest request
    ) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(
            @PathVariable Long id
    ) {
        return userService.getUser(id);
    }

    @GetMapping("email/{email}")
    public UserResponse getUserByEmail(
            @PathVariable String email) {
        return userService.getUserByEmail(email);
    }
}
