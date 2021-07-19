package com.livenow.week1.controller;

import com.livenow.week1.controller.dto.MemberResponseDto;
import com.livenow.week1.controller.dto.MemberSaveRequestDto;
import com.livenow.week1.controller.dto.MemberUpdateRequestDto;

import com.livenow.week1.service.MemberService;
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
    public void saveMember(@RequestBody MemberSaveRequestDto responseDto) {
        memberService.save(responseDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto findById(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto) {
        return memberService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMember(@PathVariable Long id) {
        memberService.delete(id);
    }

}
