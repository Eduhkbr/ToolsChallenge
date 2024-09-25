package br.com.eduhkbr.ToolsChallenge.service.impl;

import br.com.eduhkbr.ToolsChallenge.enums.StatusPagamento;
import br.com.eduhkbr.ToolsChallenge.enums.TipoPagamento;
import br.com.eduhkbr.ToolsChallenge.exceptions.ResourceNotFoundException;
import br.com.eduhkbr.ToolsChallenge.model.Descricao;
import br.com.eduhkbr.ToolsChallenge.model.FormaPagamento;
import br.com.eduhkbr.ToolsChallenge.model.entity.Transacao;
import br.com.eduhkbr.ToolsChallenge.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransacaoServiceImplTest {

    @InjectMocks
    private TransacaoServiceImpl transacaoService;

    @Mock
    private TransacaoRepository repository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarTransacao() {
        Transacao transacao = getTransacao();
        when(repository.save(any(Transacao.class))).thenReturn(transacao);

        Transacao savedTransacao = transacaoService.salvarTransacao(transacao);

        assertNotNull(savedTransacao);
        assertEquals("1234567890", savedTransacao.getDescricao().getNsu());
        assertEquals("147258369", savedTransacao.getDescricao().getCodigoAutorizacao());
        assertEquals(StatusPagamento.AUTORIZADO.getDescricao(), savedTransacao.getDescricao().getStatus());
        verify(repository, times(1)).save(transacao);
    }

    @Test
    public void testBuscarTransacaoPorId() {
        Transacao transacao = getTransacao();
        when(repository.findById(1L)).thenReturn(Optional.of(transacao));

        Transacao foundTransacao = transacaoService.buscarTransacaoPorId(1L);

        assertNotNull(foundTransacao);
        assertEquals(transacao, foundTransacao);
        verify(repository, times(1)).findById(1L);
    }

    @Test
    public void testBuscarTransacaoPorIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> transacaoService.buscarTransacaoPorId(1L));
        verify(repository, times(1)).findById(1L);
    }

    @Test
    @WithMockUser(username = "test")
    public void testBuscarEstornoPorId() {
        Transacao transacao = getTransacao();
        when(repository.findById(1L)).thenReturn(Optional.of(transacao));
        when(repository.save(any(Transacao.class))).thenReturn(transacao);

        Transacao estornoTransacao = transacaoService.buscarEstornoPorId(1L);

        assertNotNull(estornoTransacao);
        assertEquals(StatusPagamento.CANCELADO.getDescricao(), estornoTransacao.getDescricao().getStatus());
        verify(repository, times(2)).findById(1L);
        verify(repository, times(1)).save(transacao);
    }

    @Test
    public void testListarTransacoes() {
        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(getTransacao());
        when(repository.findAll()).thenReturn(transacoes);

        List<Transacao> foundTransacoes = transacaoService.listarTransacoes();

        assertNotNull(foundTransacoes);
        assertEquals(1, foundTransacoes.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testAtualizarTransacao() {
        Transacao transacao = getTransacao();
        when(repository.findById(1L)).thenReturn(Optional.of(transacao));
        when(repository.save(any(Transacao.class))).thenReturn(transacao);

        Transacao updatedTransacao = transacaoService.atualizarTransacao(transacao);

        assertNotNull(updatedTransacao);
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(transacao);
    }

    @Test
    public void testAtualizarTransacaoNotFound() {
        Transacao transacao = getTransacao();
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> transacaoService.atualizarTransacao(transacao));
        verify(repository, times(1)).findById(1L);
    }

    private static Transacao getTransacao() {
        return new Transacao(1L, "1234-5678-9012-3456", getDescricao(), getFormaPagamento());
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