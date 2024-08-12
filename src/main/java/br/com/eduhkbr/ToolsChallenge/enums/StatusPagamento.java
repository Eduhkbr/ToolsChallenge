package br.com.eduhkbr.ToolsChallenge.enums;

public enum StatusPagamento {
    AUTORIZADO("AUTORIZADO"),
    CANCELADO("CANCELADO"),
    NEGADO("NEGADO");

    private final String descricao;

    StatusPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}