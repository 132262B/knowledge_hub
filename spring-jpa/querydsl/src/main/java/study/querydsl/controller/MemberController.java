package study.querydsl.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.querydsl.dto.MemberSearchRequest;
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
}
