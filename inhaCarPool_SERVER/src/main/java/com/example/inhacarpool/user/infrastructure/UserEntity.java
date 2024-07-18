package com.example.inhacarpool.user.infrastructure;

import com.example.inhacarpool.topic.data.TopicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Table(name = "user")
@Entity
@Data
public class UserEntity {

    // 유저가 회원가입 시 생성(저장)되는 엔티티

    @Id
    @Column(name = "id")
    private String id; // 사용자 ID (파이어베이스 uid를 식별자로)

    @Column(name = "nickname")
    private String nickname; // 사용자 닉네임

    @Column(name = "email")
    private String email; // 사용자 이메일

    @Column(name = "yellowCard", columnDefinition = "int default 0") // default 0
    private int yellowCard; // 경고 횟수

    @Column(name = "redCard", columnDefinition = "boolean default false") // default false
    private boolean redCard; // 정지 여부

    @Column(name = "createdAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private String createdAt; // 생성시간: 자동으로 현재 시간으로 설정

    @OneToMany(mappedBy = "users")
    private List<TopicEntity> topics; // 유저가 참여한 토픽(채팅방) 리스트에 접근할 때 사용 (보통 사용 안함)

    // 신규 유저 저장 시 사용
    @Builder
    public UserEntity(String id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }

    /**
     * @deprecated 이미 존재하는 유저 연관 엔티티 저장 시 사용
     */
    @Builder(builderMethodName = "relationBuilder")
    public UserEntity(String id, String nickname, String email, int yellowCard, boolean redCard, String createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.yellowCard = yellowCard;
        this.redCard = redCard;
        this.createdAt = createdAt;
    }

    // resetYellowCard: 경고 횟수 초기화
    public void resetYellowCard() {
        this.yellowCard = 0;
    }
}


