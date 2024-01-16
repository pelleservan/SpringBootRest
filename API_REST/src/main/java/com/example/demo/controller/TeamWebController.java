package com.example.demo.controller;

import com.example.demo.repository.TeamRepository;
import com.example.demo.model.Team;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        return "teams";
    }
}
