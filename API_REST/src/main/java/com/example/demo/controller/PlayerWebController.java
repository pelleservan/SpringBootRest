package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.model.Team;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.TeamRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlayerWebController {

    private final PlayerRepository repository;
    private final TeamRepository teamRepository;

    PlayerWebController(PlayerRepository repository, TeamRepository teamRepository) {
        this.repository = repository;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/players")
    public String showForm(Model model) {
        List<Player> players = repository.findAll();
        model.addAttribute("players", players);
        model.addAttribute("newPlayer", new Player());
        model.addAttribute("player_id", "");
        return "players";
    }

    @GetMapping("/players/add")
    public String showAddPlayerForm(Model model) {
        model.addAttribute("newPlayer", new Player());
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("addPlayerForm", true);
        return "players";
    }

    @PostMapping("/players/add")
    public String addPlayer(@ModelAttribute("newPlayer") @Validated Player newPlayer,
                            BindingResult result,
                            @RequestParam(name = "teamId", required = false) Long teamId,
                            Model model) {

        if (teamId != null) {
            Team team = teamRepository.findById(teamId).orElse(null);

            if (team != null) {
                newPlayer.setTeam(team);
            } else {
                result.rejectValue("team", "team.not-found", "Selected team not found");
            }
        } else {
            newPlayer.setTeam(null);
        }

        if (result.hasErrors()) {
            model.addAttribute("newPlayer", newPlayer);
            model.addAttribute("teams", teamRepository.findAll());
            return "players";
        }

        repository.save(newPlayer);
        return "redirect:/players";
    }

    @ModelAttribute("allTeams")
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @PostMapping("/players/del")
    public String deletePlayer(@RequestParam(name = "player_id") Long player_id) {
        repository.deleteById(player_id);
        return "redirect:/players";
    }

    @ModelAttribute("allPlayers")
    public List<Player> getAllPlayers() {
        return repository.findAll();
    }
}
