package com.example.inhacarpool.user.controller.port;

import com.example.inhacarpool.user.controller.request.UserCreateRequest;
import com.example.inhacarpool.user.controller.response.UserWithHistoryCount;
import com.example.inhacarpool.user.domain.User;
import java.util.List;

public interface UserService {

    User create(UserCreateRequest userCreateRequest);

    List<UserWithHistoryCount> findAll();

    User findOne(String uid);

    User resetYellow(String uid);

    int countYellow(String uid);

    void addYellow(String uid);
}
