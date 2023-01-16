package jpabook.jpashop.dto;

import jpabook.jpashop.api.CreateMemberResponse;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/member")
    public Result<List<MemberDto>> selectMember() {
        List<Member> findMembers = memberService.findMembers();

        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());

        return new Result<>((long) collect.size(), collect);
    }

    @PostMapping("/api/member")
    public CreateMemberResponse saveMember(@RequestBody @Valid CreateMemberRequest request) {

        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);

        return new CreateMemberResponse(id);

    }

    @PutMapping("/api/member/{id}")
    public UpdateMemberResponse updateMember(@PathVariable Long id,
                                             @RequestBody @Valid UpdateMemberRequest request) {

        memberService.update(id, request.getName());

        return new UpdateMemberResponse(id, request.getName());
    }


}
