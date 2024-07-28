package com.example.inhacarpool.carpool.infrastructure;

import com.example.inhacarpool.carpool.service.port.CarpoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CarpoolRepositoryImpl implements CarpoolRepository {

    private final CarpoolJpaRepository carpoolJpaRepository;

}
