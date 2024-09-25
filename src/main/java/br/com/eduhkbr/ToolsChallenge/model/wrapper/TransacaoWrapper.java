package br.com.eduhkbr.ToolsChallenge.model.wrapper;

import br.com.eduhkbr.ToolsChallenge.model.entity.Transacao;

public class TransacaoWrapper {
    private Transacao transacao;

    public TransacaoWrapper() {
    }

    public TransacaoWrapper(Transacao transacao) {
        this.transacao = transacao;
    }

    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }
}
