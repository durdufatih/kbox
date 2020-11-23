package com.kbox.task;


import com.kbox.task.domain.user.UserEntity;
import com.kbox.task.domain.user.UserRepository;
import com.kbox.task.domain.user.UserService;
import com.kbox.task.domain.user.model.request.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void whenSendRequestObjectNullToCreateUserMethodExpectedIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(null);
        });
    }

    @Test
    public void whenSendValidObjectToCreateMethodExpectedResponseObject() {
        UserEntity userEntity = new UserEntity("test", "data1", "data2");
        CreateUserRequest createUserRequest = new CreateUserRequest("data1", "data2");
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        userService.createUser(createUserRequest);

        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void whenSendUserIdIsNullToGetUserMethodExpectedIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.getById(null);
        });
    }

    @Test
    public void whenSendValidUserIdToGetByIdMethodExpectedResponseObject() {
        UserEntity userEntity = new UserEntity("test", "data1", "data2");
        CreateUserRequest createUserRequest = new CreateUserRequest("data1", "data2");
        when(userRepository.findById(any(String.class))).thenReturn(java.util.Optional.of(userEntity));

        userService.getById("id");

        verify(userRepository, times(1)).findById("id");
    }
}
