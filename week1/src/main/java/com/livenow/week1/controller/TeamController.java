package com.livenow.week1.controller;

import com.livenow.week1.DTO.MemberAddRequestDto;
import com.livenow.week1.DTO.MemberAddResponseDto;
import com.livenow.week1.DTO.MemberDeleteResponseDto;
import com.livenow.week1.domain.Team;
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

//    @PostMapping
//    @ResponseStatus(HttpStatus.OK)
//    public void save(@RequestParam String name) {
//        teamService.save(name);
//    }

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.OK)
    public void addMember(@RequestParam Long teamId, @RequestParam Long memberId) {
        teamService.addMember(teamId, memberId);
    }
    @RequestMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteMember(@RequestParam String teamId) {
        teamService.deleteMember(teamId);
    }

    @DeleteMapping("{/id}")
    public void setDeleted(String id) {
        TeamService.deleteMember(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public MemberDeleteResponseDto removeMember(@PathVariable Long id) {
        return teamService.deleteMember(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public MemberAddResponseDto addMember(@RequestBody MemberAddRequestDto memberAddRequestDto) {
        return teamService.addMember(memberAddRequestDto);
    }


}