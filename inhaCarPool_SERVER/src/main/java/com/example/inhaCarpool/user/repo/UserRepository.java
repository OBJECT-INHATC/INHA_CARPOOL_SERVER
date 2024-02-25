package com.example.inhaCarpool.user.repo;

import com.example.inhaCarpool.user.data.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *    Report 관련 기능을 담당하는 Repository
 *
 *   @version          1.00    2023.09.01
 *   @author           이상훈
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByUid(String uid);
    Optional<UserEntity> findByNickname(String nickname);
    Optional<UserEntity> findByNicknameContaining(String nickname);

}
