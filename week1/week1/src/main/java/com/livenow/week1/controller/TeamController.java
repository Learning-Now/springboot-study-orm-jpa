package com.livenow.week1.controller;

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

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.OK)
    public void addMember(@RequestParam Long teamId, @RequestParam Long memberId) {
        teamService.addMember(teamId, memberId);
    }
<<<<<<< HEAD

=======
>>>>>>> 5bb9a61ce753b62c7a05b8ccb37b91928c6042c1
}
