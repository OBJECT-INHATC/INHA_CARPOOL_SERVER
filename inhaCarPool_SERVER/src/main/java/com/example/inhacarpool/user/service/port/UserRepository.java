package com.example.inhacarpool.user.service.port;

import com.example.inhacarpool.user.domain.User;
import java.util.List;

public interface UserRepository {

    User save(User user);

    List<User> findAll();

}
