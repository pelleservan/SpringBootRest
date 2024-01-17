package com.example.demo.controller;

import com.example.demo.exception.PlayerNoteFoundException;
import com.example.demo.model.Team;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.model.Player;
import com.example.demo.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class PlayerRestController {

    private final PlayerRepository repository;
    private final TeamRepository teamRepository;

    PlayerRestController(PlayerRepository repository, TeamRepository teamRepository) {
        this.repository = repository;
        this.teamRepository = teamRepository;
    }

    @GetMapping
    List<Player> all() {
        return repository.findAll();
    }

    @PostMapping
    ResponseEntity<Player> newPlayer(@RequestBody Player newPlayer) {
        try {
            if (newPlayer.getTeam() != null && newPlayer.getTeam().getId() != null) {
                Optional<Team> existingTeam = teamRepository.findById(newPlayer.getTeam().getId());
                if (existingTeam.isPresent()) {
                    newPlayer.setTeam(existingTeam.get());
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                }
            }

            Player savedPlayer = repository.save(newPlayer);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPlayer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/createWithTeam/{team_id}")
    ResponseEntity<Player> createPlayerWithTeam(@RequestBody Player newPlayer, @PathVariable Long team_id) {
        try {
            Optional<Team> existingTeam = teamRepository.findById(team_id);

            if (existingTeam.isPresent()) {
                newPlayer.setTeam(existingTeam.get());
                Player savedPlayer = repository.save(newPlayer);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedPlayer);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{player_id}")
    Player one(@PathVariable Long player_id) {
        return repository.findById(player_id)
                .orElseThrow(() -> new PlayerNoteFoundException(player_id));
    }

    @PutMapping("/{player_id}")
    Player replacePlayer(@RequestBody Player newPlayer, @PathVariable Long player_id) {
        return  repository.findById(player_id)
                .map(player -> {
                    player.setFirst_name(newPlayer.getFirst_name());
                    player.setLast_name(newPlayer.getLast_name());
                    return repository.save(player);
                })
                .orElseGet(() -> {
                    newPlayer.setId(player_id);
                    return repository.save(newPlayer);
                });
    }

    @PatchMapping("/{player_id}")
    Player partiallyUpdatePlayer(@RequestBody Player updatedPlayer, @PathVariable Long player_id) {
        return repository.findById(player_id)
                .map(player -> {
                    if (updatedPlayer.getFirst_name() != null) {
                        player.setFirst_name(updatedPlayer.getFirst_name());
                    }
                    if (updatedPlayer.getLast_name() != null) {
                        player.setLast_name(updatedPlayer.getLast_name());
                    }
                    return repository.save(player);
                })
                .orElseThrow(() -> new PlayerNoteFoundException(player_id));
    }


    @DeleteMapping("/{player_id}")
    void deletePlayer(@PathVariable Long player_id) {
        repository.deleteById(player_id);
    }
}
