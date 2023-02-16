package study.querydsl.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hello {

    @Id
    @GeneratedValue
    private Long id;
}
