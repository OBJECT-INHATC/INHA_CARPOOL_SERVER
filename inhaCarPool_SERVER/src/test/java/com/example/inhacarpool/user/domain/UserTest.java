package com.example.inhacarpool.user.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.inhacarpool.common.FixedClockHolder;
import com.example.inhacarpool.common.port.ClockHolder;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    @DisplayName("유저는 UserCreate 객체로 생성할 수 있다.")
    public void userCreateTest() {
        //given
        UserCreate userCreate = UserCreate.builder()
                .uid("1234567890123456789012345678")
                .nickname("yeong0jae")
                .email("202045090@itc.ac.kr")
                .build();
        LocalDateTime fixedTime = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        ClockHolder clockHolder = new FixedClockHolder(fixedTime);

        //when
        User user = User.from(userCreate, clockHolder);

        //then
        assertThat(user.getUid()).isEqualTo("1234567890123456789012345678");
        assertThat(user.getNickname()).isEqualTo("yeong0jae");
        assertThat(user.getEmail()).isEqualTo("202045090@itc.ac.kr");
        assertThat(user.getYellowCard()).isEqualTo(0);
        assertThat(user.isRedCard()).isFalse();
        assertThat(user.getCreatedAt()).isEqualTo(fixedTime);
        assertThat(user.getUpdatedAt()).isEqualTo(fixedTime);
    }

    @Test
    @DisplayName("유저의 경고 횟수를 초기화할 수 있다.")
    public void resetYellowTest() {
        //given
        User user = User.builder()
                .uid("1234567890123456789012345678")
                .nickname("yeong0jae")
                .email("202045090@itc.ac.kr")
                .yellowCard(1)
                .redCard(false)
                .createdAt(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
                .updatedAt(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
                .build();

        //when
        User resetUser = User.resetYellow(user);

        //then
        assertThat(resetUser.getYellowCard()).isEqualTo(0);
    }
}
