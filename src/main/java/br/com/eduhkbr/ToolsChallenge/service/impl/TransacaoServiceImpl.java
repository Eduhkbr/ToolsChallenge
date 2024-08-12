package br.com.eduhkbr.ToolsChallenge.service.impl;

import br.com.eduhkbr.ToolsChallenge.enums.StatusPagamento;
import br.com.eduhkbr.ToolsChallenge.exceptions.ResourceNotFoundException;
import br.com.eduhkbr.ToolsChallenge.model.entity.Transacao;
import br.com.eduhkbr.ToolsChallenge.repository.TransacaoRepository;
import br.com.eduhkbr.ToolsChallenge.service.TransacaoServiceInterface;
import br.com.eduhkbr.ToolsChallenge.validator.FormaPagamentoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class TransacaoServiceImpl implements TransacaoServiceInterface {

    private final Logger logger = Logger.getLogger(TransacaoServiceImpl.class.getName());

    @Autowired
    private TransacaoRepository repository;

    @Override
    public Transacao salvarTransacao(Transacao transacao) {
        logger.info("Salvando uma transação");

        FormaPagamentoValidator.validarFormaPagamento(transacao.getFormaPagamento());

        transacao.getDescricao().setNsu("1234567890");
        transacao.getDescricao().setCodigoAutorizacao("147258369");
        transacao.getDescricao().setStatus(StatusPagamento.AUTORIZADO.getDescricao());
        return repository.save(transacao);
    }

    @Override
    public Transacao buscarTransacaoPorId(Long id) {
        logger.info("Buscando uma transação!");
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum valor encontrado para esse id!"));
    }

    @Override
    public Transacao buscarEstornoPorId(Long id) {
        logger.info("Buscando uma transação de estorno!");

        Transacao transacao = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum valor encontrado para esse id!"));
        transacao.getDescricao().setStatus(StatusPagamento.CANCELADO.getDescricao());

        return atualizarTransacao(transacao);
    }

    @Override
    public List<Transacao> listarTransacoes() {
        logger.info("Buscando todas as transacoes!");

        return repository.findAll();
    }

    @Override
    public Transacao atualizarTransacao(Transacao transacao) {
        logger.info("Atualizando uma transacao!");

        FormaPagamentoValidator.validarFormaPagamento(transacao.getFormaPagamento());

        Transacao entity = repository.findById(transacao.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum valor encontrado para esse id!"));

        return repository.save(entity);
    }
}
