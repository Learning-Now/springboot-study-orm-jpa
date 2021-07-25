package com.livenow.week1.controller.team;

import com.livenow.week1.controller.BasicResponseDto;
import com.livenow.week1.controller.team.dto.TeamSaveRequestDto;
import com.livenow.week1.controller.team.dto.TeamSaveResponseDto;
import com.livenow.week1.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<BasicResponseDto<TeamSaveResponseDto>> saveTeam(@RequestBody TeamSaveRequestDto requestDto) {
        return ResponseEntity.ok(BasicResponseDto.of(teamService.save(requestDto)));
    }
}
