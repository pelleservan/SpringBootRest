package com.example.demo.exception;

public class PlayerNoteFoundException extends RuntimeException {

    public PlayerNoteFoundException(Long player_id) {
        super("Could not find player " + player_id);
    }
}
