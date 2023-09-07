package com.app.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DynamicInsert
@Where(clause = "status = 'Y'")
@SQLDelete(sql = "UPDATE member SET status = 'N' WHERE member_id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, length = 50, nullable = false)
    private String email;

    @ColumnDefault("'Y'")
    @Column(length = 1)
    private String status;

    @OneToMany(mappedBy = "member")
    private List<Content> content = new ArrayList<>();

    public Member(String email) {
        this.email = email;
    }
}
