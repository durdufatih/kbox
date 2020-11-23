package com.kbox.task.domain.user.model.response;

import com.kbox.task.domain.user.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class UserResponse {

    private String id;
    private String userName;
    private String email;

    public static UserResponse toResponse(UserEntity userEntity) {
        UserResponse userResponse = new UserResponse();
        new ModelMapper().map(userEntity, userResponse);
        return userResponse;
    }

}
