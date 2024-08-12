package br.com.eduhkbr.ToolsChallenge.exceptions;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionResponseTest {

    @Test
    public void testConstructor() {
        Date timestamp = new Date();
        String message = "Error occurred";
        String details = "Detailed error message";
        ExceptionResponse exceptionResponse = new ExceptionResponse(timestamp, message, details);
    }
}