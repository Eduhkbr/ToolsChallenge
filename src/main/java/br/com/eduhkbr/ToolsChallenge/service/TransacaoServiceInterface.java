package br.com.eduhkbr.ToolsChallenge.service;

import br.com.eduhkbr.ToolsChallenge.model.entity.Transacao;

import java.util.List;

public interface TransacaoServiceInterface {

    Transacao salvarTransacao(Transacao transacao);
    Transacao buscarTransacaoPorId(Long id);

    Transacao buscarEstornoPorId(Long id);

    List<Transacao> listarTransacoes();
    Transacao atualizarTransacao(Transacao transacao);

}
