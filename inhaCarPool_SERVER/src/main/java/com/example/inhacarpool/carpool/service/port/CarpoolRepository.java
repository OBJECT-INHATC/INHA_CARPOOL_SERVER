package com.example.inhacarpool.carpool.service.port;

import com.example.inhacarpool.carpool.domain.Carpool;

public interface CarpoolRepository {
    Carpool save(Carpool carpool);
}
