package com.example.inhacarpool.history.controller.request;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.inhacarpool.history.domain.History;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HistoryCreateRequestTest {

    @Test
    @DisplayName("신고내역 생성 요청 객체 HistoryCreateRequest를 생성할 수 있다.")
    public void toTest() {
        //given
        HistoryCreateRequest historyCreateRequest = HistoryCreateRequest.builder()
                .carPoolId("1234567890123456789012345678")
                .admin("yeong0jae")
                .member1("yeong0jae")
                .member2("yeong0jae")
                .member3("yeong0jae")
                .member4("yeong0jae")
                .nowMember(1L)
                .maxMember(4L)
                .startDetailPoint("startDetailPoint")
                .startPoint("startPoint")
                .startPointName("startPointName")
                .startTime(1L)
                .endDetailPoint("endDetailPoint")
                .endPoint("endPoint")
                .endPointName("endPointName")
                .gender("무관")
                .build();

        //when
        History history = historyCreateRequest.to();

        //then
        assertThat(history.getCarPoolId()).isEqualTo("1234567890123456789012345678");
        assertThat(history.getAdmin()).isEqualTo("yeong0jae");
        assertThat(history.getMember1()).isEqualTo("yeong0jae");
        assertThat(history.getMember2()).isEqualTo("yeong0jae");
        assertThat(history.getMember3()).isEqualTo("yeong0jae");
        assertThat(history.getMember4()).isEqualTo("yeong0jae");
        assertThat(history.getNowMember()).isEqualTo(1L);
        assertThat(history.getMaxMember()).isEqualTo(4L);
        assertThat(history.getStartDetailPoint()).isEqualTo("startDetailPoint");
        assertThat(history.getStartPoint()).isEqualTo("startPoint");
        assertThat(history.getStartPointName()).isEqualTo("startPointName");
        assertThat(history.getStartTime()).isEqualTo(1L);
        assertThat(history.getEndDetailPoint()).isEqualTo("endDetailPoint");
        assertThat(history.getEndPoint()).isEqualTo("endPoint");
        assertThat(history.getEndPointName()).isEqualTo("endPointName");
        assertThat(history.getGender()).isEqualTo("무관");
    }
}
