package com.example.inhacarpool.user.controller.port;

import com.example.inhacarpool.user.controller.request.UserCreateRequest;
import com.example.inhacarpool.user.domain.User;
import java.util.List;

public interface UserService {

    User create(UserCreateRequest userCreateRequest);

    List<User> findAll();
}
