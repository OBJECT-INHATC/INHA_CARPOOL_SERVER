package com.example.inhacarpool.carpool.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarpoolJpaRepository extends JpaRepository<CarpoolEntity, String> {
}
