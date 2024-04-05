package com.example.inhaCarpool.report.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReportEntity is a Querydsl query type for ReportEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportEntity extends EntityPathBase<ReportEntity> {

    private static final long serialVersionUID = -1104972690L;

    public static final QReportEntity reportEntity = new QReportEntity("reportEntity");

    public final StringPath carPoolId = createString("carPoolId");

    public final StringPath content = createString("content");

    public final DateTimePath<java.util.Date> reportDate = createDateTime("reportDate", java.util.Date.class);

    public final StringPath reportedUser = createString("reportedUser");

    public final StringPath reporter = createString("reporter");

    public final NumberPath<Long> reportIdx = createNumber("reportIdx", Long.class);

    public final StringPath reportType = createString("reportType");

    public final BooleanPath status = createBoolean("status");

    public QReportEntity(String variable) {
        super(ReportEntity.class, forVariable(variable));
    }

    public QReportEntity(Path<? extends ReportEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReportEntity(PathMetadata metadata) {
        super(ReportEntity.class, metadata);
    }

}

