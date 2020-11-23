package com.kbox.task.domain.userview.model.response;

import com.kbox.task.domain.userview.UserViewEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.text.SimpleDateFormat;

@Data
public class UserViewResponse {

    private Long id;
    private String currentUser;
    private String viewerUser;
    private String viewTime;

    public static UserViewResponse toResponse(UserViewEntity userEntity) {
        UserViewResponse userResponse = new UserViewResponse();
        new ModelMapper().map(userEntity, userResponse);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ssZ");
        userResponse.setViewTime(sdf.format(userEntity.getViewTime()));
        userResponse.setCurrentUser(userEntity.getActiveUser());
        return userResponse;
    }
}
