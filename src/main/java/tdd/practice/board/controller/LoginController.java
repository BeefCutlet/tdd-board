package tdd.practice.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tdd.practice.board.dto.Member;
import tdd.practice.board.service.MemberService;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @PostMapping("/login")
    public String login(@RequestBody Member member) {

        return "";
    }

    @GetMapping("/password/find")
    public Member findPassword(@RequestBody Member member) {
        Member findMember = memberService.findMember(member.getMemberNo());
        return findMember;
    }

    @PutMapping("/password/modify")
    public String modifyMemberInfo(@RequestBody Member member) {
        memberService.update(member);
        return "OK";
    }
}
