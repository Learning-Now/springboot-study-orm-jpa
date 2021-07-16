package com.livenow.week1.controller;

import com.livenow.week1.domain.Member;
import com.livenow.week1.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Controller에 ResponseBody가 결합된 어노테이션
// 컨트롤러 클래스 하위 메서드에 문자열과 JSON 전송가능
@RequestMapping("/api/v1/members")
//들어온 요청을 특정 메서드와 매핑하기 위해 사용하는 것이 @RequestMapping이다
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

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void inquire(@RequestParam String name, @RequestParam int age)
    {
        memberService.inquire(name,age);
    }

    @GetMapping("/member")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("member",members);
        return "/member";
    }
    @RequestMapping(value="delete", method=RequestMethod.POST)
    String edit(@RequestParam Integer id){
        MemberService.delete(id);
        return "/member";
    }
}