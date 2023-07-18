package tdd.practice.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tdd.practice.board.dto.Member;
import tdd.practice.board.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/password/find")
    public Member findPassword(@RequestBody Member member) {
        Member findMember = memberService.findMember(member.getMemberNo());
        return findMember;
    }

    @PostMapping("/join")
    public String join(@RequestBody Member member) {
        memberService.save(member);
        return "OK";
    }

    @PutMapping("/password/modify")
    public String modifyMemberInfo(@RequestBody Member member) {
        memberService.update(member);
        return "OK";
    }
}
