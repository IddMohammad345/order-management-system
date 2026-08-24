package com.idd.user_service.service;

import com.idd.user_service.dto.UserCreateRequest;
import com.idd.user_service.dto.UserResponse;
import com.idd.user_service.exception.UserNotFoundException;
import com.idd.user_service.model.User;
import com.idd.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.email())){
            throw new IllegalArgumentException(
                    "Email already registered: "+ request.email()
            );
        }
        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phone(request.phone())
                .password(request.password())
                .status("ACTIVE")
                .build();
        User save = userRepository.save(user);

        return toResponse(save);
    }

    @Override
    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "USER NOT FOUND: " + id
                        )
                );
        return toResponse(user);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        return toResponse(
                userRepository.findByEmail(email)
                        .orElseThrow(
                                ()->new UserNotFoundException(
                                        "User Not Found: "+email
                                )
                        )
                );
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getStatus(),
                user.getCreatedAT()
        );
    }
}
