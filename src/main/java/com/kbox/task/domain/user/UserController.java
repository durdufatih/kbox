package com.kbox.task.domain.user;

import com.kbox.task.domain.user.model.request.CreateUserRequest;
import com.kbox.task.domain.user.model.response.UserResponse;
import com.kbox.task.domain.userview.UserViewService;
import com.kbox.task.domain.userview.model.request.CreateUserViewRequest;
import com.kbox.task.domain.userview.model.response.UserViewListResponse;
import com.kbox.task.domain.userview.model.response.UserViewResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final UserViewService userViewService;

    public UserController(UserService userService, UserViewService userViewService) {
        this.userService = userService;
        this.userViewService = userViewService;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @PostMapping("view")
    public UserViewResponse createView(@RequestBody @Valid CreateUserViewRequest createUserViewRequest) {
        return userViewService.createUserView(createUserViewRequest);
    }

    @GetMapping("{currentUser}/views")
    public UserViewListResponse list(@PathVariable String currentUser,
                                     @RequestParam int page,
                                     @RequestParam(defaultValue = "10") @Size(max = 20, message = "Max size is 20") int size) {
        return userViewService.getList(currentUser, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "viewTime")));
    }
}
