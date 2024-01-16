package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Player {

    private @Id @GeneratedValue Long player_id;

    private String first_name;

    private String last_name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Player() {}

    public Player(String first_name, String last_name, Team team) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.team = team;
    }

    public Long getId() {
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
}
