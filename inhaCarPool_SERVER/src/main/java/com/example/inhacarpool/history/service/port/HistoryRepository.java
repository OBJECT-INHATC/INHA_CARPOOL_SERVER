package com.example.inhacarpool.history.service.port;

import com.example.inhacarpool.history.domain.History;

public interface HistoryRepository {

    History save(History history);
}
