package study.querydsl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberTeamDto>> search(@RequestParam(required = false) String username,
                                                      @RequestParam(required = false) Integer age) {
        return ResponseEntity.ok(memberService.search(username, age));
    }

    @GetMapping("/v1/page")
    public ResponseEntity<Page<MemberTeamDto>> searchPageSimple(@RequestParam(required = false) String username,
                                                                @RequestParam(required = false) Integer age,
                                                                Pageable pageable) {
        return ResponseEntity.ok(memberService.searchPageSimple(username, age, pageable));
    }

    @GetMapping("/v2/page")
    public ResponseEntity<Page<MemberTeamDto>> searchPageComplex(String username, Integer age, Pageable pageable) {
        return ResponseEntity.ok(memberService.searchPageComplex(username, age, pageable));
    }
}
