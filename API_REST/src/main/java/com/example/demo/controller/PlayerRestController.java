package com.example.demo.controller;

import com.example.demo.exception.PlayerNoteFoundException;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.model.Player;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerRestController {

    private final PlayerRepository repository;

    PlayerRestController(PlayerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Player> all() {
        return repository.findAll();
    }

    @PostMapping
    Player newPlayer(@RequestBody Player newPlayer) {
        return repository.save(newPlayer);
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
