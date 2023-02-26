package com.app.entity;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;

@Getter
@Entity
@DynamicInsert
@Where(clause = "status = 'Y'")
@SQLDelete(sql = "UPDATE content SET status = 'N' WHERE content_id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 1)
    @ColumnDefault("'Y'")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Content(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void statusChange() {
        this.status = "N";
    }
}
