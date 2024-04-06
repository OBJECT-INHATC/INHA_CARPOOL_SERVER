package com.example.inhaCarpool.feedback.data.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFeedbackEntity is a Querydsl query type for FeedbackEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFeedbackEntity extends EntityPathBase<FeedbackEntity> {

    private static final long serialVersionUID = 1084628843L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFeedbackEntity feedbackEntity = new QFeedbackEntity("feedbackEntity");

    public final StringPath content = createString("content");

    public final NumberPath<Long> created_at = createNumber("created_at", Long.class);

    public final NumberPath<Long> feedbackId = createNumber("feedbackId", Long.class);

    public final EnumPath<FeedbackType> feedbackType = createEnum("feedbackType", FeedbackType.class);

    public final com.example.inhaCarpool.user.data.QUserEntity userEntity;

    public QFeedbackEntity(String variable) {
        this(FeedbackEntity.class, forVariable(variable), INITS);
    }

    public QFeedbackEntity(Path<? extends FeedbackEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFeedbackEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFeedbackEntity(PathMetadata metadata, PathInits inits) {
        this(FeedbackEntity.class, metadata, inits);
    }

    public QFeedbackEntity(Class<? extends FeedbackEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userEntity = inits.isInitialized("userEntity") ? new com.example.inhaCarpool.user.data.QUserEntity(forProperty("userEntity")) : null;
    }

}

