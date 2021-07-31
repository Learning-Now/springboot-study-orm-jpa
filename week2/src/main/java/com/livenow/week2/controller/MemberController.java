package com.livenow.week2.controller;

import com.livenow.week2.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
