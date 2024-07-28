package com.example.inhacarpool.carpool.infrastructure;

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
import lombok.NoArgsConstructor;

@Table(name = "carpool")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
}
