package com.app.service;

import com.app.entity.Content;
import com.app.entity.Member;
import com.app.repository.ContentRepository;
import com.app.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ContentServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ContentService contentService;

    private static final String MEMBER_EMAIL = "member@gmail.com";

    @BeforeEach
    void beforeEach() {
        Member member = new Member(MEMBER_EMAIL);
        em.persist(member);

        Content content1 = new Content("게시물1", "내용1", member);
        Content content2 = new Content("게시물2", "내용2", member);
        Content content3 = new Content("게시물3", "내용3", member);

        em.persist(content1);
        em.persist(content2);
        em.persist(content3);

        em.flush();
        em.clear();
    }

    @DisplayName("deleteAllInBatch로 delete")
    @Test
    void deleteContentV0_test() {
        Member member = memberRepository.findByEmail(MEMBER_EMAIL);

        contentService.deleteContentV0(member.getId());

        List<Content> list = contentRepository.findAll();
        assertThat(list.size()).isEqualTo(0);
    }

    @DisplayName("더티 체킹으로 update")
    @Test
    void deleteContentV1_test() {
        Member member = memberRepository.findByEmail(MEMBER_EMAIL);

        contentService.deleteContentV1(member.getId());

        List<Content> list = contentRepository.findAll();
        assertThat(list.size()).isEqualTo(0);
    }

    @DisplayName("JPQL로 update")
    @Test
    void deleteContentV2_test() {
        Member member = memberRepository.findByEmail(MEMBER_EMAIL);

        contentService.deleteContentV2(member.getId());

        List<Content> list = contentRepository.findAll();
        assertThat(list.size()).isEqualTo(0);
    }

    @DisplayName("queryDsl로 update")
    @Test
    void deleteContentV3_test() {
        Member member = memberRepository.findByEmail(MEMBER_EMAIL);

        contentService.deleteContentV3(member.getId());

        List<Content> list = contentRepository.findAll();
        assertThat(list.size()).isEqualTo(0);
    }

}