package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members")
    public Page<Member> get(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }


    @PostConstruct
    public void init() {
        List<Member> list = new ArrayList<>();
        for (int i=0; i<100; i++) {
            list.add(new Member("member"+i,20,null));
        }
        memberRepository.saveAll(list);
    }

}
