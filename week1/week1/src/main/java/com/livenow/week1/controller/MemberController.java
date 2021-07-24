package com.livenow.week1.controller;

import com.livenow.week1.controller.dto.MemberDeleteResponseDto;
import com.livenow.week1.controller.dto.MemberFindResponseDto;
import com.livenow.week1.controller.dto.MemberSaveRequestDto;
import com.livenow.week1.controller.dto.MemberSaveResponseDto;
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
    public MemberSaveResponseDto saveMember(@RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        return memberService.save(memberSaveRequestDto);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberFindResponseDto> findMembers() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberFindResponseDto findMember(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @DeleteMapping("/{id}}")
    @ResponseStatus(HttpStatus.OK)
    public MemberDeleteResponseDto deleteMember(@PathVariable Long id) {
        return memberService.delete(id);
    }
}
