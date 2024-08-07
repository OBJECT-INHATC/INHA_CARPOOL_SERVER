package com.example.inhacarpool.carpool.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.inhacarpool.common.FixedClockHolder;
import com.example.inhacarpool.common.port.ClockHolder;
import com.example.inhacarpool.user.domain.User;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarpoolTest {

    @Test
    @DisplayName("카풀은 CarpoolCreate 객체로 생성할 수 있다.")
    public void carpoolCreateTest() {
        //given
        CarpoolCreate carpoolCreate = CarpoolCreate.builder()
                .id("1234567890123456789012345678")
                .admin("adminUserId")
                .maxMember(4)
                .gender(GenderType.ALL)
                .startTime(1612137600L)
                .shortStartPoint("출발지 요약 주소")
                .startPointCoordinate("출발지 위도 경도")
                .detailStartPoint("출발지 상세 주소")
                .shortEndPoint("도착지 요약 주소")
                .endPointCoordinate("도착지 위도 경도")
                .detailEndPoint("도착지 상세 주소")
                .build();
        LocalDateTime fixedTime = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        User user = User.builder()
                .uid("adminUserId")
                .nickname("admin")
                .email("202045090@itc.ac.kr")
                .yellowCard(0)
                .redCard(false)
                .createdAt(fixedTime)
                .updatedAt(fixedTime)
                .build();
        ClockHolder clockHolder = new FixedClockHolder(fixedTime);

        //when
        Carpool carpool = Carpool.from(carpoolCreate, user, clockHolder);

        //then
        assertThat(carpool.getId()).isEqualTo("1234567890123456789012345678");
        assertThat(carpool.getAdmin()).isEqualTo(user);
        assertThat(carpool.getNowMember()).isEqualTo(1);
        assertThat(carpool.getMaxMember()).isEqualTo(4);
        assertThat(carpool.getGender()).isEqualTo(GenderType.ALL);
        assertThat(carpool.getStartTime()).isEqualTo(1612137600L);
        assertThat(carpool.getShortStartPoint()).isEqualTo("출발지 요약 주소");
        assertThat(carpool.getStartPointCoordinate()).isEqualTo("출발지 위도 경도");
        assertThat(carpool.getDetailStartPoint()).isEqualTo("출발지 상세 주소");
        assertThat(carpool.getShortEndPoint()).isEqualTo("도착지 요약 주소");
        assertThat(carpool.getEndPointCoordinate()).isEqualTo("도착지 위도 경도");
        assertThat(carpool.getDetailEndPoint()).isEqualTo("도착지 상세 주소");
        assertThat(carpool.getCreatedAt()).isEqualTo(fixedTime);
        assertThat(carpool.getUpdatedAt()).isEqualTo(fixedTime);
        assertThat(carpool.isStatus()).isTrue();
//        assertThat(carpool.getEndTime()).isEqualTo(1612137600L + 3600 * 3);
    }
}
