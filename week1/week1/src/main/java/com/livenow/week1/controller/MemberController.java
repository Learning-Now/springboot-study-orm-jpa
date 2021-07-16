package com.livenow.week1.controller;

<<<<<<< HEAD
import com.livenow.week1.controller.dto.MemberResponseDto;
import com.livenow.week1.controller.dto.MemberUpdateRequestDto;
=======
>>>>>>> 5bb9a61ce753b62c7a05b8ccb37b91928c6042c1
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

<<<<<<< HEAD
    @PostMapping("/save")
=======
    @PostMapping
>>>>>>> 5bb9a61ce753b62c7a05b8ccb37b91928c6042c1
    @ResponseStatus(HttpStatus.OK)
    public void saveMember(@RequestParam String name, @RequestParam int age) {
        memberService.save(name, age);
    }
<<<<<<< HEAD

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto findById(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @PutMapping("/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto) {
        return memberService.update(id, requestDto);
    }

    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMember(@PathVariable Long id) {
        memberService.delete(id);
    }

=======
>>>>>>> 5bb9a61ce753b62c7a05b8ccb37b91928c6042c1
}
