package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.repository.TeamRepository;
import com.example.demo.model.Team;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeamWebController {

    private final TeamRepository repository;

    TeamWebController(TeamRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/teams")
    public String showTeams(Model model) {
        List<Team> teams = repository.findAll();
        model.addAttribute("teams", teams);
        model.addAttribute("newTeam", new Team());
        model.addAttribute("addTeamForm", false);
        return "teams";
    }

    @GetMapping("/teams/add")
    public String showAddTeamForm(Model model) {
        model.addAttribute("newTeam", new Team());
        model.addAttribute("addTeamForm", true);
        return "teams";
    }

    @PostMapping("/teams/add")
    public String addTeam(@ModelAttribute Team newTeam) {
        repository.save(newTeam);
        return "redirect:/teams";
    }

    @PostMapping("/teams/del")
    public String deleteTeam(@RequestParam(name = "id") Long team_id) {
        repository.deleteById(team_id);
        return "redirect:/teams";
    }

    @ModelAttribute("allTeams")
    public List<Team> getAllTeams() {
        return repository.findAll();
    }

}
