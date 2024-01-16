package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.TeamRepository;
import com.example.demo.model.Team;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PlayerWebController {

    private final PlayerRepository repository;

    PlayerWebController(PlayerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/players")
    public String showPlayers(Model model) {
        List<Player> players = repository.findAll();
        model.addAttribute("players", players);
        return "players";
    }
}
