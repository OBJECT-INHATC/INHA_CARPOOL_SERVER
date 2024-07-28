package com.example.inhacarpool.carpool.infrastructure;

import com.example.inhacarpool.carpool.domain.Carpool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarpoolJpaRepository extends JpaRepository<Carpool, Long> {
}
