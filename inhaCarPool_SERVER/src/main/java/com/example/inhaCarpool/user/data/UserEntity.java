package com.example.inhaCarpool.user.data;

import com.example.inhaCarpool.topic.data.TopicEntity;
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

@Data
@NoArgsConstructor
@Table(name = "user")
@Entity
public class UserEntity {

    // 유저가 회원가입 시 생성(저장)되는 엔티티

    @Id
    @Column(name = "uid")
    private String uid; // 사용자 ID (파이어베이스 uid를 식별자로)

    @Column(name = "nickname")
    private String nickname; // 사용자 닉네임

    @Column(name = "email")
    private String email; // 사용자 이메일

    @Column(name = "yellowCard", columnDefinition = "int default 0") // default 0
    private int yellowCard; // 경고 횟수

    @Column(name = "redCard", columnDefinition = "boolean default false") // default false
    private boolean redCard; // 정지 여부

//    @OneToMany(mappedBy = "reporter")
//    private List<ReportEntity> reporter; // 신고한 리스트에 접근할 때 사용 (보통 사용 안함)
//
//    @OneToMany(mappedBy = "reportedUser")
//    private List<ReportEntity> reportedUser; // 신고 당한 리스트에 접근할 때 사용 (보통 사용 안함)

    @OneToMany(mappedBy = "users")
    private List<TopicEntity> users; // 유저가 참여한 토픽(채팅방) 리스트에 접근할 때 사용 (보통 사용 안함)

    @Builder
    public UserEntity(String uid, String nickname, String email) { // 유저 엔티티의 생성자
        this.uid = uid;
        this.nickname = nickname;
        this.email = email;
    }

}


