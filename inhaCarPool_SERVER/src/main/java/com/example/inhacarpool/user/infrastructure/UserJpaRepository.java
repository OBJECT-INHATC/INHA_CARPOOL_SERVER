package com.example.inhacarpool.user.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {

	Optional<UserEntity> findByUid(String uid);

	Optional<UserEntity> findByNickname(String nickname);

	Optional<UserEntity> findByNicknameContaining(String nickname);

	boolean existsByNickname(String nickname);

	boolean existsByUid(String uid);

}
