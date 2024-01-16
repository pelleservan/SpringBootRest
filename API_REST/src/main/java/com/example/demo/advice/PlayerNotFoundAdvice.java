package com.example.demo.advice;

import com.example.demo.exception.PlayerNoteFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PlayerNotFoundAdvice {

    @ExceptionHandler(PlayerNoteFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String playerNotFoundHandler(PlayerNoteFoundException ex) {
        return ex.getMessage();
    }
}
