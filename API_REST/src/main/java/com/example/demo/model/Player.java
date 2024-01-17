package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Player {

    private @Id @GeneratedValue Long player_id;

    private String first_name;

    private String last_name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id")
    @JsonIgnore
    private Team team;

    @Transient
    private Long teamId;

    public Player() {}

    public Player(String first_name, String last_name, Team team) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.team = team;
        this.teamId = team.getId();
    }

    public Long getPlayer_id() {
        return this.player_id;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public void setId(Long player_id) {
        this.player_id = player_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getTeamId() {
        return teamId;
    }
}
