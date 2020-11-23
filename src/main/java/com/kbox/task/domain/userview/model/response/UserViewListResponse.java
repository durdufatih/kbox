package com.kbox.task.domain.userview.model.response;

import com.kbox.task.domain.user.model.response.UserResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class UserViewListResponse {
    private UserResponse userDetail;
    private Page<UserViewResponse> viewList;
}
