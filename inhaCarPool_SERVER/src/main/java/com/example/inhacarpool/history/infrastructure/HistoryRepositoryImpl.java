package com.example.inhacarpool.history.infrastructure;

import com.example.inhacarpool.history.domain.History;
import com.example.inhacarpool.history.service.port.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepository {
    private final HistoryJpaRepository historyJpaRepository;

    @Override
    public History save(History history) {
        return historyJpaRepository.save(HistoryEntity.from(history)).toModel();
    }
}
