package br.com.eduhkbr.ToolsChallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4082091005423872981L;

    public ResourceNotFoundException(String ex){
        super(ex);
    }

}