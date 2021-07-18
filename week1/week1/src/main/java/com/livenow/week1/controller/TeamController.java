package com.livenow.week1.controller;

import com.livenow.week1.controller.dto.MemberAddRequestDto;
import com.livenow.week1.controller.dto.MemberDeleteRequestDto;
import com.livenow.week1.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestParam String name) {
        teamService.save(name);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void addMember(@RequestBody MemberAddRequestDto memberAddRequestDto) {
        teamService.addMember(memberAddRequestDto.getTeamId(), memberAddRequestDto.getMemberId());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteMember(@RequestBody MemberDeleteRequestDto memberDeleteRequestDto) {
        teamService.deleteMember(memberDeleteRequestDto.getMemberId());
    }
}
