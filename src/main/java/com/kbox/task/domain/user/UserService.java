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

    /**
     * This method gives to allow create new user
     *
     * @param createUserRequest this is request object when you send null onject method return @throws IllegalArgumentException.class
     * @return UserResponse.class
     */
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        if (Objects.isNull(createUserRequest))
            throw new IllegalArgumentException("Request Object is null");
        return UserResponse.toResponse(userRepository.save(createUserRequest.toEntity()));
    }

    /**
     * This method return UserResponse ,by given id
     *
     * @param id User Id if you send null this method throw exception
     * @return UserResponse
     */
    public UserResponse getById(String id) {
        if (!StringUtils.hasText(id))
            throw new IllegalArgumentException("Id is null");
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        return UserResponse.toResponse(userEntity);
    }
}
