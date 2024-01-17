package com.example.demo.controller;

import com.example.demo.exception.TeamNotFoundException;
import com.example.demo.repository.TeamRepository;
import com.example.demo.model.Team;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamRestController {

    private final TeamRepository repository;

    TeamRestController(TeamRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Team> all() {
        return repository.findAll();
    }

    @PostMapping
    Team newTeam(@RequestBody Team newTeam) {
        return repository.save(newTeam);
    }

    @GetMapping("/{team_id}")
    Team one(@PathVariable Long team_id) {
        return repository.findById(team_id)
                .orElseThrow(() -> new TeamNotFoundException(team_id));
    }

    @PutMapping("/{team_id}")
    Team updateTeam(@RequestBody Team updatedTeam, @PathVariable Long team_id) {
        return repository.findById(team_id)
                .map(team -> {
                    team.setName(updatedTeam.getName());
                    // Mettez à jour d'autres champs si nécessaire
                    return repository.save(team);
                })
                .orElseThrow(() -> new TeamNotFoundException(team_id));
    }

    @PatchMapping("/{team_id}")
    Team partiallyUpdateTeam(@RequestBody Team updatedTeam, @PathVariable Long team_id) {
        return repository.findById(team_id)
                .map(team -> {
                    if (updatedTeam.getName() != null) {
                        team.setName(updatedTeam.getName());
                    }
                    // Mettez à jour d'autres champs si nécessaire
                    return repository.save(team);
                })
                .orElseThrow(() -> new TeamNotFoundException(team_id));
    }

    @DeleteMapping("/{team_id}")
    void deleteTeam(@PathVariable Long team_id) {
        repository.deleteById(team_id);
    }
}
