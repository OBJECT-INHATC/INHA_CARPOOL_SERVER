package com.example.inhaCarpool.repository;

import com.example.inhaCarpool.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *    Report 관련 기능을 담당하는 Repository
 *
 *   @version          1.00    2023.09.01
 *   @author           이상훈
 */

public interface UserInterface extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByUid(String uid);
    Optional<UserEntity> findByNickname(String nickname);

}
