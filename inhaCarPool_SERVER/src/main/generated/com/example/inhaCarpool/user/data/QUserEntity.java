package com.example.inhaCarpool.user.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = 262977372L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final StringPath createdAt = createString("createdAt");

    public final StringPath email = createString("email");

    public final StringPath nickname = createString("nickname");

    public final BooleanPath redCard = createBoolean("redCard");

    public final StringPath uid = createString("uid");

    public final ListPath<com.example.inhaCarpool.topic.data.TopicEntity, com.example.inhaCarpool.topic.data.QTopicEntity> users = this.<com.example.inhaCarpool.topic.data.TopicEntity, com.example.inhaCarpool.topic.data.QTopicEntity>createList("users", com.example.inhaCarpool.topic.data.TopicEntity.class, com.example.inhaCarpool.topic.data.QTopicEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> yellowCard = createNumber("yellowCard", Integer.class);

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

