package br.com.eduhkbr.ToolsChallenge.model.wrapper;

import br.com.eduhkbr.ToolsChallenge.enums.StatusPagamento;
import br.com.eduhkbr.ToolsChallenge.enums.TipoPagamento;
import br.com.eduhkbr.ToolsChallenge.model.Descricao;
import br.com.eduhkbr.ToolsChallenge.model.FormaPagamento;
import br.com.eduhkbr.ToolsChallenge.model.entity.Transacao;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TransacaoWrapperTest {

    @Test
    public void testGetTransacao() {
        Transacao transacao = getTransacao();
        TransacaoWrapper transacaoWrapper = new TransacaoWrapper();
        transacaoWrapper.setTransacao(transacao);

        assertEquals(transacao, transacaoWrapper.getTransacao());
    }

    @Test
    public void testSetTransacao() {
        Transacao transacao = getTransacao();
        TransacaoWrapper transacaoWrapper = new TransacaoWrapper();
        transacaoWrapper.setTransacao(transacao);

        assertNotNull(transacaoWrapper.getTransacao());
        assertEquals(transacao, transacaoWrapper.getTransacao());
    }

    private static Transacao getTransacao() {
        return new Transacao(1L, "1234-5678-9012-3456", getDescricao(), getFormaPagamento() );
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