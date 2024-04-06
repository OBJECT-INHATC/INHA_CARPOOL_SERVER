package com.example.inhaCarpool.feedback.repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

/**
 * FeedbackCustomRepositoryImpl
 * QueryDSL 쿼리를 구현
 *
 */
@RequiredArgsConstructor
public class FeedbackCustomRepositoryImpl implements FeedbackCustomRepository{

    private JPAQueryFactory jpaQueryFactory;

    // 쿼리 작성부


}
