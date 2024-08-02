package com.example.inhacarpool.carpool.infrastructure;

import com.example.inhacarpool.carpool.domain.Carpool;
import com.example.inhacarpool.carpool.domain.GenderType;
import com.example.inhacarpool.user.infrastructure.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "carpool")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CarpoolEntity {

    @Id
    private String id; // firebase carpoolId와 동일

    @ManyToOne
    @JoinColumn(name = "admin")
    private UserEntity admin;

    @Column(columnDefinition = "int default 1")
    private int nowMember;

    private int maxMember;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    private Long startTime; // epoch time

    private String shortStartPoint;

    private String startPointCoordinate;

    private String detailStartPoint;

    private String shortEndPoint;

    private String endPointCoordinate;

    private String detailEndPoint;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean status;

    private Long endTime; // epoch time

    public static CarpoolEntity from(Carpool carpool) {
        CarpoolEntity carpoolEntity = new CarpoolEntity();
        carpoolEntity.id = carpool.getId();
        carpoolEntity.admin = UserEntity.from(carpool.getAdmin());
        carpoolEntity.nowMember = carpool.getNowMember();
        carpoolEntity.maxMember = carpool.getMaxMember();
        carpoolEntity.gender = carpool.getGender();
        carpoolEntity.startTime = carpool.getStartTime();
        carpoolEntity.shortStartPoint = carpool.getShortStartPoint();
        carpoolEntity.startPointCoordinate = carpool.getStartPointCoordinate();
        carpoolEntity.detailStartPoint = carpool.getDetailStartPoint();
        carpoolEntity.shortEndPoint = carpool.getShortEndPoint();
        carpoolEntity.endPointCoordinate = carpool.getEndPointCoordinate();
        carpoolEntity.detailEndPoint = carpool.getDetailEndPoint();
        carpoolEntity.createdAt = carpool.getCreatedAt();
        carpoolEntity.updatedAt = carpool.getUpdatedAt();
        carpoolEntity.status = carpool.isStatus();
        carpoolEntity.endTime = carpool.getEndTime();
        return carpoolEntity;
    }

    public Carpool toModel() {
        return Carpool.builder()
                .id(id)
                .admin(admin.toModel())
                .nowMember(nowMember)
                .maxMember(maxMember)
                .gender(gender)
                .startTime(startTime)
                .shortStartPoint(shortStartPoint)
                .startPointCoordinate(startPointCoordinate)
                .detailStartPoint(detailStartPoint)
                .shortEndPoint(shortEndPoint)
                .endPointCoordinate(endPointCoordinate)
                .detailEndPoint(detailEndPoint)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .status(status)
                .endTime(endTime)
                .build();
    }
}
