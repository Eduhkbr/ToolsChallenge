package br.com.eduhkbr.ToolsChallenge.controller;

import br.com.eduhkbr.ToolsChallenge.exceptions.InvalidDataException;
import br.com.eduhkbr.ToolsChallenge.exceptions.ResourceNotFoundException;
import br.com.eduhkbr.ToolsChallenge.model.entity.Transacao;
import br.com.eduhkbr.ToolsChallenge.model.wrapper.TransacaoWrapper;
import br.com.eduhkbr.ToolsChallenge.service.impl.TransacaoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PagamentoController.class)
public class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransacaoServiceImpl service;

    @InjectMocks
    private PagamentoController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsultarTodasTransacoes() throws Exception {
        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(new Transacao(1L, "1234-5678-9012-3456", null, null));
        when(service.listarTransacoes()).thenReturn(transacoes);

        mockMvc.perform(get("/pagamento/consulta")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].transacao.id").value(1L))
                .andExpect(jsonPath("$[0].transacao.cartao").value("1234-5678-9012-3456"));

        verify(service, times(1)).listarTransacoes();
    }

    @Test
    public void testConsultarPagamentoPorId() throws Exception {
        Transacao transacao = new Transacao(1L, "1234-5678-9012-3456", null, null);
        when(service.buscarTransacaoPorId(1L)).thenReturn(transacao);

        mockMvc.perform(get("/pagamento/consulta/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transacao.id").value(1L))
                .andExpect(jsonPath("$.transacao.cartao").value("1234-5678-9012-3456"));

        verify(service, times(1)).buscarTransacaoPorId(1L);
    }

    @Test
    public void testBuscarEstornoPorId() throws Exception {
        Transacao transacao = new Transacao(1L, "1234-5678-9012-3456", null, null);
        when(service.buscarEstornoPorId(1L)).thenReturn(transacao);

        mockMvc.perform(get("/pagamento/estorno/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transacao.id").value(1L))
                .andExpect(jsonPath("$.transacao.cartao").value("1234-5678-9012-3456"));

        verify(service, times(1)).buscarEstornoPorId(1L);
    }

    @Test
    public void testRealizarPagamentoESalvarTransacao() throws Exception {
        Transacao transacao = new Transacao(1L, "1234-5678-9012-3456", null, null);
        TransacaoWrapper transacaoWrapper = new TransacaoWrapper();
        transacaoWrapper.setTransacao(transacao);
        when(service.salvarTransacao(any(Transacao.class))).thenReturn(transacao);

        mockMvc.perform(post("/pagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"transacao\":{\"id\":1,\"cartao\":\"1234-5678-9012-3456\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transacao.id").value(1L))
                .andExpect(jsonPath("$.transacao.cartao").value("1234-5678-9012-3456"));

        verify(service, times(1)).salvarTransacao(any(Transacao.class));
    }

    @Test
    public void testBuscarPagamentoPorIdNotFoundException() throws Exception {
        when(service.buscarTransacaoPorId(1L)).thenThrow(new ResourceNotFoundException("Nenhum valor encontrado para esse id!"));

        mockMvc.perform(get("/pagamento/consulta/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}