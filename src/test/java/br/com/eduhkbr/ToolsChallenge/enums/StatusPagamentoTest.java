package br.com.eduhkbr.ToolsChallenge.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StatusPagamentoTest {

    @Test
    public void testGetDescricao() {
        assertEquals("AUTORIZADO", StatusPagamento.AUTORIZADO.getDescricao());
        assertEquals("CANCELADO", StatusPagamento.CANCELADO.getDescricao());
        assertEquals("NEGADO", StatusPagamento.NEGADO.getDescricao());
    }

    @Test
    public void testEnumValues() {
        StatusPagamento[] expectedValues = {
                StatusPagamento.AUTORIZADO,
                StatusPagamento.CANCELADO,
                StatusPagamento.NEGADO
        };
        assertArrayEquals(expectedValues, StatusPagamento.values());
    }

    @Test
    public void testEnumValueOf() {
        assertEquals(StatusPagamento.AUTORIZADO, StatusPagamento.valueOf("AUTORIZADO"));
        assertEquals(StatusPagamento.CANCELADO, StatusPagamento.valueOf("CANCELADO"));
        assertEquals(StatusPagamento.NEGADO, StatusPagamento.valueOf("NEGADO"));
    }
}