package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Team {

    private @Id @GeneratedValue Long team_id;

    private String name;

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

    public void setId(Long team_id) {
        this.team_id = team_id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
