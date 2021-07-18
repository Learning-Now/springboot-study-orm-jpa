package com.livenow.week1.controller;

import com.livenow.week1.domain.Member;
import com.livenow.week1.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void saveMember(@RequestParam String name, @RequestParam int age) {
        memberService.save(name, age);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Member> findMembers() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Member findMember(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @DeleteMapping("/{id}}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMember(@PathVariable Long id) {
        memberService.delete(id);
    }
}
