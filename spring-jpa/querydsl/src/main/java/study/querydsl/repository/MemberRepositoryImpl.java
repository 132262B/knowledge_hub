package study.querydsl.repository;


import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.dto.QMemberTeamDto;

import java.util.List;

import static org.springframework.util.StringUtils.*;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;


@Service
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberTeamDto> search(String username, Integer age) {
        return queryFactory
                .select(new QMemberTeamDto(member.username, member.age, team.name))
                .from(member)
                .leftJoin(member.team, team)
                .where(eqUsername(username),
                        eqAge(age))
                .fetch();
    }

    @Override
    public Page<MemberTeamDto> searchPageSimple(String username, Integer age, Pageable pageable) {
        List<MemberTeamDto> content = queryFactory
                .select(new QMemberTeamDto(member.username, member.age, team.name))
                .from(member)
                .leftJoin(member.team, team)
                .where(eqUsername(username),
                        eqAge(age))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long count = queryFactory
                .select(member.count())
                .from(member)
                .leftJoin(member.team, team)
                .where(eqUsername(username),
                        eqAge(age))
                .fetchFirst();

        return new PageImpl<>(content, pageable, count);
    }

    @Override
    public Page<MemberTeamDto> searchPageComplex(String username, Integer age, Pageable pageable) {
        List<MemberTeamDto> content = queryFactory
                .select(new QMemberTeamDto(member.username, member.age, team.name))
                .from(member)
                .leftJoin(member.team, team)
                .where(eqUsername(username),
                        eqAge(age))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(member.count())
                .from(member)
                .leftJoin(member.team, team)
                .where(eqUsername(username),
                        eqAge(age));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchFirst);
    }

    private BooleanExpression eqUsername(String username) {
        return hasText(username) ? member.username.eq(username) : null;
    }

    private BooleanExpression eqAge(Integer age) {
        return age == null ? null : member.age.eq(age);
    }
}
