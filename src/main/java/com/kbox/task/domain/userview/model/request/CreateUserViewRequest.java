package com.kbox.task.domain.userview.model.request;

import com.kbox.task.domain.userview.UserViewEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserViewRequest {

    @NotNull
    private String currentUser;
    @NotNull
    private String viewerUser;
    private String viewTime;

    public UserViewEntity toEntity() {
        UserViewEntity userViewEntity = new UserViewEntity();
        userViewEntity.setActiveUser(this.getCurrentUser());
        userViewEntity.setViewerUser(this.getViewerUser());
        userViewEntity.setViewTime(new Date());
        userViewEntity.setShowStatus(true);
        return userViewEntity;
    }

}
