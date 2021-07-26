package com.livenow.week1.controller;

import com.livenow.week1.controller.dto.MemberResponseDto;
import com.livenow.week1.controller.dto.MemberSaveRequestDto;
import com.livenow.week1.controller.dto.MemberUpdateRequestDto;

import com.livenow.week1.controller.responsemessage.Message;
import com.livenow.week1.controller.responsemessage.StatusEnum;
import com.livenow.week1.service.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Message> saveMember(@RequestBody MemberSaveRequestDto responseDto, HttpServletResponse response) {
        memberService.save(responseDto);
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable Long id) {
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        message.setData(memberService.findById(id));
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> update(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto) {
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        message.setData(memberService.update(id, requestDto));
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteMember(@PathVariable Long id) {
        memberService.delete(id);
        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
