package br.com.eduhkbr.ToolsChallenge.model.entity;

import br.com.eduhkbr.ToolsChallenge.enums.StatusPagamento;
import br.com.eduhkbr.ToolsChallenge.enums.TipoPagamento;
import br.com.eduhkbr.ToolsChallenge.model.Descricao;
import br.com.eduhkbr.ToolsChallenge.model.FormaPagamento;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TransacaoTest {

    @Test
    public void testConstructorAndGetters() {
        Descricao descricao = getDescricao();
        FormaPagamento formaPagamento = getFormaPagamento();
        Transacao transacao = new Transacao(1L, "1234-5678-9012-3456", descricao, formaPagamento);

        assertEquals(1L, transacao.getId());
        assertEquals("1234-5678-9012-3456", transacao.getCartao());
        assertEquals(descricao, transacao.getDescricao());
        assertEquals(formaPagamento, transacao.getFormaPagamento());
    }

    @Test
    public void testSetters() {
        Descricao descricao = getDescricao();
        FormaPagamento formaPagamento = getFormaPagamento();
        Transacao transacao = new Transacao();

        transacao.setId(1L);
        transacao.setCartao("1234-5678-9012-3456");
        transacao.setDescricao(descricao);
        transacao.setFormaPagamento(formaPagamento);

        assertEquals(1L, transacao.getId());
        assertEquals("1234-5678-9012-3456", transacao.getCartao());
        assertEquals(descricao, transacao.getDescricao());
        assertEquals(formaPagamento, transacao.getFormaPagamento());
    }

    @Test
    public void testToString() {
        Descricao descricao = getDescricao();
        FormaPagamento formaPagamento = getFormaPagamento();
        Transacao transacao = new Transacao(1L, "1234-5678-9012-3456", descricao, formaPagamento);

        String expected = "Transacao{cartao=1234-5678-9012-3456, id=1, descricao=Descricao{valor=100, dataHora='null', estabelecimento='null', nsu='null', codigoAutorizacao='null', status='AUTORIZADO'}, formaPagamento=FormaPagamento{tipo='PARCELADO EMISSOR', parcelas=12}}";
        assertEquals(expected, transacao.toString());
    }

    @Test
    public void testEquals() {
        Descricao descricao1 = getDescricao();
        FormaPagamento formaPagamento1 = getFormaPagamento();
        Transacao transacao1 = new Transacao(1L, "1234-5678-9012-3456", descricao1, formaPagamento1);

        Descricao descricao2 = getDescricao();
        FormaPagamento formaPagamento2 = getFormaPagamento();
        Transacao transacao2 = new Transacao(1L, "1234-5678-9012-3456", descricao2, formaPagamento2);

        Descricao descricao3 = getDescricao();
        descricao3.setStatus(StatusPagamento.NEGADO.getDescricao());
        FormaPagamento formaPagamento3 = getFormaPagamento();
        formaPagamento3.setTipo(TipoPagamento.PARCELADO_LOJA.getDescricao());
        Transacao transacao3 = new Transacao(2L, "6543-2109-8765-4321", descricao3, formaPagamento3);

        assertEquals(transacao1, transacao2);
        assertNotEquals(transacao1, transacao3);
    }

    @Test
    public void testHashCode() {
        Descricao descricao1 = getDescricao();
        FormaPagamento formaPagamento1 = getFormaPagamento();
        Transacao transacao1 = new Transacao(1L, "1234-5678-9012-3456", descricao1, formaPagamento1);

        Descricao descricao2 = getDescricao();
        FormaPagamento formaPagamento2 = getFormaPagamento();
        Transacao transacao2 = new Transacao(1L, "1234-5678-9012-3456", descricao2, formaPagamento2);

        assertEquals(transacao1.hashCode(), transacao2.hashCode());
    }


    private static FormaPagamento getFormaPagamento() {
        return new FormaPagamento(TipoPagamento.PARCELADO_EMISSOR.getDescricao(), 12);
    }

    private static Descricao getDescricao() {
        Descricao descricao = new Descricao();
        descricao.setStatus(StatusPagamento.AUTORIZADO.getDescricao());
        descricao.setValor(new BigDecimal(100));
        return descricao;
    }
}