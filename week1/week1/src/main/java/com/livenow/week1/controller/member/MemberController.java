package com.livenow.week1.controller.member;

import com.livenow.week1.controller.BasicResponseDto;
import com.livenow.week1.controller.member.dto.*;
import com.livenow.week1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<BasicResponseDto<MembersResponseDto>> findAllMember() {
        return ResponseEntity.ok(BasicResponseDto.of(memberService.findAllMember()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasicResponseDto<MemberResponseDto>> findMember(@PathVariable("id") Long memberId) {
        return ResponseEntity.ok(BasicResponseDto.of(memberService.findMember(memberId)));
    }


    @PostMapping
    public ResponseEntity<BasicResponseDto<MemberSaveResponseDto>> saveMember(@RequestBody MemberSaveRequestDto requestDto) {
        return ResponseEntity.ok(BasicResponseDto.of(memberService.saveMember(requestDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BasicResponseDto<MemberDeleteResponseDto>> deleteMember(@PathVariable("id") Long memberId) {
        return ResponseEntity.ok(BasicResponseDto.of(memberService.deleteMember(memberId)));
    }
}
