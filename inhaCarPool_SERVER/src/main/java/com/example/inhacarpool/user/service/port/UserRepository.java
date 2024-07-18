package com.example.inhacarpool.user.service.port;

import com.example.inhacarpool.user.domain.User;

public interface UserRepository {

    User save(User user);

}
