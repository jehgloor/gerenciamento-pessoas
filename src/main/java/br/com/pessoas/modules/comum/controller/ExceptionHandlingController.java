package br.com.pessoas.modules.comum.controller;

import br.com.pessoas.modules.comum.exception.NotFoundException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlingController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public List<Message> notFoundError(NotFoundException ex) {
        return Collections.singletonList(new Message(ex.getMessage()));
    }

    @Data
    private static class Message {
        private String message;

        Message(String message) {
            this.message = message;
        }
    }
}
