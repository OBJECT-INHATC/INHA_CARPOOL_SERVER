package com.example.inhaCarpool.entity;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *    User DB 엔티티
 *
 *   @version          1.00    2023.09.01
 *   @author           이상훈
 */

@Tag(name = "예제 API", description = "Swagger 테스트용 API")
@Data
@NoArgsConstructor
@Table(name = "user")
@Entity
public class UserEntity {

    @Id
    @Column(name = "uid")
    private String uid; // 사용자 ID (파이어베이스 uid)

    @Column(name = "nickname")
    private String nickname; //

    @OneToMany(mappedBy = "reporter")
    private List<ReportEntity> reporter;

    @OneToMany(mappedBy = "reportedUser")
    private List<ReportEntity> reportedUser;

    @OneToMany(mappedBy = "users")
    private List<TopicEntity> users;

    @Builder
    public UserEntity(String uid, String nickname) {
        this.uid = uid;
        this.nickname = nickname;
    }



}


