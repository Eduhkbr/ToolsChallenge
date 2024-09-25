package br.com.eduhkbr.ToolsChallenge.controller;

import br.com.eduhkbr.ToolsChallenge.exceptions.ResourceNotFoundException;
import br.com.eduhkbr.ToolsChallenge.model.entity.Transacao;
import br.com.eduhkbr.ToolsChallenge.model.wrapper.TransacaoWrapper;
import br.com.eduhkbr.ToolsChallenge.service.impl.TransacaoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransacaoServiceImpl service;

    @InjectMocks
    private PagamentoController controller;

    private ObjectMapper objectMapper;

    private static final Long TRANSACTION_ID = 1L;
    private static final String CARTAO = "1234-5678-9012-3456";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    private Transacao criarTransacao(Long id) {
        return new Transacao(id, CARTAO, null, null);
    }

    private TransacaoWrapper criarTransacaoWrapper(Long id) {
        Transacao transacao = criarTransacao(id);
        TransacaoWrapper wrapper = new TransacaoWrapper();
        wrapper.setTransacao(transacao);
        return wrapper;
    }

    @Test
    @WithMockUser(username = "test")
    public void testConsultarTodasTransacoes() throws Exception {
        List<Transacao> transacoes = List.of(criarTransacao(TRANSACTION_ID));
        when(service.listarTransacoes()).thenReturn(transacoes);

        mockMvc.perform(get("/pagamento/consulta").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].transacao.id").value(TRANSACTION_ID))
                .andExpect(jsonPath("$[0].transacao.cartao").value(CARTAO));

        verify(service, times(1)).listarTransacoes();
    }

    @Test
    @WithMockUser(username = "test")
    public void testConsultarPagamentoPorId() throws Exception {
        Transacao transacao = criarTransacao(TRANSACTION_ID);
        when(service.buscarTransacaoPorId(TRANSACTION_ID)).thenReturn(transacao);

        mockMvc.perform(get("/pagamento/consulta/{id}", TRANSACTION_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transacao.id").value(TRANSACTION_ID))
                .andExpect(jsonPath("$.transacao.cartao").value(CARTAO));

        verify(service, times(1)).buscarTransacaoPorId(TRANSACTION_ID);
    }

    @Test
    @WithMockUser(username = "test")
    public void testBuscarEstornoPorId() throws Exception {
        Transacao transacao = criarTransacao(TRANSACTION_ID);
        when(service.buscarEstornoPorId(TRANSACTION_ID)).thenReturn(transacao);

        mockMvc.perform(get("/pagamento/estorno/{id}", TRANSACTION_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transacao.id").value(TRANSACTION_ID))
                .andExpect(jsonPath("$.transacao.cartao").value(CARTAO));

        verify(service, times(1)).buscarEstornoPorId(TRANSACTION_ID);
    }

    @Test
    @WithMockUser(username = "test")
    public void testRealizarPagamentoESalvarTransacao() throws Exception {
        TransacaoWrapper transacaoWrapper = criarTransacaoWrapper(TRANSACTION_ID);
        when(service.salvarTransacao(any(Transacao.class))).thenReturn(criarTransacao(TRANSACTION_ID));

        mockMvc.perform(post("/pagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoWrapper)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.transacao.id").value(TRANSACTION_ID))
                .andExpect(jsonPath("$.transacao.cartao").value(CARTAO));
    }

    @Test
    @WithMockUser(username = "test")
    public void testSalvarTransacaoInvalida() throws Exception {
        TransacaoWrapper transacaoWrapper = new TransacaoWrapper();

        mockMvc.perform(post("/pagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacaoWrapper)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "test")
    public void testBuscarPagamentoPorIdNotFoundException() throws Exception {
        when(service.buscarTransacaoPorId(TRANSACTION_ID)).thenThrow(new ResourceNotFoundException("Nenhum valor encontrado para esse id!"));

        mockMvc.perform(get("/pagamento/consulta/{id}", TRANSACTION_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}