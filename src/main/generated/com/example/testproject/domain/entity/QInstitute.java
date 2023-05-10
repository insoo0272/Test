package com.example.testproject.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInstitute is a Querydsl query type for Institute
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInstitute extends EntityPathBase<Institute> {

    private static final long serialVersionUID = -225321092L;

    public static final QInstitute institute = new QInstitute("institute");

    public final StringPath code = createString("code");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QInstitute(String variable) {
        super(Institute.class, forVariable(variable));
    }

    public QInstitute(Path<? extends Institute> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInstitute(PathMetadata metadata) {
        super(Institute.class, metadata);
    }

}

