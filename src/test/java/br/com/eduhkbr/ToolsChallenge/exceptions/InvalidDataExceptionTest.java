package br.com.eduhkbr.ToolsChallenge.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

public class InvalidDataExceptionTest {

    @Test
    public void testExceptionMessage() {
        String errorMessage = "Invalid data provided";
        InvalidDataException exception = new InvalidDataException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testResponseStatus() {
        InvalidDataException exception = new InvalidDataException("Invalid data provided");

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        });

        assertEquals(HttpStatus.BAD_REQUEST, responseStatusException.getStatusCode());
        assertEquals("Invalid data provided", responseStatusException.getReason());
    }
}