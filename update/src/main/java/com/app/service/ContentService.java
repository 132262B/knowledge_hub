package com.app.service;

import com.app.entity.Content;
import com.app.entity.Member;
import com.app.repository.ContentRepository;
import com.app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    private final MemberRepository memberRepository;

    public void deleteContentV0(Long memberId) {
        Member member = memberRepository.findById(memberId).get();

        List<Content> contents = member.getContent();
        contentRepository.deleteAllInBatch(contents);
    }

    public void deleteContentV1(Long memberId) {
        Member member = memberRepository.findById(memberId).get();

        member.getContent().forEach(Content::statusChange);
    }

    public void deleteContentV2(Long memberId) {
        Member member = memberRepository.findById(memberId).get();

        List<Long> contentIds = member.getContent().stream()
                .map(Content::getId)
                .collect(Collectors.toList());

        contentRepository.deleteAllContent(contentIds);
    }

    public void deleteContentV3(Long memberId) {
        Member member = memberRepository.findById(memberId).get();

        List<Long> contentIds = member.getContent().stream()
                .map(Content::getId)
                .collect(Collectors.toList());

        contentRepository.deleteAllContentQueryDsl(contentIds);
    }


}
