package br.com.eduhkbr.ToolsChallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDataException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5493307906413113978L;

    public InvalidDataException(String ex){
        super(ex);
    }

}