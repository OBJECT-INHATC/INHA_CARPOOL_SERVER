package com.example.inhacarpool.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inhacarpool.user.data.UserEntity;

/**
 *    Report 관련 기능을 담당하는 Repository
 *
 *   @version 1.00    2023.09.01
 *   @author 이상훈
 */

public interface UserRepository extends JpaRepository<UserEntity, String> {

	Optional<UserEntity> findByUid(String uid);

	Optional<UserEntity> findByNickname(String nickname);

	Optional<UserEntity> findByNicknameContaining(String nickname);

	boolean existsByNickname(String nickname);

}
