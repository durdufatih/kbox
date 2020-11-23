package com.kbox.task.domain.userview;

import com.kbox.task.domain.user.UserService;
import com.kbox.task.domain.userview.model.request.CreateUserViewRequest;
import com.kbox.task.domain.userview.model.response.UserViewListResponse;
import com.kbox.task.domain.userview.model.response.UserViewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserViewService {

    private final UserViewRepository userViewRepository;
    private final UserService userService;

    public UserViewService(UserViewRepository userViewRepository, UserService userService) {
        this.userViewRepository = userViewRepository;
        this.userService = userService;
    }

    /**
     * This method create UserView data by given reqeust object
     *
     * @param createUserViewRequest this object contains currentuser,viewer id
     * @return
     */
    //TODO: Kafka integration
    public UserViewResponse createUserView(CreateUserViewRequest createUserViewRequest) {
        if (Objects.isNull(createUserViewRequest))
            throw new IllegalArgumentException("Request is null");
        return UserViewResponse.toResponse(userViewRepository.save(createUserViewRequest.toEntity()));
    }

    /**
     * This method allow to list userviews.
     *
     * @param currentUser
     * @param pageable
     * @return UserViewListResponse you can use cache for userService.getById in this method
     */
    public UserViewListResponse getList(String currentUser, Pageable pageable) {
        if (Objects.isNull(currentUser) || Objects.isNull(pageable))
            throw new IllegalArgumentException("Request objects are null");
        UserViewListResponse userViewListResponse = new UserViewListResponse();
        userViewListResponse.setUserDetail(userService.getById(currentUser));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30);
        Page<UserViewEntity> entityPage = userViewRepository.findAllByActiveUserAndShowStatusIsTrueAndViewTimeAfter(currentUser, calendar.getTime(), pageable);
        userViewListResponse.setViewList(new PageImpl<>(entityPage.getContent().stream().map(UserViewResponse::toResponse).collect(Collectors.toList()), pageable, entityPage.getTotalElements()));
        return userViewListResponse;
    }
}
