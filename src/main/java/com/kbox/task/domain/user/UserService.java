package com.kbox.task.domain.user;

import com.kbox.task.domain.user.model.request.CreateUserRequest;
import com.kbox.task.domain.user.model.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest createUserRequest) {
        if (Objects.isNull(createUserRequest))
            throw new IllegalArgumentException("Request Object is null");
        return UserResponse.toResponse(userRepository.save(createUserRequest.toEntity()));
    }

    public UserResponse getById(String id) {
        if (!StringUtils.hasText(id))
            throw new IllegalArgumentException("Id is null");
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        return UserResponse.toResponse(userEntity);
    }
}
