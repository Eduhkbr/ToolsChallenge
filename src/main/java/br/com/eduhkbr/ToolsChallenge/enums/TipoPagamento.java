package br.com.eduhkbr.ToolsChallenge.enums;

public enum TipoPagamento {
    AVISTA("AVISTA"),
    PARCELADO_LOJA("PARCELADO LOJA"),
    PARCELADO_EMISSOR("PARCELADO EMISSOR");

    public static void fromTipo(String descricao) {
        for (TipoPagamento tipo : TipoPagamento.values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return;
            }
        }
        throw new IllegalArgumentException("Tipo de pagamento inválido: " + descricao);
    }

    private final String descricao;

    TipoPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}