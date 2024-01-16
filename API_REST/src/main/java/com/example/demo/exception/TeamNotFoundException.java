package com.example.demo.exception;

public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException(Long team_id) {
        super("Could not find team " + team_id);
    }
}
