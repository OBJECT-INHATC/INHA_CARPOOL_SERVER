package com.example.inhacarpool.history.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryJpaRepository extends JpaRepository<HistoryEntity, Long> {

    HistoryEntity findByCarPoolId(String carPoolId);

    List<HistoryEntity> findByMember1ContainingOrMember2ContainingOrMember3ContainingOrMember4Containing(String member1,
                                                                                                         String member2,
                                                                                                         String member3,
                                                                                                         String member4);

    Long countByMember1ContainingOrMember2ContainingOrMember3ContainingOrMember4Containing(String member1,
                                                                                           String member2,
                                                                                           String member3,
                                                                                           String member4);

}
