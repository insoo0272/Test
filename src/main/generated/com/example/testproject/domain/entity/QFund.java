package com.example.testproject.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFund is a Querydsl query type for Fund
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFund extends EntityPathBase<Fund> {

    private static final long serialVersionUID = -1131939586L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFund fund = new QFund("fund");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QInstitute institute;

    public final NumberPath<Integer> month = createNumber("month", Integer.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QFund(String variable) {
        this(Fund.class, forVariable(variable), INITS);
    }

    public QFund(Path<? extends Fund> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFund(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFund(PathMetadata metadata, PathInits inits) {
        this(Fund.class, metadata, inits);
    }

    public QFund(Class<? extends Fund> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.institute = inits.isInitialized("institute") ? new QInstitute(forProperty("institute")) : null;
    }

}

