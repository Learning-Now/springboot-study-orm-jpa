package com.livenow.week1.controller;

import com.livenow.week1.controller.dto.MemberAddRequestDto;
import com.livenow.week1.controller.dto.MemberAddResponseDto;
import com.livenow.week1.controller.dto.MemberDeleteResponseDto;
import com.livenow.week1.controller.dto.TeamSaveResponseDto;
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
    public TeamSaveResponseDto save(@RequestParam String name) {
        return teamService.save(name);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public MemberAddResponseDto addMember(@RequestBody MemberAddRequestDto memberAddRequestDto) {
        return teamService.addMember(memberAddRequestDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public MemberDeleteResponseDto removeMember(@PathVariable Long id) {
        return teamService.deleteMember(id);
    }
}
