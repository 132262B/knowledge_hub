package study.querydsl.repository;


import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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

    private BooleanExpression eqUsername(String username) {
        return hasText(username) ? member.username.eq(username) : null;
    }

    private BooleanExpression eqAge(Integer age) {
        return age == null ? null : member.age.eq(age);
    }
}
