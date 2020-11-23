package com.kbox.task.domain.user.model.request;

import com.kbox.task.domain.user.UserEntity;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotNull
    @Column(unique = true)
    private String userName;
    @NotNull
    @Column(unique = true)
    @Email
    private String email;

    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(this.email);
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setUserName(this.userName);
        return userEntity;
    }


}
