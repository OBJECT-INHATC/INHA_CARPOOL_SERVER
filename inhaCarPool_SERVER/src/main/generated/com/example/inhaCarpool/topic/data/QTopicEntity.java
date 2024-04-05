package com.example.inhaCarpool.topic.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTopicEntity is a Querydsl query type for TopicEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTopicEntity extends EntityPathBase<TopicEntity> {

    private static final long serialVersionUID = -80644038L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTopicEntity topicEntity = new QTopicEntity("topicEntity");

    public final StringPath carId = createString("carId");

    public final NumberPath<Long> topicIdx = createNumber("topicIdx", Long.class);

    public final com.example.inhaCarpool.user.data.QUserEntity users;

    public QTopicEntity(String variable) {
        this(TopicEntity.class, forVariable(variable), INITS);
    }

    public QTopicEntity(Path<? extends TopicEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTopicEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTopicEntity(PathMetadata metadata, PathInits inits) {
        this(TopicEntity.class, metadata, inits);
    }

    public QTopicEntity(Class<? extends TopicEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.users = inits.isInitialized("users") ? new com.example.inhaCarpool.user.data.QUserEntity(forProperty("users")) : null;
    }

}

