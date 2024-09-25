package br.com.eduhkbr.ToolsChallenge.controller;

import br.com.eduhkbr.ToolsChallenge.exceptions.InvalidDataException;
import br.com.eduhkbr.ToolsChallenge.model.entity.Transacao;
import br.com.eduhkbr.ToolsChallenge.model.wrapper.TransacaoWrapper;
import br.com.eduhkbr.ToolsChallenge.service.impl.TransacaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private TransacaoServiceImpl service;

    @GetMapping(value = "/consulta", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransacaoWrapper>> listarTransacoes() {
        List<TransacaoWrapper> transacaoWrappers = service.listarTransacoes().stream()
                .map(TransacaoWrapper::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(transacaoWrappers);
    }

    @GetMapping(value = "/consulta/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransacaoWrapper> buscarPagamentoPorId(@PathVariable Long id) {
        Transacao transacao = service.buscarTransacaoPorId(id);
        return criarWrapperResponse(transacao);
    }

    @GetMapping(value = "/estorno/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransacaoWrapper> buscarEstornoPorId(@PathVariable Long id) {
        Transacao estorno = service.buscarEstornoPorId(id);
        return criarWrapperResponse(estorno);
    }

    private ResponseEntity<TransacaoWrapper> criarWrapperResponse(Transacao transacao) {
        if (transacao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        return ResponseEntity.ok(new TransacaoWrapper(transacao));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransacaoWrapper> salvarTransacao(@RequestBody TransacaoWrapper transacaoWrapper) {
        if (transacaoWrapper == null || transacaoWrapper.getTransacao() == null) {
            throw new InvalidDataException("Transação inválida!");
        }

        Transacao transacao = transacaoWrapper.getTransacao();
        Transacao savedTransacao = service.salvarTransacao(transacao);

        TransacaoWrapper responseWrapper = new TransacaoWrapper();
        responseWrapper.setTransacao(savedTransacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseWrapper);
    }

}
