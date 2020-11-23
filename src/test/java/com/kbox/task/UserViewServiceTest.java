package com.kbox.task;

import com.kbox.task.domain.user.UserEntity;
import com.kbox.task.domain.user.UserService;
import com.kbox.task.domain.user.model.response.UserResponse;
import com.kbox.task.domain.userview.UserViewEntity;
import com.kbox.task.domain.userview.UserViewRepository;
import com.kbox.task.domain.userview.UserViewService;
import com.kbox.task.domain.userview.model.request.CreateUserViewRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserViewServiceTest {

    @Mock
    private UserViewRepository userViewRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserViewService userViewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenSendRequestObjectNullToCreateUserViewMethodExpectedIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userViewService.createUserView(null);
        });
    }

    @Test
    public void whenSendValidObjectToCreateViewMethodExpectedResponseObject() {
        UserEntity userEntity = new UserEntity("test", "data1", "data2");
        UserViewEntity userViewEntity = new UserViewEntity(1, "test", "data1", new Date(), true);
        CreateUserViewRequest createUserViewRequest = new CreateUserViewRequest("data1", "data2", "datetime");
        when(userViewRepository.save(any(UserViewEntity.class))).thenReturn(userViewEntity);
        when(userService.getById(any(String.class))).thenReturn(UserResponse.toResponse(userEntity));

        userViewService.createUserView(createUserViewRequest);

        verify(userViewRepository, times(1)).save(any(UserViewEntity.class));
    }


    @Test
    public void whenSendRequestObjectNullToListMethodExpectedIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userViewService.getList(null, null);
        });
    }

    @Test
    public void whenSendValidObjectToListMethodExpectedResponseObject() {

        UserViewEntity userViewEntity = new UserViewEntity(1, "test", "data1", new Date(), true);
        Page<UserViewEntity> pge = new PageImpl<>(Arrays.asList(userViewEntity), Pageable.unpaged(), 1);
        when(userViewRepository.findAllByActiveUserAndShowStatusIsTrueAndViewTimeAfter(any(String.class), any(Date.class), any(Pageable.class))).thenReturn(pge);

        userViewService.getList("User1", Pageable.unpaged());

        verify(userViewRepository, times(1)).findAllByActiveUserAndShowStatusIsTrueAndViewTimeAfter(any(String.class), any(Date.class), any(Pageable.class));
    }
}
