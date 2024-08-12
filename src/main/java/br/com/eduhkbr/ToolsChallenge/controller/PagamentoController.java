package br.com.eduhkbr.ToolsChallenge.controller;

import br.com.eduhkbr.ToolsChallenge.model.entity.Transacao;
import br.com.eduhkbr.ToolsChallenge.model.wrapper.TransacaoWrapper;
import br.com.eduhkbr.ToolsChallenge.service.impl.TransacaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private TransacaoServiceImpl service;

    @GetMapping(value = "/consulta", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TransacaoWrapper> listarTransacoes(){
        List<TransacaoWrapper> transacaoWrapperList = new ArrayList<>();
        List<Transacao> transacoes = service.listarTransacoes();

        for (Transacao transacao : transacoes) {
            TransacaoWrapper transacaoWrapper = new TransacaoWrapper();
            transacaoWrapper.setTransacao(transacao);
            transacaoWrapperList.add(transacaoWrapper);
        }

        return transacaoWrapperList;
    }

    @GetMapping(value = "/consulta/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransacaoWrapper buscarPagamentoPorId(@PathVariable(value = "id") Long id){
        TransacaoWrapper transacaoWrapper = new TransacaoWrapper();
        transacaoWrapper.setTransacao(service.buscarTransacaoPorId(id));
        return transacaoWrapper;
    }

    @GetMapping(value = "/estorno/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransacaoWrapper buscarEstornoPorId(@PathVariable(value = "id") Long id){
        TransacaoWrapper transacaoWrapper = new TransacaoWrapper();
        transacaoWrapper.setTransacao(service.buscarEstornoPorId(id));
        return transacaoWrapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TransacaoWrapper salvarTransacao(@RequestBody TransacaoWrapper transacaoWrapper){
        Transacao transacao = transacaoWrapper.getTransacao();
        service.salvarTransacao(transacao);
        return transacaoWrapper;
    }

}
