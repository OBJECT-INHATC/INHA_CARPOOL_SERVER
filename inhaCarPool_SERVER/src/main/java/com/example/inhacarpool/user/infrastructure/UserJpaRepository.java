package com.example.inhacarpool.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {

    @Modifying
    @Transactional
    @Query("update UserEntity u set u.yellowCard = u.yellowCard + 1 where u.uid = :uid")
    void addYellow(String uid);

    @Modifying
    @Transactional
    @Query("update UserEntity u set u.redCard = true where u.uid = :uid")
    void ban(String uid);

    @Modifying
    @Transactional
    @Query("update UserEntity u set u.redCard = false where u.uid = :uid")
    void cancelBan(String uid);
}
