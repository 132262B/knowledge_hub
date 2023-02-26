package com.app.repository;

import com.app.entity.QContent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.entity.QContent.*;

@Repository
@RequiredArgsConstructor
public class ContentRepositoryCustomImpl implements ContentRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public void deleteAllContentQueryDsl(List<Long> contentIds) {
        queryFactory
                .update(content1)
                .set(content1.status, "N")
                .where(content1.status.eq("Y"),
                        content1.id.in(contentIds))
                .execute();
    }
}
