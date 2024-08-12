package br.com.eduhkbr.ToolsChallenge.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TipoPagamentoTest {

    @Test
    public void testGetDescricao() {
        assertEquals("AVISTA", TipoPagamento.AVISTA.getDescricao());
        assertEquals("PARCELADO LOJA", TipoPagamento.PARCELADO_LOJA.getDescricao());
        assertEquals("PARCELADO EMISSOR", TipoPagamento.PARCELADO_EMISSOR.getDescricao());
    }

    @Test
    public void testEnumValues() {
        TipoPagamento[] expectedValues = {
                TipoPagamento.AVISTA,
                TipoPagamento.PARCELADO_LOJA,
                TipoPagamento.PARCELADO_EMISSOR
        };
        assertArrayEquals(expectedValues, TipoPagamento.values());
    }

    @Test
    public void testEnumValueOf() {
        assertEquals(TipoPagamento.AVISTA, TipoPagamento.valueOf("AVISTA"));
        assertEquals(TipoPagamento.PARCELADO_LOJA, TipoPagamento.valueOf("PARCELADO_LOJA"));
        assertEquals(TipoPagamento.PARCELADO_EMISSOR, TipoPagamento.valueOf("PARCELADO_EMISSOR"));
    }
}