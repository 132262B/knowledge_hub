package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberTeamDto {

    private String username;

    private Integer age;

    private String teamName;

    @QueryProjection
    public MemberTeamDto(String username, Integer age, String teamName) {
        this.username = username;
        this.age = age;
        this.teamName = teamName;
    }
}
