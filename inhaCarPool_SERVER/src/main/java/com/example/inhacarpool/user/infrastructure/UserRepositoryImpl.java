package com.example.inhacarpool.user.infrastructure;

import com.example.inhacarpool.user.domain.User;
import com.example.inhacarpool.user.service.port.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        return userJpaRepository.save(UserEntity.from(user)).toModel();
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream().map(UserEntity::toModel).toList();
    }

    @Override
    public User findById(String uid) {
        return userJpaRepository.findById(uid).map(UserEntity::toModel).orElse(null);
    }

    @Override
    public void addYellow(String uid) {
        userJpaRepository.addYellow(uid);
    }

    @Override
    public void ban(String uid) {
        userJpaRepository.ban(uid);
    }

    @Override
    public void cancelBan(String uid) {
        userJpaRepository.cancelBan(uid);
    }
}
