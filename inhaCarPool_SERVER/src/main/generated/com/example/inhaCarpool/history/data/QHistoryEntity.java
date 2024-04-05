package com.example.inhaCarpool.history.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHistoryEntity is a Querydsl query type for HistoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHistoryEntity extends EntityPathBase<HistoryEntity> {

    private static final long serialVersionUID = 2099931930L;

    public static final QHistoryEntity historyEntity = new QHistoryEntity("historyEntity");

    public final StringPath admin = createString("admin");

    public final StringPath carPoolId = createString("carPoolId");

    public final StringPath endDetailPoint = createString("endDetailPoint");

    public final StringPath endPoint = createString("endPoint");

    public final StringPath endPointName = createString("endPointName");

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> maxMember = createNumber("maxMember", Long.class);

    public final StringPath member1 = createString("member1");

    public final StringPath member2 = createString("member2");

    public final StringPath member3 = createString("member3");

    public final StringPath member4 = createString("member4");

    public final NumberPath<Long> nowMember = createNumber("nowMember", Long.class);

    public final StringPath startDetailPoint = createString("startDetailPoint");

    public final StringPath startPoint = createString("startPoint");

    public final StringPath startPointName = createString("startPointName");

    public final NumberPath<Long> startTime = createNumber("startTime", Long.class);

    public QHistoryEntity(String variable) {
        super(HistoryEntity.class, forVariable(variable));
    }

    public QHistoryEntity(Path<? extends HistoryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHistoryEntity(PathMetadata metadata) {
        super(HistoryEntity.class, metadata);
    }

}

