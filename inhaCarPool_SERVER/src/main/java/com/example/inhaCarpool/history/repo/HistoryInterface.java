package com.example.inhaCarpool.history.repo;


import com.example.inhaCarpool.history.data.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryInterface extends JpaRepository<HistoryEntity, Long>{

    HistoryEntity findByCarPoolId(String carPoolId);

    List<HistoryEntity> findByMember1ContainingOrMember2ContainingOrMember3ContainingOrMember4Containing(String member1, String member2, String member3, String member4);

    Long countByMember1ContainingOrMember2ContainingOrMember3ContainingOrMember4Containing(String member1, String member2, String member3, String member4);

}
