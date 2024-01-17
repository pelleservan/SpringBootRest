package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Team {

    private @Id @GeneratedValue Long team_id;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Player> players;

    public Team() {}

    public Team(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.team_id;
    }

    public String getName() {
        return this.name;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void setId(Long team_id) {
        this.team_id = team_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(team_id, team.team_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team_id);
    }
}
