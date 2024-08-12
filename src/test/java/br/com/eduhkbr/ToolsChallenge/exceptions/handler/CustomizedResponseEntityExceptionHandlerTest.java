package br.com.eduhkbr.ToolsChallenge.exceptions.handler;

import br.com.eduhkbr.ToolsChallenge.exceptions.ExceptionResponse;
import br.com.eduhkbr.ToolsChallenge.exceptions.InvalidDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(CustomizedResponseEntityExceptionHandler.class)
public class CustomizedResponseEntityExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WebRequest webRequest;

    @BeforeEach
    public void setup() {
        when(webRequest.getDescription(false)).thenReturn("uri=/test");
    }

    @Test
    public void testHandleAllExceptions() {
        CustomizedResponseEntityExceptionHandler handler = new CustomizedResponseEntityExceptionHandler();
        Exception exception = new Exception("An error occurred");
        WebRequest request = mock(WebRequest.class);
        when(request.getDescription(false)).thenReturn("Teste");

        ResponseEntity<ExceptionResponse> response = handler.handleAllExceptions(exception, request);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testHandleInvalidDataExceptions() {
        CustomizedResponseEntityExceptionHandler handler = new CustomizedResponseEntityExceptionHandler();
        InvalidDataException exception = new InvalidDataException("Invalid data");
        WebRequest request = mock(WebRequest.class);
        when(request.getDescription(false)).thenReturn("Teste");

        ResponseEntity<ExceptionResponse> response = handler.handleInvalidDataExceptions(exception, request);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}