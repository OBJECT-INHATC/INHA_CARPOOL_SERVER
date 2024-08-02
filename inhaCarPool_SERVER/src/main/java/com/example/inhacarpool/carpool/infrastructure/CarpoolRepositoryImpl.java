package com.example.inhacarpool.carpool.infrastructure;

import com.example.inhacarpool.carpool.domain.Carpool;
import com.example.inhacarpool.carpool.service.port.CarpoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CarpoolRepositoryImpl implements CarpoolRepository {

    private final CarpoolJpaRepository carpoolJpaRepository;

    @Override
    public Carpool save(Carpool carpool) {
        return carpoolJpaRepository.save(CarpoolEntity.from(carpool)).toModel();
    }

    @Override
    public Carpool findCarpool(String carpoolId) {
        return carpoolJpaRepository.findById(carpoolId).map(CarpoolEntity::toModel).orElse(null);
    }
}
