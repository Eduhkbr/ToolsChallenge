package br.com.eduhkbr.ToolsChallenge.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FormaPagamentoTest {

    @Test
    public void testConstructorAndGetters() {
        FormaPagamento formaPagamento = new FormaPagamento("AVISTA", 1);
        assertEquals("AVISTA", formaPagamento.getTipo());
        assertEquals(1, formaPagamento.getParcelas());
    }

    @Test
    public void testSetters() {
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setTipo("PARCELADO LOJA");
        formaPagamento.setParcelas(6);
        assertEquals("PARCELADO LOJA", formaPagamento.getTipo());
        assertEquals(6, formaPagamento.getParcelas());
    }

    @Test
    public void testToString() {
        FormaPagamento formaPagamento = new FormaPagamento("PARCELADO EMISSOR", 12);
        String expected = "FormaPagamento{tipo='PARCELADO EMISSOR', parcelas=12}";
        assertEquals(expected, formaPagamento.toString());
    }

    @Test
    public void testEquals() {
        FormaPagamento formaPagamento1 = new FormaPagamento("PARCELADO EMISSOR", 12);
        FormaPagamento formaPagamento2 = new FormaPagamento("PARCELADO EMISSOR", 12);
        FormaPagamento formaPagamento3 = new FormaPagamento("AVISTA", 1);
        assertEquals(formaPagamento1, formaPagamento2);
        assertNotEquals(formaPagamento1, formaPagamento3);
    }

    @Test
    public void testHashCode() {
        FormaPagamento formaPagamento1 = new FormaPagamento("PARCELADO EMISSOR", 12);
        FormaPagamento formaPagamento2 = new FormaPagamento("PARCELADO EMISSOR", 12);
        assertEquals(formaPagamento1.hashCode(), formaPagamento2.hashCode());
    }
}