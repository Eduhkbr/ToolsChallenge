package br.com.eduhkbr.ToolsChallenge.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DescricaoTest {

    @Test
    public void testConstructorAndGetters() {
        Descricao descricao = new Descricao(
                new BigDecimal("100.00"),
                "2024-08-12T10:34:27",
                "Loja XYZ",
                "123456",
                "ABC123",
                "AUTORIZADO"
        );

        assertEquals(new BigDecimal("100.00"), descricao.getValor());
        assertEquals("2024-08-12T10:34:27", descricao.getDataHora());
        assertEquals("Loja XYZ", descricao.getEstabelecimento());
        assertEquals("123456", descricao.getNsu());
        assertEquals("ABC123", descricao.getCodigoAutorizacao());
        assertEquals("AUTORIZADO", descricao.getStatus());
    }

    @Test
    public void testSetters() {
        Descricao descricao = new Descricao();
        descricao.setValor(new BigDecimal("200.00"));
        descricao.setDataHora("2024-08-12T11:00:00");
        descricao.setEstabelecimento("Loja ABC");
        descricao.setNsu("654321");
        descricao.setCodigoAutorizacao("XYZ789");
        descricao.setStatus("CANCELADO");

        assertEquals(new BigDecimal("200.00"), descricao.getValor());
        assertEquals("2024-08-12T11:00:00", descricao.getDataHora());
        assertEquals("Loja ABC", descricao.getEstabelecimento());
        assertEquals("654321", descricao.getNsu());
        assertEquals("XYZ789", descricao.getCodigoAutorizacao());
        assertEquals("CANCELADO", descricao.getStatus());
    }


}